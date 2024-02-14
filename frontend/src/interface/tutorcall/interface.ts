import type { tagInfo } from "../common/interface";

export interface alertForm{
    nickname: string,
    title: string,
    content: string,
    hide: boolean,
    tag: tagInfo
}