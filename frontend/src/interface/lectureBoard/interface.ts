import type { commonResponse, tagInfo, user } from "../common/interface";

export interface lectureResponse{
    content: lecture[]
}

export interface lecture{
    id: number,
    title: string,
    content: string,
    tag: tagInfo,
    tutor: user,
    promotionState: boolean,
    createdAt: string,
}

export interface detailLecture extends lecture{

    promotionTitle: string,
    promotionContent: string,
    tutor: detailTutor,
    maxParticipants: number,
    participants: number,
    promotionDue: string,
    lectureStartAt:string,
    lectureEndAt:string,
}

export interface detailTutor extends user{
    mannerRate: number,
    communicationRate: number,
    professionalismRate: number,
    introduction: string,
    reliablity: number
}

export interface registResponse extends commonResponse{
    lectureId: number
}

export interface deleteResponse extends commonResponse{
    id:number
}