package com.keith.customerservice.model.dto.request;

import jakarta.validation.constraints.Email;
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

    @Email(message = "Please enter a valid email")
    @NotEmpty(message = "Email cannot be blank")
    String email;
}
