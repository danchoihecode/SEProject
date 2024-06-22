package com.chattingweb.backend.services.messaging;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.chattingweb.backend.entities.conversation.Conversation;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.conversation.ConversationRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.conversation.Message;
import com.chattingweb.backend.entities.response.MessageDTO;
import com.chattingweb.backend.models.MessageData;
import com.chattingweb.backend.repository.conversation.MessageRepository;

@Service
public class MessageService {



	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	@Autowired
	private UserRepository userRepository;

	public void saveNewMessage(MessageData messageData) {
		Optional<Conversation> conversation = conversationRepository.findById(messageData.getConversationId());
		Optional<User> user = userRepository.findById(messageData.getSenderUserId());
		if(conversation.isPresent() && user.isPresent()) {
			Message message =  Message.builder()
					.sender(user.get())
					.conversation(conversation.get())
					.messageDate(Instant.now())
					.messageContent(messageData.getMessageContent())
					.build();
			messageRepository.save(message);
		}

	}

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
	public void updateMessage(Long messageId, String content){
		Optional<Message> message=messageRepository.findById(messageId);
		if(message.isPresent()){
			Message mess=message.get();
			mess.setMessageContent(content);
			messageRepository.save(mess);
		} else {
			throw new IllegalArgumentException("Message not found for ID: " + messageId);
		}
	}
	public void deleteMessage(Long messageId){
		messageRepository.deleteById(messageId);
	}
}
