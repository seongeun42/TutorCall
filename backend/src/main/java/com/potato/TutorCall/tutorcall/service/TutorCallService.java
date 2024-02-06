package com.potato.TutorCall.tutorcall.service;

import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.tutorcall.repository.TutorCallRepository;
import com.potato.TutorCall.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorCallService {

  private final TutorCallRepository tutorcallRepository;

  @Transactional(readOnly = true)
  public TutorCall findById(Long id) {
    return tutorcallRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public List<TutorCall> findUserTutorCalls(User currentUser) {
    return tutorcallRepository.findByUserOrderByIdDesc(currentUser);
  }

  public void startTutorCallLive(Long tutorCallId, String sessionId) {
    TutorCall tutorCall = this.findById(tutorCallId);
    tutorCall.changeLiveState(true);
    tutorCall.setLiveUrl(sessionId);
  }

  public void endTutorCallLive(Long tutorCallId) {
    TutorCall tutorCall = this.findById(tutorCallId);
    tutorCall.changeLiveState(false);
    tutorCall.setLiveUrl(null);
  }
}
