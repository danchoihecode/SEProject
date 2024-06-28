package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.ConversationMember;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import com.chattingweb.backend.models.ConversationListItem;
import com.chattingweb.backend.entities.user.User;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import org.springframework.data.repository.query.Param;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, ConversationMemberId> {
  @Query( "select new com.chattingweb.backend.models.ConversationListItem(u.avatar, u.nickName,c.isGroup, A.conversationId,A.hasRead )" +
          "from (select cm.id.conversationId as conversationId, cm.hasRead as hasRead " +
                    "from ConversationMember as cm " +
                    "where cm.id.userId = ?1) as A " +
          "inner join ConversationMember as cm on cm.id.conversationId = A.conversationId "+
          "inner join User as u on cm.id.userId = u.id " +
          "inner join Conversation as c on A.conversationId = c.id " +
          "where c.isGroup = false  and cm.id.userId != ?1"
  )
  List<ConversationListItem> findAllConversationList(UUID userId);

  @Query( "select new com.chattingweb.backend.models.ConversationListItem(g.groupAvatar,g.groupName,c.isGroup,A.conversationId,A.hasRead) " +
          "from (" +
                  "select cm.id.conversationId as conversationId , cm.hasRead as hasRead " +
                  "from ConversationMember as cm " +
                  "where cm.id.userId = ?1" +
          ") as A " +
          "inner join Group as g on g.id = A.conversationId " +
          "inner join Conversation as c on A.conversationId = c.id " +
          "where c.isGroup = true"
  )
  List<ConversationListItem> findAllGroupList(UUID userId);


	@Query("SELECT cm.user FROM ConversationMember cm WHERE cm.id.conversationId = :conversationId")
    List<User> findUsersByConversationId(@Param("conversationId") UUID conversationId);

	List<User> findAllByConversationId(UUID conversationId);

	void deleteByConversationId(UUID conversationId);
  }