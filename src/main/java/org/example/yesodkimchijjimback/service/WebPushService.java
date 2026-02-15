package org.example.yesodkimchijjimback.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.example.yesodkimchijjimback.domain.PushSubscribe;
import org.example.yesodkimchijjimback.domain.User;
import org.example.yesodkimchijjimback.dto.push.PushSubscribeRequest;
import org.example.yesodkimchijjimback.repository.PushSubscribeRepository;
import org.example.yesodkimchijjimback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebPushService {

    private final PushSubscribeRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Value("${vapid.public.key}") private String publicKey;
    @Value("${vapid.private.key}") private String privateKey;

    private PushService pushService;

    @PostConstruct
    public void init() throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        pushService = new PushService(publicKey, privateKey, "mailto:admin@example.com");
    }

    @Transactional
    public void subscribe(Long userId, PushSubscribeRequest pushSubscribeRequest){
        if(subscriptionRepository.findByEndpoint(pushSubscribeRequest.getEndpoint()).isPresent()){
            return;
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        PushSubscribe pushSubscribe = PushSubscribe.builder()
                .user(user)
                .endpoint(pushSubscribeRequest.getEndpoint())
                .p256dh(pushSubscribeRequest.getKeys().getP256dh())
                .auth(pushSubscribeRequest.getKeys().getAuth())
                .build();
        subscriptionRepository.save(pushSubscribe);
    }

    public void sendNotificationToMembers(List<Long> memberIds, String title, String body) {
        List<PushSubscribe> subscriptions = subscriptionRepository.findAllByUserIdIn(memberIds);

        for (PushSubscribe sub : subscriptions) {
            try {
                String payload = String.format("{\"title\":\"%s\", \"body\":\"%s\"}", title, body);

                Notification notification = new Notification(
                        sub.getEndpoint(),
                        sub.getP256dh(),
                        sub.getAuth(),
                        payload.getBytes()
                );

                pushService.send(notification);

            } catch (Exception e) {
                log.error("전송 실패 (삭제 예정): {}", e.getMessage());
                subscriptionRepository.delete(sub);
            }
        }
    }
}
