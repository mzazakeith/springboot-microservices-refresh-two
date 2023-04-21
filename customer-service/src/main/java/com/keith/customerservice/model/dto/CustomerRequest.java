package com.keith.customerservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotEmpty(message = "First name cannot be blank")
    String firstName;

    @NotEmpty(message = "Last name cannot be blank")
    String lastName;

    @NotEmpty(message = "Email cannot be blank")
    String email;
}
