package com.keith.notificationservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    @NotEmpty(message = "customer id cannot be blank")
    UUID customerId;
    @NotEmpty(message = "customer name cannot be blank")
    String customerName;
    @NotEmpty(message = "message cannot be blank")
    String message;
}
