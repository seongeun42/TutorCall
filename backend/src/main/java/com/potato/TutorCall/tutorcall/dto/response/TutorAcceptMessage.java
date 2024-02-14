package com.potato.TutorCall.tutorcall.dto.response;

import com.potato.TutorCall.tutor.dto.TutorDetailDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TutorAcceptMessage {

    private UUID reqId;
    private UUID resId;
    private TutorDetailDto tutor;
    private LocalDateTime created;

    @Builder
    public TutorAcceptMessage(UUID reqId, UUID resId, TutorDetailDto tutor) {
        this.reqId = reqId;
        this.resId = resId;
        this.tutor = tutor;
        this.created = LocalDateTime.now();
    }

}
