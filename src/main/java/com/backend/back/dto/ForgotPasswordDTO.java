package com.backend.back.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDTO {
    @Email
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

}