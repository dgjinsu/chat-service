package chat.chat.repository;

import chat.chat.entity.JoinChat;
import chat.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface JoinChatRepository extends JpaRepository<JoinChat, Long> {

    @Query("select j from JoinChat j join fetch j.room r where j.member.loginId = :loginId")
    List<JoinChat> findByMemberId(@Param("loginId") String loginId);

    @Query("select j from JoinChat j where j.member.loginId = :loginId and j.room.id = :roomId")
    Optional<JoinChat> findByMemberIdAndRoomId(@Param("loginId")String loginId, @Param("roomId")Long roomId);
}
