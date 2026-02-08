package org.example.yesodkimchijjimback.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GoogleLoginRequest {
    @NotBlank
    private String idToken;
}