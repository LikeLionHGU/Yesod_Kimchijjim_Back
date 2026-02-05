package org.example.yesodkimchijjimback.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.yesodkimchijjimback.domain.Room;
import org.example.yesodkimchijjimback.dto.room.RoomRequest;
import org.example.yesodkimchijjimback.dto.room.RoomResponse;
import org.example.yesodkimchijjimback.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public RoomResponse createRoom(RoomRequest roomRequest){
        Room room = roomRepository.save(Room.fromEntity(roomRequest));
        return RoomResponse.fromResponse(room);
    }

    @Transactional
    public RoomResponse readRoom(String roomCode){
        Room room = roomRepository.findByRoomCode(roomCode).orElseThrow();
        return RoomResponse.fromResponse(room);
    }

    @Transactional
    public RoomResponse updateRoom(RoomRequest roomRequest, String roomCode){
        Room room = roomRepository.findByRoomCode(roomCode).orElseThrow();
        room.update(roomRequest);
        return RoomResponse.fromResponse(room);
    }

    @Transactional
    public void deleteRoom(String roomCode){
        roomRepository.findByRoomCode(roomCode).orElseThrow();
        roomRepository.deleteByRoomCode(roomCode);
    }
}
