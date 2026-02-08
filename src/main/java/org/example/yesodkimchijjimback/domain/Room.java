package org.example.yesodkimchijjimback.domain;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.example.yesodkimchijjimback.dto.room.RoomRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false, unique = true)
    private String roomCode;

    @Column(nullable = false)
    private int maxPeople;

    @Column(nullable = false)
    private int currentPeople;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomMember> members = new ArrayList<>();


    public static Room fromEntity(RoomRequest roomRequest){
        String randomCode;
        Random random = new Random();

        randomCode = random.ints(48, 91)
                .filter(i -> (i <= 57 || i >= 65))
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return builder()
                .roomName(roomRequest.getRoomName())
                .roomCode(randomCode)
                .maxPeople(roomRequest.getMaxPeople())
                .build();
    }

    public void update(RoomRequest roomRequest){
        this.roomName = roomRequest.getRoomName();
        this.maxPeople = roomRequest.getMaxPeople();
    }
}