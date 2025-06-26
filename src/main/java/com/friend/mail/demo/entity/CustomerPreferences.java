package com.friend.mail.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "customer_preferences")
@Getter
@Setter
public class CustomerPreferences {
    @Id
    @Column(name = "customer_preferences_id", nullable = false)
    private UUID customerPreferencesId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "whats_app_number", nullable = false, length = 20)
    private String whatsAppNumber;

    @Column(name = "lang", nullable = false, length = 10)
    private String lang;

    @Column(name = "message_time_preference", length = 20)
    private String messageTimePreference;

    @Column(name = "keywords_ignored", columnDefinition = "TEXT")
    private String keywordsIgnored;

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private java.time.LocalDateTime updatedAt;


}
