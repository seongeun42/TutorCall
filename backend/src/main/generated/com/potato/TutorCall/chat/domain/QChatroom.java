package com.potato.TutorCall.chat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatroom is a Querydsl query type for Chatroom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatroom extends EntityPathBase<Chatroom> {

    private static final long serialVersionUID = 1478276795L;

    public static final QChatroom chatroom = new QChatroom("chatroom");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ChatMessage, QChatMessage> messageList = this.<ChatMessage, QChatMessage>createList("messageList", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<ChatParticipant, QChatParticipant> participantList = this.<ChatParticipant, QChatParticipant>createList("participantList", ChatParticipant.class, QChatParticipant.class, PathInits.DIRECT2);

    public final EnumPath<com.potato.TutorCall.chat.domain.enums.ChatroomType> type = createEnum("type", com.potato.TutorCall.chat.domain.enums.ChatroomType.class);

    public QChatroom(String variable) {
        super(Chatroom.class, forVariable(variable));
    }

    public QChatroom(Path<? extends Chatroom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatroom(PathMetadata metadata) {
        super(Chatroom.class, metadata);
    }

}

