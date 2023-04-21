package com.keith.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_notification")
public class Notification {

    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(nullable = false)
    @JsonProperty("customer_id")
    private UUID customerId;
    @Column(nullable = false)
    @JsonProperty("email")
    private String email;
    @Column(nullable = false)
    @JsonProperty("sender")
    private String sender;
    @Column(nullable = false)
    @JsonProperty("message")
    private String message;
    private LocalDateTime sentAt;

    @PrePersist
    protected void onCreate() {
        sentAt = LocalDateTime.now();
    }
}
