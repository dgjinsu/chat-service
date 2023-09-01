package chat.chat.service;

import chat.chat.dto.ChatDto;
import chat.chat.entity.Chat;
import chat.chat.repository.ChatRepository;
import chat.member.entity.Member;
import chat.member.repository.MemberRepository;
import chat.room.entity.Room;
import chat.room.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    public ChatDto createChat(Long roomId, String message, String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new EntityNotFoundException());

        Room room = roomRepository.findById(roomId).orElseThrow();  //방 찾기 -> 없는 방일 경우 여기서 예외처리
        Chat chatEntity = Chat.builder()
                .room(room)
                .message(message)
                .member(member)
                .build();
        chatRepository.save(chatEntity);

        return ChatDto.builder()
                .roomId(chatEntity.getRoom().getId())
                .sender(member.getName())
                .message(chatEntity.getMessage())
                .build();
    }

    public List<ChatDto> findAllChatByRoomId(Long roomId) {
        List<Chat> chatHistory = chatRepository.findAllByRoomId(roomId);
        List<ChatDto> chatDtoList = new ArrayList<>();
        for (Chat chat : chatHistory) {
            ChatDto chatDto = ChatDto.builder()
                    .message(chat.getMessage())
                    .sender(chat.getMember().getName())
                    .sendDate(chat.getSendDate())
                    .build();
            chatDtoList.add(chatDto);
        }
        return chatDtoList;
    }



}
