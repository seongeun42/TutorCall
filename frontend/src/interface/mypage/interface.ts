import type { Review, TagInfo, User } from "../common/interface";

export interface TutorCallHistory{
    tutoringId: number,
    tutor: User,
    user: User,
    problem: string,
    replayVideo: string,
    createAt: string,
    liveUrl: string,
    liveState: string,
    price: number,
    review: Review
}

export interface TutorcallResponse{
    content: TutorCallHistory[]
}
export interface LectureResponse{
    content: LectureHistory[]
}
export interface LectureHistory{

    lectureId: number,
    tutor: User,
    promotionTitle: string,
    promotionState: boolean,
    lecture_state: boolean,
    tag: TagInfo,
    lectureEntAt: string,
    review: boolean,

}

export interface Modifynickname{
    nickname: string
}

export interface Modifypassword{
    password: string,
    newPassword: string
}

export interface Modifynotify{
    notificationOption: string
}

export interface Modifytags{
    tags: number[]
}

export interface ModifyIntro{
    introduction: string
}