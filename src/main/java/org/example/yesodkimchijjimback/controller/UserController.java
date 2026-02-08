package org.example.yesodkimchijjimback.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.yesodkimchijjimback.dto.RoomMemberRe.RoomMemberResponse;
import org.example.yesodkimchijjimback.dto.UserRe.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.yesodkimchijjimback.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyUserProfile(
            @SessionAttribute(name = GoogleAuthController.SESSION_USER_ID) Long userId){
        UserResponse userResponse = userService.getMyUserProfile(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/me/room")
    public ResponseEntity<RoomMemberResponse> getMyRoom(
            @SessionAttribute(name = GoogleAuthController.SESSION_USER_ID) Long userId){
        RoomMemberResponse roomMemberResponse = userService.getMyRoom(userId);
        return ResponseEntity.ok(roomMemberResponse);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(
            @SessionAttribute(name = GoogleAuthController.SESSION_USER_ID) Long userId, HttpSession httpSession){
        userService.deleteUser(userId);
        httpSession.invalidate();
        return ResponseEntity.noContent().build();
    }
}
