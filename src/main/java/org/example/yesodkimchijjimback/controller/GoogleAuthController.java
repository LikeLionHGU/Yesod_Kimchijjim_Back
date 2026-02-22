package org.example.yesodkimchijjimback.controller;


import org.example.yesodkimchijjimback.domain.RoomMember;
import org.example.yesodkimchijjimback.domain.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.yesodkimchijjimback.dto.login.GoogleLoginRequest;
import org.example.yesodkimchijjimback.dto.login.LoginResponse;
import org.example.yesodkimchijjimback.repository.RoomMemberRepository;
import org.example.yesodkimchijjimback.service.GoogleAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class GoogleAuthController {
    private final GoogleAuthService googleAuthService;
    private final RoomMemberRepository roomMemberRepository;

    public static final String SESSION_USER_ID = "LOGIN_USER_ID";

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> googleLogin(
            @Valid @RequestBody GoogleLoginRequest request,
            HttpSession session
    ) {
        User user = googleAuthService.loginWithGoogleIdToken(request.getIdToken());
        //구글이 준 idToken이 진짜인지 확인.

        //  세션에 로그인 정보 저장
        session.setAttribute(SESSION_USER_ID, user.getId());

        List<RoomMember> roomMembers = roomMemberRepository.findAllByUser(user);

        String roomCode = roomMembers.isEmpty() ? null : roomMembers.get(0).getRoom().getRoomCode();

        return ResponseEntity.ok(
                new LoginResponse(user.getId(), user.getEmail(), user.getName(), roomCode)
        );
    }

    //현재 로그인 상태
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object userId = session.getAttribute(SESSION_USER_ID);
        if (userId == null) {
            return ResponseEntity.status(401).body("NOT_LOGGED_IN");
        }
        return ResponseEntity.ok(userId);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();//세션 자체를 폐기해버림
        return ResponseEntity.ok().build();
    }
}
