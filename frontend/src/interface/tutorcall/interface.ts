import type { UUID } from "crypto";
import type { tagInfo, userSimple, tutorDetail } from "../common/interface";

export interface alertForm {
    nickname: string,
    title: string,
    content: string,
    hide: boolean,
    tag: tagInfo
}

// matched: 0(기본), 1(수락대기), 2(매칭), 3(거절)
export interface problem {
    id: UUID,
    title: string,
    content: string,
    tag: tagInfo,
    user: userSimple,
    created: string,
    matched: number
}

export interface accept {
    reqId: UUID,
    resId: UUID,
    tutor: tutorDetail,
    created: string
}

export interface ssesionId {
    ssesionId: string,
    message: string
}

export interface acceptTutor {
    id: number,
    delay: number,
    size: number,
    objectsize: number,
    positionX: number,
    positionY: number,
    data: accept
}

export interface matchedResponse {
    matched: boolean,
    sessionId: string
}