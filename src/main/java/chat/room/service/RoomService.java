package chat.room.service;

import chat.room.dto.MyRoomDto;
import chat.room.dto.RoomFormDto;
import chat.room.entity.Room;
import chat.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;

    public Long createRoom(RoomFormDto form) {
        Room roomEntity = Room.builder()
                .name(form.getName())
                .build();
        return roomRepository.save(roomEntity).getId();
    }

    /**
     * 모든 채팅방 찾기
     */
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    public List<MyRoomDto> findMyRoom(String loginId) {
        roomRepository.findByMemberId(loginId);
    }
}
