import { defineStore } from 'pinia'
import type { Ref } from 'vue';
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'
import type{ AxiosResponse } from 'axios';
import type { chatMessage, chatroomInfo } from '@/interface/chat/interface';
import type { userSimple } from '@/interface/common/interface';

// chatting에 사용되는 socket

export const useChattingStore = defineStore('chatting', {
    state: () => ({
        // 연결된 소켓
        stompClient: null as Stomp.Client | null,
        // 사용자가 현재 참여 중인 채팅방들의 id 목록
        chatroomList: [] as Object[],
        // 현재 메시지를 주고 받는 채팅방의 메시지들
        chatMessages: [] as Object[],
        // 사용자가 새롭게 참여하게 된 방에 대한 id
        newChatroom: null as Stomp.Subscription | null,
        // 현재 보고 있는 방 타입(1:1 또는 단체)
        roomType: "PERSONAL",
        subInfo: new Set(),
    }),
    actions: {
        // 소켓을 연결
        connectSocket(userId: number) {
            const socket: WebSocket = new SockJS(`${import.meta.env.VITE_VUE_API_URL}/ws-hello`); // WebSocket 엔드포인트 경로 설정
            this.stompClient = Stomp.over(socket);
            this.stompClient.connect({}, () => {
                if (this.stompClient) {
                    // 유저가 참여하는 새로운 채팅방이 생겼을 경우를 위한 구독
                    this.stompClient.subscribe("/sub/chatroom/created/" + userId, (response: any) => {
                        this.chatroomList.push(response.body);
                    })
                    // 사용자가 현재 참여하고 있는 채팅방의 정보들
                    this.stompClient.subscribe("/sub/chatroom/" + userId + "/PERSONAL", (response: any) => {
                        this.chatroomList = JSON.parse(response.body);
                    })
                    this.stompClient.subscribe("/sub/chatroom/" + userId + "/GROUP", (response: any) => {
                        this.chatroomList = JSON.parse(response.body);
                    })
                }
            }, onError);
        },
        // 메시지를 전송
        sendMessage(url: string, message: object) {
            if (this.stompClient) {
                this.stompClient.send('/pub/' + url, {}, JSON.stringify(message))
            }
        },
        // 채팅방에 참여한 사람들의 id를 가져옴
        getParticipants(roomId: string, participants: Ref<Object[]>) {
            if(this.stompClient) {
                    this.stompClient.subscribe("/sub/chatroom/users/" + roomId, (response: any) => {
                        participants.value = JSON.parse(response.body);
                    })
            }
        },
        // 기존 채팅방에 있던 메시지들을 한 번에 불러옴
        getAllChatsInRoom(roomId: string){
            if(this.stompClient) {
                if(!this.subInfo.has("/sub/chat/" + roomId)) {
                    this.subInfo.add("/sub/chat/" + roomId)
                    this.stompClient.subscribe("/sub/chat/" + roomId, (response: any) => {
                        this.chatMessages = JSON.parse(response.body)
                    })
                }
            }
        },
        // 채팅방에 새로운 메시지가 들어오면 이를 받음
        getNewMessage(roomId: string, chats: Ref<Object[]>) {
            if(this.stompClient) {
                if(!this.subInfo.has("/sub/chat/new/" + roomId)) {
                    this.subInfo.add("/sub/chat/new/" + roomId)
                    this.stompClient.subscribe("/sub/chat/new/" + roomId, (response: any) => {
                        this.chatMessages.push(JSON.parse(response.body));
                    })
                }
            }
        },
        clearParticipantingRoom() {
            this.participatingRoom = null
        },
        clearChatMessages() {
            this.chatMessages = []
        },
        clear() {
            this.stompClient = null,
            this.chatroomList = [],
            this.participatingRoom = null,
            this.chatMessages =  []
        },
    },
})

function onError(error: any) {
    console.error('채팅용 소켓 에러:', error);
}
