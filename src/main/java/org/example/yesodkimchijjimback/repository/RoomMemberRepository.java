package org.example.yesodkimchijjimback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yesodkimchijjimback.domain.RoomMember;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {
    long countByMember(Room room);

}
