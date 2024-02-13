package com.potato.TutorCall.tutorcall.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class MatchResponseMessage {

    private boolean matched;
    private String sessionId;

    @Builder
    public MatchResponseMessage(boolean matched, String sessionId) {
        this.matched = matched;
        this.sessionId = sessionId;
    }

}
