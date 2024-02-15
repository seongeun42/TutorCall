// 채팅방에서 주고 받은 개별 메시지
export interface chatMessage {
    senderId: number,
    message: String,
    createdAt: Date
}

export interface chatroomInfo {
    id: String,
    name: String,
    chatroomType: any
}