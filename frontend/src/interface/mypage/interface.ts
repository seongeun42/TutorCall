import type { review, tagInfo, user } from "../common/interface";

export interface tutorCallHistory{
    tutoringId: number,
    tutor: user,
    user: user,
    problem: string,
    replayVideo: string,
    createAt: string,
    liveUrl: string,
    liveState: string,
    price: number,
    review: review
}

export interface tutorcallResponse{
    content: tutorCallHistory[]
}
export interface lectureResponse{
    content: lectureHistory[]
}
export interface lectureHistory{

    lectureId: number,
    tutor: user,
    promotionTitle: string,
    promotionState: boolean,
    lecture_state: boolean,
    tag: tagInfo,
    lectureEntAt: string,
    review: boolean,

}

export interface modifynickname{
    nickname: string
}

export interface modifypassword{
    password: string,
    newPassword: string
}

export interface modifynotify{
    notificationOption: string
}

export interface modifytags{
    tags: number[]
}

export interface modifyIntro{
    introduction: string
}