package org.example.yesodkimchijjimback.dto.RoomMemberRe;

import lombok.Builder;
import lombok.Data;
import org.example.yesodkimchijjimback.domain.RoomMember;

@Data
@Builder
public class RoomMemberResponse {

    private String nickname;
    private boolean isHost;
    private String roomName;
    private Long roomId;

    public static RoomMemberResponse fromResponse(RoomMember roomMember){
        return  builder()
                .nickname(roomMember.getNickname())
                .isHost(roomMember.isHost())
                .roomName(roomMember.getRoom().getRoomName())
                .roomId(roomMember.getRoom().getId())
                .build();
    }
}
