package chat.room.dto;

import chat.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyRoomDto {
    private Long roomId;
    private String name;

    public static MyRoomDto from(Room room) {
        MyRoomDto myRoomDto = new MyRoomDto();
        myRoomDto.setRoomId(room.getId());
        myRoomDto.setName(room.getName());
        return myRoomDto;
    }
}
