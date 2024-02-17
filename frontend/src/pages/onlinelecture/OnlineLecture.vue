<script setup lang="ts">
import OnlineLectureTitleBar from '@/pages/onlinelecture/components/OnlineLectureTitleBar.vue'
import OnlineLectureUtilBtn from '@/pages/onlinelecture/components/OnlineLectureUtilBtn.vue'
import OnlineLectureChatForm from '@/pages/onlinelecture/components/OnlineLectureChatForm.vue'
import OnlineLectureUserProfile from '@/pages/onlinelecture/components/OnlineLectureUserProfile.vue'
import OnlineLectureChatInput from '@/pages/onlinelecture/components/OnlineLectureChatInput.vue'
import OnlineLectureSettingBtn from '@/pages/onlinelecture/components/OnlineLectureSettingBtn.vue'
import UserVideo from './components/UserVideo.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'

const chatSideView: Ref<boolean> = ref(true)

function handleChatView(chat: boolean) {
  chatSideView.value = chat
}

interface userInfo {
  imgUrl: string
  nickName: string
  isHost: boolean
  mikeStatus: boolean
}
</script>
<template>
  <!--메인 틀-->
  <div class="mx-36">
    <OnlineLectureTitleBar />
    <hr />

    <div class="mt-5 grid grid-cols-9 gap-4 max-h-[1000px]">
      <div class="col-span-7 grid grid-rows-9 max-h-[900px] video-box">
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
              <div class="min-h-[600px] max-h-[600px] overflow-scroll no-scrollbar">
                <OnlineLectureChatForm />
              </div>
              <div>
                <OnlineLectureChatInput />
              </div>
            </div>
            <div v-else>
              <OnlineLectureUserProfile />
              <OnlineLectureUserProfile />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.video-box {
  border: 1px;
  border-radius: 10px;
  background-color: #ecf1fa;
}
</style>
