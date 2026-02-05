package org.example.yesodkimchijjimback.dto.room;

import lombok.Data;

@Data
public class RoomRequest {
    private String roomName;
    // private String roomCode;
    private int maxPeople;
}
