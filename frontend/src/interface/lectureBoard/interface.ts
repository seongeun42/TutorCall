import type { CommonResponse, TagInfo, User } from '../common/interface'

export interface LectureResponse {
  content: Lecture[]
}

export interface Lecture {
  id: number
  title: string
  content: string
  tag: TagInfo
  tutor: User
  promotionState: boolean
  createdAt: string
}

export interface DetailLecture extends Lecture{

    promotionTitle: string,
    promotionContent: string,
    tutor: DetailTutor,
    maxParticipants: number,
    participants: number,
    promotionDue: string,
    lectureStartAt: string,
    lectureEndAt: string,
    participated: boolean,
    price: number,
    promotionCreatedAt:string,
}

export interface DetailTutor extends User {
  mannerRate: number
  communicationRate: number
  professionalismRate: number
  introduction: string
  reliablity: number
}

export interface RegistResponse extends CommonResponse {
  lectureId: number
}

export interface DeleteResponse extends CommonResponse {
  id: number
}

export interface Promotion {
  promotionTitle: string
  promotionContent: string
  maxParticipant: number
  promotionDue: string
  price: number
  tagId: number
}