<script setup lang="ts">
</script>
<template>
    <div class="flex justify-between h-full py-2">
        <div class="flex justify-center">
            <button class="btn">
                url copy 
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 17.25v3.375c0 .621-.504 1.125-1.125 1.125h-9.75a1.125 1.125 0 0 1-1.125-1.125V7.875c0-.621.504-1.125 1.125-1.125H6.75a9.06 9.06 0 0 1 1.5.124m7.5 10.376h3.375c.621 0 1.125-.504 1.125-1.125V11.25c0-4.46-3.243-8.161-7.5-8.876a9.06 9.06 0 0 0-1.5-.124H9.375c-.621 0-1.125.504-1.125 1.125v3.5m7.5 10.375H9.375a1.125 1.125 0 0 1-1.125-1.125v-9.25m12 6.625v-1.875a3.375 3.375 0 0 0-3.375-3.375h-1.5a1.125 1.125 0 0 1-1.125-1.125v-1.5a3.375 3.375 0 0 0-3.375-3.375H9.75" />
                </svg>
            </button>
        </div>
        <div class="flex justify-center">
            <button class="btn btn-square mx-1">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 18.75a6 6 0 0 0 6-6v-1.5m-6 7.5a6 6 0 0 1-6-6v-1.5m6 7.5v3.75m-3.75 0h7.5M12 15.75a3 3 0 0 1-3-3V4.5a3 3 0 1 1 6 0v8.25a3 3 0 0 1-3 3Z" />
                    </svg>
            </button>
            <button class="btn btn-error mx-1">
                <svg fill="none" viewBox="0 0 24 24" id="microphone-disable" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6"><path id="primary" d="M12 17h0a4 4 0 0 1 -4 -4V7a4 4 0 0 1 4 -4h0a4 4 0 0 1 4 4v6a4 4 0 0 1 -4 4Zm0 0v4m3 0H9" style="fill: none; stroke: rgb(255, 255, 255); stroke-linecap: round; stroke-linejoin: round; stroke-width: 2;"/><path id="secondary" x1="4" y1="4" x2="20" y2="20" style="fill: none; stroke: rgb(44, 169, 188); stroke-linecap: round; stroke-linejoin: round; stroke-width: 2;" d="m4 4 16 16"/></svg>
            </button>
            <button class="btn btn-square mx-1">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="m15.75 10.5 4.72-4.72a.75.75 0 0 1 1.28.53v11.38a.75.75 0 0 1-1.28.53l-4.72-4.72M4.5 18.75h9a2.25 2.25 0 0 0 2.25-2.25v-9a2.25 2.25 0 0 0-2.25-2.25h-9A2.25 2.25 0 0 0 2.25 7.5v9a2.25 2.25 0 0 0 2.25 2.25Z" />
                </svg>
            </button>
            <button class="btn btn-square mx-1">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M10.05 4.575a1.575 1.575 0 1 0-3.15 0v3m3.15-3v-1.5a1.575 1.575 0 0 1 3.15 0v1.5m-3.15 0 .075 5.925m3.075.75V4.575m0 0a1.575 1.575 0 0 1 3.15 0V15M6.9 7.575a1.575 1.575 0 1 0-3.15 0v8.175a6.75 6.75 0 0 0 6.75 6.75h2.018a5.25 5.25 0 0 0 3.712-1.538l1.732-1.732a5.25 5.25 0 0 0 1.538-3.712l.003-2.024a.668.668 0 0 1 .198-.471 1.575 1.575 0 1 0-2.228-2.228 3.818 3.818 0 0 0-1.12 2.687M6.9 7.575V12m6.27 4.318A4.49 4.49 0 0 1 16.35 15m.002 0h-.002" />
                </svg>
            </button>
            <button class="btn btn-square mx-1">
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="#000000"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    >
                    <path d="M13 3H4a2 2 0 00-2 2v10a2 2 0 002 2h16a2 2 0 002-2v-3" />
                    <path d="M8 21h8" />
                    <path d="M12 17v4" />
                    <path d="M17 8l5-5" />
                    <path d="M17 3h5v5" />
                </svg>
            </button>
        </div>
        <div>
            <button class="btn btn-outline btn-error">나가기</button>
        </div>
    </div>
</template>
<style scoped>
</style>