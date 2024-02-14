package com.potato.TutorCall.tutorcall.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutorcall.domain.RequestCall;
import com.potato.TutorCall.tutorcall.domain.ResponseCall;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.tutorcall.repository.RequestCallRepository;
import com.potato.TutorCall.tutorcall.repository.ResponseCallRepository;
import com.potato.TutorCall.tutorcall.repository.TutorCallRepository;
import com.potato.TutorCall.user.domain.User;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorCallService {

  private final TutorCallRepository tutorcallRepository;
  private final RequestCallRepository requestCallRepository;
  private final ResponseCallRepository responseCallRepository;

  @Transactional(readOnly = true)
  public TutorCall findById(Long id) {
    return tutorcallRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public List<TutorCall> findUserTutorCalls(User currentUser) {
    return tutorcallRepository.findByUserOrderByIdDesc(currentUser);
  }

  @Transactional(readOnly = true)
  public List<TutorCall> findAllByTutor(Tutor tutor) {
    return tutorcallRepository.findAllByTutorOrderByIdDesc(tutor);
  }

  public Long save(TutorCall tutorCall) {
    return tutorcallRepository.save(tutorCall).getId();
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

  public void tutorCallRequestSave(RequestCall requestCall) {
    requestCallRepository.save(requestCall);
  }

  public void tutorCallResponseSave(UUID requestId, ResponseCall responseCall) {
    updateAnswerCount(requestId);
    responseCallRepository.save(responseCall);
  }

  public RequestCall getRequestCall(UUID id) {
    return requestCallRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 요청입니다."));
  }

  public ResponseCall getResponseCall(UUID id) {
    return responseCallRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 요청입니다."));
  }

  private void updateAnswerCount(UUID id) {
    RequestCall requestCall = this.getRequestCall(id);
    requestCall.increaseTutorCount();
    requestCallRepository.save(requestCall);
  }

}
