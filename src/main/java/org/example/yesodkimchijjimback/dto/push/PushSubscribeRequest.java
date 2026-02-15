package org.example.yesodkimchijjimback.dto.push;

import lombok.Data;
import lombok.Getter;

@Data
public class PushSubscribeRequest {
    private String endpoint;
    private String expirationTime;
    private Keys keys;

    @Getter
    public static class Keys{
        private String p256dh;
        private String auth;
    }
}
