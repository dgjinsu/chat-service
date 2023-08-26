package chat.room.repository;

import chat.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.")
    List<Room> findByMemberId(String loginId);
}
