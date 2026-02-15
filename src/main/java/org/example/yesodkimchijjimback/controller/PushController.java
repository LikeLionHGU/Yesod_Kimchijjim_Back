package org.example.yesodkimchijjimback.controller;

import lombok.RequiredArgsConstructor;
import org.example.yesodkimchijjimback.dto.push.PushSubscribeRequest;
import org.example.yesodkimchijjimback.service.WebPushService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/push")
public class PushController {

    private final WebPushService pushService;

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(
            @SessionAttribute(name = GoogleAuthController.SESSION_USER_ID, required = false) Long userId,
            @RequestBody PushSubscribeRequest pushSubscribeRequeset){

        pushService.subscribe(userId, pushSubscribeRequeset);

        return ResponseEntity.ok().build();
    }

}
