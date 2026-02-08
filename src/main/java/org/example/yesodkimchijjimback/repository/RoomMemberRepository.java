package org.example.yesodkimchijjimback.repository;

import org.example.yesodkimchijjimback.domain.Room;
import org.example.yesodkimchijjimback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yesodkimchijjimback.domain.RoomMember;

import java.util.Optional;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {
    Long countByRoom(Room room);
    boolean existsByRoomAndUser(Room room, User user);

    //RoomMember findByUser(User user);
    Optional<RoomMember> findByUser(User user);

    Optional<RoomMember> findByUserAndRoom(User user, Room room);
}
