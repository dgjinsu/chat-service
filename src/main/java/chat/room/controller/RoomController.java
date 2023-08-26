package chat.room.controller;

import chat.chat.dto.ChatDto;
import chat.chat.service.ChatService;
import chat.member.entity.Member;
import chat.room.dto.MyRoomDto;
import chat.room.dto.RoomFormDto;
import chat.room.service.RoomService;
import chat.room.entity.Room;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomController {
    private final RoomService roomService;
    private final ChatService chatService;

    /**
     * 채팅방 입장
     */
    @GetMapping("/{roomId}")
    public String joinRoom(@PathVariable(required = false) Long roomId, Model model) {
        System.out.println("/roomId 진입");
        List<ChatDto> chatList = chatService.findAllChatByRoomId(roomId);

        model.addAttribute("roomId", roomId);
        model.addAttribute("chatList", chatList);
        return "chat/roomIn";
    }

    /**
     * 채팅방 등록
     */
    @PostMapping("/room")
    @ResponseBody
    public ResponseEntity<?> createRoom(RoomFormDto form) {
        roomService.createRoom(form);
        return ResponseEntity.ok(null);
    }

    /**
     * 채팅방 리스트 보기
     */
    @GetMapping("/roomList")
    public String roomList(Model model) {
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "chat/roomList";
    }

    /**
     * 내가 들어간 채팅방 리스트
     */
    @GetMapping("/my-chat-list")
    public String myChatList(HttpSession session) {
        String loginId = (String) session.getAttribute("user");
        List<MyRoomDto> myRoomDtoList = roomService.findMyRoom(loginId);
        return "ok";
    }

    /**
     * 방만들기 폼
     */
    @GetMapping("/roomForm")
    public String roomForm() {
        return "chat/roomForm";
    }
}
