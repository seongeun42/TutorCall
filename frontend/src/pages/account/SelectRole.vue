<template>
  <button class="btn bg-white ml-5" @click="showModal">회원가입</button>
  <dialog
    :id="modalId"
    class="modal fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50 z-50"
  >
    <div class="modal-box w-1/2 h-2/3 max-w-md bg-white p-6 rounded-md shadow-md text-center">
      <h3 class="font-black mb-4 mt-10 text-3xl">당신에 대해 알려주세요!</h3>
      <p class="py-4 font-semibold text-xl">당신은 학생이신가요 선생님이신가요?</p>
      <div class="modal-action flex justify-center">
        <form method="dialog">
          <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
        </form>

        <form @submit.prevent="handleSubmit" style="margin: 0px">
          <div class="flex justify-center">
            <button
              class="btnimsi btn-[#121212] bg-gray rounded px-4 py-2 mr-auto w-1/2 flex items-center"
              @click="saveChoice('학생')"
            >
              <img src="@/img/hand_student.png" alt="그림 설명" />
              <span style="font-size: large">학생</span>
            </button>
            <button
              class="btnimsi btn-[#121212] bg-gray rounded px-4 py-2 ml-auto w-1/2 flex items-center"
              @click="saveChoice('선생님')"
            >
              <img src="@/img/Teacher_pana.png" alt="선생님" />
              <span style="font-size: large">선생님</span>
            </button>
          </div>
        </form>
      </div>
    </div>
  </dialog>
</template>

<script setup lang="ts">
//   import { ref } from 'vue';
import { useUserStore } from '@/store/userStore';
const modalId = 'my_modal_4'
const userStore = useUserStore();

const showModal = (): void => {
  const modal = document.getElementById(modalId) as HTMLDialogElement
  if (modal) {
    modal.showModal()
  } else {
    console.error(`Element with id ${modalId} is not an HTMLDialogElement`)
  }
}

const closeModal = (): void => {
  const modal = document.getElementById(modalId) as HTMLDialogElement
  if (modal) {
    modal.close()
  } else {
    console.error(`Element with id ${modalId} is not an HTMLDialogElement`)
  }
}

const saveChoice = (choice: string) => {
  
  if(choice =="선생님"){
    userStore.isTutor = true;
  }else{
    userStore.isTutor = false;
  }
  emit('update:changeForm')
  closeModal()
  // 선택한 값을 'userChoice'라는 키로 localStorage에 저장.
  // 이 방법 이외에 변수로 저장해서 prop, emit으로 넘겨버리는 방법도 있음.
}

const handleSubmit = () => {
  // 폼 제출 시 필요한 Role값을 localStorage에서 가져와 넘길 예정.
}

const emit = defineEmits<{
  'update:changeForm': []
}>()
</script>

<style scoped>
.btnimsi {
  display: inline-flex;
  height: 15rem /* 48px */;
  min-height: 3rem /* 48px */;
  flex-shrink: 0;
  cursor: pointer;
  user-select: none;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  border-radius: var(--rounded-btn, 0.5rem /* 8px */);
  border-color: transparent;
  border-color: oklch(var(--btn-color, var(--b2)) / var(--tw-border-opacity));
}
</style>
