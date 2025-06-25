package com.friend.mail.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {
    @Value("${mail.imap.host}")
    public String host;

    @Value("${mail.imap.port}")
    public int port;

    @Value("${mail.imap.username}")
    public String username;

    @Value("${mail.imap.password}")
    public String password;

    @Value("${mail.imap.folder}")
    public String folder;
}
