<template>
  <div class="overflow-x-auto">
    <table class="table">
      <!-- head -->
      <thead>
        <tr>
          <th>쿠폰 목록</th>
          <th>종류</th>
          <th>내용</th>
          <th>사용 가능 기간</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <!-- 예제 1 -->
        <tr>
          <th>
            <label>
              <input type="checkbox" class="checkbox" />
            </label>
          </th>
          <td>
            <div class="flex items-center gap-3">
              <div class="avatar">
                <div class="mask mask-squircle w-12 h-12">
                  <img src="@/img/changepoint.png" alt="포인트 교환 아이콘" />
                </div>
              </div>
              <div>
                <div class="font-bold">할인권</div>
                <div class="text-sm opacity-50">쿠폰</div>
              </div>
            </div>
          </td>
          <td>
            1,000 원
            <br />
            <span class="badge badge-ghost badge-sm">해당 금액만큼 포인트로 교환</span>
          </td>
          <td>2024-01-15 ~ 2024-01-15</td>
          <th>
            <button class="btn btn-ghost btn-xs">사용 가능</button>
          </th>
        </tr>
        <!-- 예제 2 -->
        <tr>
          <th>
            <label>
              <input type="checkbox" class="checkbox" />
            </label>
          </th>
          <td>
            <div class="flex items-center gap-3">
              <div class="avatar">
                <div class="mask mask-squircle w-12 h-12">
                  <img src="@/img/sunmool.png" alt="선물 아이콘" />
                </div>
              </div>
              <div>
                <div class="font-bold">선물</div>
                <div class="text-sm opacity-50">쿠폰</div>
              </div>
            </div>
          </td>
          <td>
            382,000 원
            <br />
            <span class="badge badge-ghost badge-sm">튜터콜 첫 회원 기념 선물</span>
          </td>
          <td>2020-01-01 ~ 2020-01-02</td>
          <th>
            <button class="btn btn-ghost btn-xs">사용 불가</button>
          </th>
        </tr>
      </tbody>
      <!-- foot -->
      <tfoot>
        <tr>
          <th></th>
          <th>종류</th>
          <th>내용</th>
          <th>사용 가능 기간</th>
          <th></th>
        </tr>
      </tfoot>
    </table>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped></style>
