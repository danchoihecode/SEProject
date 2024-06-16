package com.chattingweb.backend.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.conversation.Message;
import com.chattingweb.backend.entities.response.MessageDTO;
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

}
