package org.example.yesodkimchijjimback.controller;

import lombok.RequiredArgsConstructor;
import org.example.yesodkimchijjimback.domain.User;
import org.example.yesodkimchijjimback.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.yesodkimchijjimback.dto.UserRe.UserRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class RoomMemberController {

    private final UserService userService;

    @PostMapping("/join")
    public void joinRoom(@RequestBody UserRequest userRequest, @AuthenticationPrincipal User user){
        userService.joinRoom(userRequest, user);
    }



}
