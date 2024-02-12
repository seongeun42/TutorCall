import type { review, user } from "../common/interface";

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