package com.friend.mail.demo.listener;


import com.friend.mail.demo.config.MailConfig;
import com.friend.mail.demo.service.MailProcessorService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.*;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailListener {

    private final MailConfig config;
    private final MailProcessorService processorService;

    public MailListener(MailConfig config, MailProcessorService processorService) {
        this.config = config;
        this.processorService = processorService;
    }

    @PostConstruct
    public void startListening() {
        new Thread(this::listenLoop, "IMAP-IDLE-Thread").start();
    }

    private void listenLoop() {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getInstance(props);
            Store store = session.getStore();
            store.connect(config.host, config.port, config.username, config.password);

            Folder inbox = store.getFolder(config.folder);
            inbox.open(Folder.READ_ONLY);

            inbox.addMessageCountListener(new MessageCountAdapter() {
                public void messagesAdded(MessageCountEvent ev) {
                    for (Message message : ev.getMessages()) {
                        processorService.processEmail((MimeMessage) message);
                    }
                }
            });

            while (true) {
                if (inbox instanceof com.sun.mail.imap.IMAPFolder imapFolder) {
                    imapFolder.idle();
                } else {
                    Thread.sleep(30000);
                    inbox.getMessageCount();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: adicionar log e retry logic aqui
        }
    }
}
