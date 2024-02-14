import type { TagInfo } from "../common/interface";

export interface AlertForm{
    nickname: string,
    title: string,
    content: string,
    hide: boolean,
    tag: TagInfo
}