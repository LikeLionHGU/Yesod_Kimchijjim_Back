package org.example.yesodkimchijjimback.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.yesodkimchijjimback.domain.RoomMember;
import org.example.yesodkimchijjimback.domain.User;
import org.example.yesodkimchijjimback.dto.UserRe.UserRequest;
import org.example.yesodkimchijjimback.repository.RoomMemberRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;

    public void joinRoom(UserRequest userRequest, User user) {

        Room room = roomRepository.findByCode(userRequest.getCode())
                .orElseThrow(() -> new IllegalArgumentException("찾는 방이 없습니다."));


        long currentCount = roomMemberRepository.countByMember(room);
        long nowCount = currentCount + 1;

        RoomMember roomMember = RoomMember.builder()
                .room(room)
                .user(user)
                .name(userRequest.getName())
                .roomUserId(nowCount)
                .build();

        roomMemberRepository.save(roomMember);
    }
}

