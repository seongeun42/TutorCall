<script setup lang="ts">
import OnlineLectureTitleBar from '@/pages/onlinelecture/components/OnlineLectureTitleBar.vue'
import OnlineLectureUtilBtn from '@/pages/onlinelecture/components/OnlineLectureUtilBtn.vue'
import OnlineLectureChatForm from '@/pages/onlinelecture/components/OnlineLectureChatForm.vue'
import OnlineLectureUserProfile from '@/pages/onlinelecture/components/OnlineLectureUserProfile.vue'
import OnlineLectureChatInput from '@/pages/onlinelecture/components/OnlineLectureChatInput.vue'
import OnlineLectureSettingBtn from '@/pages/onlinelecture/components/OnlineLectureSettingBtn.vue'
import UserVideo from './components/UserVideo.vue'
import { ref, watch } from 'vue'
import type { Ref } from 'vue'
import { useVideoStore } from '@/store/videoStore'
const videoStore = useVideoStore()
watch(videoStore, () => {
  videoStore.sessionCamera?.on('signal', (event) => {
    videoStore.messages.push({
      userName: JSON.parse(event.from?.data).clientData,
      message: event.data
    })
    // console.log('잘 받고 있나?', event.data, JSON.parse(event.from?.data).clientData)
    // videoStore.messages.push({ userName: event.from, message: event.data })
  })
})

const chatSideView: Ref<boolean> = ref(true)

function handleChatView(chat: boolean) {
  chatSideView.value = chat
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * 이하부터 테스트를 위한 더미 코드기 때문에 나중에 지워야함
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

interface userInfo {
  imgUrl: string
  nickName: string
  isHost: boolean
  mikeStatus: boolean
}

const dummydata: userInfo = {
  imgUrl: '',
  nickName: '투블럭의 여집합',
  isHost: true,
  mikeStatus: true
}

const dummydata2: userInfo = {
  imgUrl: '',
  nickName: '파트너 피카츄',
  isHost: false,
  mikeStatus: false
}
</script>
<template>
  <!--메인 틀-->
  <div class="mx-36">
    <OnlineLectureTitleBar />
    <hr />

    <div class="mt-5 grid grid-cols-9 gap-4 max-h-[1000px]">
      <div class="col-span-7 grid grid-rows-9 max-h-[900px]">
        <div class="row-span-8 max-h-[800px] flex justify-center items-center">
          <UserVideo />
        </div>
        <div class="row-span-1">
          <OnlineLectureSettingBtn />
        </div>
      </div>
      <div class="col-span-2">
        <div class="max-h-[800px]">
          <OnlineLectureUtilBtn
            @update:btnClicked="handleChatView"
            btnName1="채팅"
            btnName2="참가자"
          />
          <div class="border-4 h-[700px]">
            <div v-if="chatSideView" class="flex-col">
              <div class="h-[600px]">
                <OnlineLectureChatForm />
                <OnlineLectureChatForm />
              </div>
              <div>
                <OnlineLectureChatInput />
              </div>
            </div>
            <div v-else>
              <OnlineLectureUserProfile :info="dummydata" />
              <OnlineLectureUserProfile :info="dummydata2" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
