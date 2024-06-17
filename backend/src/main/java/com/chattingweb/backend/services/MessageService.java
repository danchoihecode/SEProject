package com.chattingweb.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.conversation.Message;
import com.chattingweb.backend.entities.response.MessageDTO;
import com.chattingweb.backend.models.MessageData;
import com.chattingweb.backend.models.MessageType;
import com.chattingweb.backend.repository.conversation.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public List<MessageDTO> findAllByConversationId(UUID conversationId) {
		List<Message> messages = messageRepository.findAllByConversationId(conversationId);

		return messages.stream().map(message -> new MessageDTO(message.getId(), message.getMessageContent(),
				message.getMessageDate(), message.getSender().getId() // Assuming sender has an ID
		)).collect(Collectors.toList());
	}
	
	public List<MessageData> getConversationMessages(UUID conversationId) {
		List<MessageData> messageDataList = new ArrayList<>();
		for (Message message : messageRepository.findByConversationIdOrderByMessageDateAsc(conversationId)) {
			MessageData messageData = MessageData.builder()
//			          .messageType(MessageType.CONNECT)
			          .messageContent(message.getMessageContent())
			          .senderUserId(message.getSender().getId())
			          .conversationId(conversationId)
//			          .sessionId(message.getSessionId())
			          .build();
			messageDataList.add(messageData);
		}
	    return messageDataList;
	  }
}
