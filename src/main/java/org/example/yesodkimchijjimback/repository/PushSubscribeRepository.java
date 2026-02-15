package org.example.yesodkimchijjimback.repository;

import org.example.yesodkimchijjimback.domain.PushSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PushSubscribeRepository extends JpaRepository<PushSubscribe, Long> {
    Optional<PushSubscribe> findByEndpoint(String endpoint);

    List<PushSubscribe> findAllByUserIdIn(List<Long> memberIds);
}
