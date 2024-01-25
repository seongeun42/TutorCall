<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import type { Ref } from 'vue'

interface payInfo {
  [key: string]: string
}

const props = defineProps<{ datas?: payInfo[]; mode: string; btnName: string }>()

const selectedMonth: Ref<string> = ref('')
const selectedYear: Ref<string> = ref('')
const cardNumber: Ref<string> = ref('')
const modalRef: Ref<HTMLDialogElement | null> = ref(null)

if (props.datas) {
  selectedMonth.value = props.datas[0]['Month']
  selectedYear.value = props.datas[0]['Year']
  cardNumber.value = props.datas[0]['카드 번호']
}

const monthOptions: string[] = [
  '01',
  '02',
  '03',
  '04',
  '05',
  '06',
  '07',
  '08',
  '09',
  '10',
  '11',
  '12'
]
const yearOptions: string[] = [
  '2024',
  '2025',
  '2026',
  '2027',
  '2028',
  '2029',
  '2030',
  '2031',
  '2032',
  '2033',
  '2034'
]
const cvc: Ref<string> = ref('')

watch(cardNumber, (value: string) => {
  const formattedValue: string = value.replace(/\s/g, '').replace(/(\d{4})/g, '$1 ')
  cardNumber.value = formattedValue.slice(0, 19)
})

function openModal(): void {
  const modal: HTMLDialogElement | null = modalRef.value
  if (modal) {
    modal.showModal()
  }
}

onMounted((): void => {
  const modal: HTMLDialogElement | null = modalRef.value
  if (modal) {
    modal.close()
  }
})
</script>
<template>
  <button class="btn btn-sm" @click="openModal">{{ props.mode }}</button>
  <dialog ref="modalRef" id="infoModal" class="modal">
    <div class="modal-box">
      <form method="dialog">
        <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
      </form>
      <div class="flex flex-col">
        <p>카드 정보</p>
        <div class="w-full">
          <label class="form-control w-full">
            <div class="label">
              <span class="label-text">카드 번호</span>
            </div>
            <input type="text" class="input input-bordered w-full" v-model="cardNumber" />
          </label>
        </div>
        <div id="expired" class="w-full flex flex-row">
          <label class="form-control">
            <div class="label">
              <span class="label-text">Expired Date</span>
            </div>
            <div class="flex flex-row">
              <div class="pr-4">
                <select class="select select-bordered w-full max-w-xs" v-model="selectedMonth">
                  <option disabled selected>MONTH</option>
                  <option
                    v-for="monthOption in monthOptions"
                    :key="monthOption"
                    :value="monthOption"
                    :selected="monthOption === selectedMonth"
                  >
                    {{ monthOption }}
                  </option>
                </select>
              </div>
              <div class="pr-4">
                <select class="select select-bordered w-full max-w-xs" v-model="selectedYear">
                  <option disabled selected>YEAR</option>
                  <option
                    v-for="yearOption in yearOptions"
                    :key="yearOption"
                    :value="yearOption"
                    :selected="yearOption === selectedMonth"
                  >
                    {{ yearOption }}
                  </option>
                </select>
              </div>
            </div>
          </label>
          <label class="form-control">
            <div class="label">
              <span class="label-text">CVC</span>
            </div>
            <input type="text" class="input input-bordered w-full" v-model="cvc" />
          </label>
        </div>
      </div>
      <div class="flex justify-end mt-2">
        <button class="btn btn-sm">{{ props.btnName }}</button>
      </div>
    </div>
  </dialog>
</template>
<style scoped></style>
