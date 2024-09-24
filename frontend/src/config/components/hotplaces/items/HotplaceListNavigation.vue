<script setup>
import { ref, computed } from 'vue';
const emits = defineEmits(['pageChange']);

import { hotplaceStore } from "@/stores/hotplaceStore.js";
const hpStore = hotplaceStore();

const navigationSize = parseInt(import.meta.env.VITE_HOTPLACE_NAVIGATION_SIZE);

const startPage = computed(() => {
    return parseInt((hpStore.currentPage - 1) / navigationSize) * navigationSize + 1;
});

const endPage = computed(() => {
    let lastPage = parseInt((hpStore.currentPage - 1) / navigationSize) * navigationSize + navigationSize;
    return hpStore.totalPage < lastPage ? hpStore.totalPage : lastPage;
});

const endRange = computed(() => {
    return parseInt((hpStore.totalPage - 1) / navigationSize) * navigationSize < hpStore.currentPage;
});

function range(start, end) {
    const list = [];
    for (let i = start; i <= end; i++) list.push(i);
    return list;
}

function onPageChange(page) {
    emits("pageChange", page);
}
</script>

<template>
    <div class="row">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" @click="onPageChange(1)">최신</a>
            </li>
            <li class="page-item">
                <a class="page-link" @click="onPageChange(startPage == 1 ? 1 : startPage - 1)">이전</a>
            </li>
            <template v-for="pg in range(startPage, endPage)" :key="pg">
                <li :class="hpStore.currentPage === pg ? 'page-item active' : 'page-item'">
                    <a class="page-link" @click="onPageChange(pg)">{{ pg }}</a>
                </li>
            </template>
            <li class="page-item">
                <a class="page-link" @click="onPageChange(endRange ? hpStore.totalPage : endPage + 1)">다음</a>
            </li>
            <li class="page-item"><a class="page-link" @click="onPageChange(hpStore.totalPage)">마지막</a></li>
        </ul>
    </div>
</template>

<style></style>
