package org.example.yesodkimchijjimback.dto.UserRe;

import lombok.Builder;
import lombok.Data;
import org.example.yesodkimchijjimback.domain.User;

@Data
@Builder
public class UserResponse {
    private Long userId;
    private String name;
    private String email;
    private String googleSub;

    public static UserResponse fromResponse(User user){
        return builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .googleSub(user.getGoogleSub())
                .build();
    }
}
