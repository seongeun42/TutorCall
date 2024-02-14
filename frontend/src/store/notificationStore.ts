import { defineStore } from 'pinia'
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'
import type { UUID } from 'crypto'
import * as api from '@/api/tutor/tutor'
import type { tagResponse } from '@/interface/common/interface'
import type { problem, acceptTutor, matchedResponse } from '@/interface/tutorcall/interface'
import type { AxiosResponse } from 'axios'

export const useNotificationStore = defineStore('notification', {
  state: () => ({
    // 연결된 소켓
    stompClient: null as Stomp.Client | null,
    // 새로운 알림 여부
    existNew: false,
    // 선생님이 수락 후 학생 응답 기다림 여부
    waitingMatching: false,
    // 개인 알림 구독
    userSub: null as Stomp.Subscription | null,
    // 선생님 tag 구독
    tagSubs: [] as Stomp.Subscription[],
    // 선생님이 받은 문제 요청
    problems: [] as problem[],
    // 학생이 받은 문제 수락
    accepts: [] as acceptTutor[],
    // 매칭된 강의룸 세션
    roomSessionId: null as String | null,
    // 현재 요청한 tutorcall uuid
    requestUuid: null as UUID | null,
    // 현재 수락한 response uuid
    responseUuid: null as UUID | null
  }),
  actions: {
    socketConnect(userId: number) {
      // 소켓 연결
      const socket: WebSocket = new SockJS(`${import.meta.env.VITE_VUE_API_URL}/ws-hello`) // WebSocket 엔드포인트 경로 설정
      this.stompClient = Stomp.over(socket)
      this.stompClient.connect(
        {},
        () => {
          // 유저 구독
          if (this.stompClient) {
            this.userSub = this.stompClient.subscribe('/sub/user/' + userId, (response: any) => {
              this.existNew = true
            })
          }
        },
        onError
      )
    },
    socketReconnect(userId: number, isTutor: boolean, isActiveCall: boolean) {
      const socket: WebSocket = new SockJS(`${import.meta.env.VITE_VUE_API_URL}/ws-hello`) // WebSocket 엔드포인트 경로 설정
      this.stompClient = Stomp.over(socket)
      this.stompClient.connect(
        {},
        () => {
          // 유저 구독
          if (this.stompClient) {
            this.userSub = this.stompClient.subscribe('/sub/user/' + userId, (response: any) => {
              this.existNew = true
            })
            if (isTutor && isActiveCall) {
              this.tagSubs = []
              this.tagSubscribe()
            }
            // 학생이고, 튜터콜 요청 중이라면 재연결
            if (!isTutor && this.requestUuid != null) {
              this.callSubscribe(this.requestUuid)
            }
            if (this.responseUuid != null) {
              this.answerSubscribe(this.responseUuid, this.requestUuid)
            }
          }
        },
        onError
      )
    },
    socketDisconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect(() => {
          console.log('WebSocket 연결 해제')
        })
      }
    },
    async tagSubscribe() {
      // 선생님의 태그 구독
      if (this.stompClient) {
        await api.getTutorTags().then((response: AxiosResponse<tagResponse>) => {
          if (this.stompClient) {
            const tags = response.data
            for (let i = 0; i < tags.length; i++) {
              this.tagSubs.push(
                this.stompClient.subscribe('/sub/tag/' + tags[i].id, (response: any) => {
                  // 문제 요청을 문제함에 저장
                  this.problems.unshift(JSON.parse(response.body))
                  setTimeout(
                    () => {
                      this.problems.pop()
                    },
                    1000 * 60 * 5 + 1000
                  )
                })
              )
            }
          }
        })
        // for (let i = 0; i < tags.length; i++) {
        //     this.tagSubs.push(this.stompClient.subscribe("/sub/tag/" + tags[i].id, (response: any) => {
        //         // 문제 요청을 문제함에 저장
        //         this.problems.push(JSON.parse(response.body).message)
        //     }))
        // }
      }
    },
    callSubscribe(reqUuid: UUID) {
      // 튜터콜 요청 후 수락 구독
      if (this.stompClient) {
        this.requestUuid = reqUuid
        let callSub: Stomp.Subscription | null = this.stompClient.subscribe(
          '/sub/tutorcall/' + reqUuid,
          (response: any) => {
            // 수락을 수락함에 저장
            const objectsize = 175
            let positionX = Math.floor(Math.random() * (500 - objectsize + 1)) + 100
            let positionY = Math.floor(Math.random() * (mainWidth * 0.35)) + mainWidth * 0.325

            while (checkoverlapping(objectsize, positionX, positionY, this.accepts)) {
              positionX = Math.floor(Math.random() * (500 - objectsize + 1)) + 100
              positionY = Math.floor(Math.random() * (mainWidth - objectsize - 200 + 1)) + 200
            }

            const data = {
              id: this.accepts.length,
              delay: Math.floor(Math.random() * 100) + 1,
              size: objectsize,
              objectsize: objectsize,
              positionX: positionX,
              positionY: positionY,
              data: JSON.parse(response.body)
            }

            this.accepts.push(data)

            setTimeout(() => {
              this.requestUuid = null
              this.accepts.pop()
            }, 1000 * 60)
          }
        )
        setTimeout(
          () => {
            callSub?.unsubscribe()
            callSub = null
            this.requestUuid = null
            this.accepts = []
          },
          1000 * 60 * 5 + 10000
        )
      }
    },
    answerSubscribe(resUuid: UUID, reqUuid: UUID | null) {
      // 수락 후 매칭 구독
      if (this.stompClient) {
        this.waitingMatching = true
        this.requestUuid = reqUuid
        let answerSub: Stomp.Subscription | null = this.stompClient.subscribe(
          '/sub/tutorcall/answer/' + resUuid,
          (response: any) => {
            const data: matchedResponse = JSON.parse(response.body)
            if (data.matched) {
              // 매칭 성공
              this.roomSessionId = data.sessionId
              for (let i = 0; i < this.problems.length; i++) {
                if (this.problems[i].id === reqUuid) {
                  this.problems[i].matched = 2
                  break
                }
              }
              this.accepts = []
            } else {
              // 매칭 거절
              for (let i = 0; i < this.problems.length; i++) {
                if (this.problems[i].id === reqUuid) {
                  this.problems[i].matched = 3
                  break
                }
              }
            }
            this.requestUuid = null
            this.waitingMatching = false
          }
        )
        setTimeout(
          () => {
            for (let i = 0; i < this.problems.length; i++) {
              if (this.problems[i].id === reqUuid) {
                this.problems[i].matched = 3
                break
              }
            }
            answerSub?.unsubscribe()
            answerSub = null
            this.waitingMatching = false
            this.requestUuid = null
            this.responseUuid = null
            this.accepts = []
          },
          1000 * 60 * 1 + 1000
        )
      }
    },
    unsubscribe(sub: Stomp.Subscription) {
      if (sub) {
        sub.unsubscribe()
      }
    },
    sendMessage(url: string, message: object) {
      if (this.stompClient) {
        this.stompClient.send('/pub/' + url, {}, JSON.stringify(message))
      }
    },
    clearTagSubs() {
      this.tagSubs = []
    },
    clear() {
      ;(this.stompClient = null),
        (this.existNew = false),
        (this.waitingMatching = false),
        (this.userSub = null),
        (this.tagSubs = []),
        (this.problems = []),
        (this.accepts = []),
        (this.roomSessionId = null),
        (this.requestUuid = null),
        (this.responseUuid = null)
    }
  },
  persist: {
    storage: sessionStorage
  }
})

const mainContent = document.querySelector('#mainComponent')
const mainWidth = mainContent?.clientWidth ?? 1960
const mainHeight = mainContent?.clientHeight ?? 1000

function checkoverlapping(
  objectsize: number,
  positionX: number,
  positionY: number,
  arr: acceptTutor[]
): boolean {
  const length = arr.length

  for (let i = 0; i < length - 1; i++) {
    const r = Math.sqrt(
      Math.pow(Math.abs(arr[i].positionX - positionX), 2) +
        Math.pow(Math.abs(arr[i].positionY - positionY), 2)
    )
    if (r < 175) return true
  }
  return false
}

function onError(error: any) {
  console.error('WebSocket 연결 에러:', error)
}
