package org.example.yesodkimchijjimback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.yesodkimchijjimback.dto.room.RoomRequest;
import org.example.yesodkimchijjimback.dto.room.RoomResponse;
import org.example.yesodkimchijjimback.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomRequest request){
        RoomResponse roomResponse = roomService.createRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomResponse);
    }

    @GetMapping("/{roomCode}")
    public ResponseEntity<RoomResponse> read(@PathVariable String roomCode){
        RoomResponse roomResponse = roomService.readRoom(roomCode);
        return ResponseEntity.ok(roomResponse);
    }

    @PutMapping("/{roomCode}")
    public ResponseEntity<RoomResponse> update(@PathVariable String roomCode, @RequestBody RoomRequest roomRequest){
        RoomResponse roomResponse = roomService.updateRoom(roomRequest, roomCode);
        return ResponseEntity.ok(roomResponse);
    }

    @DeleteMapping("/{roomCode}")
    public ResponseEntity<RoomResponse> delete(@PathVariable String roomCode){
        roomService.deleteRoom(roomCode);
        return ResponseEntity.noContent().build();
    }
}
