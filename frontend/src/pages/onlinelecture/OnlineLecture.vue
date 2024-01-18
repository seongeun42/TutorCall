<script setup lang="ts">
import OnlineLectureTitleBar from "@/pages/onlinelecture/components/OnlineLectureTitleBar.vue";
import OnlineLectureUtilBtn from "@/pages/onlinelecture/components/OnlineLectureUtilBtn.vue";
import OnlineLectureChatForm from "@/pages/onlinelecture/components/OnlineLectureChatForm.vue";
import OnlineLectureSystemMsg from "@/pages/onlinelecture/components/OnlineLectureSystemMsg.vue"
import OnlineLectureUserProfile from "@/pages/onlinelecture/components/OnlineLectureUserProfile.vue"
import OnlineLectureChatInput from "@/pages/onlinelecture/components/OnlineLectureChatInput.vue"
import OnlineLectureSettingBtn from "@/pages/onlinelecture/components/OnlineLectureSettingBtn.vue";

import { ref } from 'vue';
import type { Ref } from 'vue';

const videoSideView: Ref<boolean> = ref(true);
const chatSideView: Ref<boolean> = ref(true);

function handleVideoView(video: boolean){
    videoSideView.value = video;
}

function handleChatView(chat: boolean){
    chatSideView.value = chat;
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*
* 이하부터 테스트를 위한 더미 코드기 때문에 나중에 지워야함
*
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

interface userInfo { 
    imgUrl: string,
    nickName: string,
    isHost: boolean,
    mikeStatus: boolean
}

interface videoSettings { 
    onMike: boolean,
    onVideo: boolean
}

interface chatForm {
    isMychat: boolean,
    message: string
}

interface btnProps { 
    btnName1: string,
    btnName2: string
}

const dummydata: userInfo = {
    imgUrl: "",
    nickName: "투블럭의 여집합",
    isHost: true,
    mikeStatus: true
}

const dummydata2: userInfo = {
    imgUrl: "",
    nickName: "파트너 피카츄",
    isHost: false,
    mikeStatus: false
}

const dummydata3: videoSettings = {
    onMike: false,
    onVideo: false
}

const dummydata4: chatForm ={
    isMychat: true,
    message: "테스트 말풍선"
}

const dummydata5: chatForm ={
    isMychat: false,
    message: "테스트 말풍선"
}

</script>
<template>
    <!--메인 틀-->
    <div class="mx-36">
        <OnlineLectureTitleBar/>
        <hr>
        <!--스티커바-->
        <div class="grid grid-cols-9 gap-4">
            <div class="col-start-1 col-span-2">
                <OnlineLectureUtilBtn @update:btnClicked="handleVideoView" btnName1="메인화면" btnName2="메모장"/>
            </div>
            <div class="col-start-8 col-span-2">
                <OnlineLectureUtilBtn
                class= "col-start-8 col-span-2"
                @update:btnClicked="handleChatView"
                btnName1="채팅" btnName2="참가자"/>
            </div>
        </div>
        <div class="grid grid-cols-9 gap-4 max-h-[500px]">
                <div class="col-span-7 grid grid-rows-9 max-h-[500px]">
                    <div class="row-span-8">
                        <!-- 화면 컨텐츠 변환-->
                        <img v-if="videoSideView" class="" src="../../img/video.png" alt="">
                        <img v-else class="h-full w-full" src="../../img/whitepage.jpg" alt="">
                    </div>
                    <div class="row-span-1">
                        <OnlineLectureSettingBtn :settings="dummydata3"/>
                    </div>
                </div>
            <div class="col-span-2">
                <div class="border-4 grid grid-rows-8 max-h-[500px]">
                    <div v-if ="chatSideView">
                        <div class="row-start-1 row-end-6 row-span-5 overflow-auto max-h-[430px] min-h-[430px]">
                            <OnlineLectureChatForm :data = "dummydata4"/>
                            <OnlineLectureChatForm :data = "dummydata5"/>
                            <OnlineLectureSystemMsg message="시스템 메세지"/>
                        </div>
                        <div class="row-start-7 row-span-1">
                            <OnlineLectureChatInput/>
                        </div>
                    </div>
                    <div v-else>
                        <OnlineLectureUserProfile
                        :info="dummydata"/>
                        <OnlineLectureUserProfile
                        :info="dummydata2"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
</style>

