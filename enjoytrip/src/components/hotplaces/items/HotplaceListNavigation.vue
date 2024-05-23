<script setup>
import { computed } from 'vue';
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
                <a class="page-link p-4" @click="onPageChange(1)"><<</a>
            </li>
            <li class="page-item">
                <a class="page-link p-4" @click="onPageChange(startPage == 1 ? 1 : startPage - 1)"><</a>
            </li>
            <template v-for="pg in range(startPage, endPage)" :key="pg">
                <li :class="hpStore.currentPage === pg ? 'page-item active' : 'page-item'">
                    <a class="page-link p-4" @click="onPageChange(pg)">{{ pg }}</a>
                </li>
            </template>
            <li class="page-item">
                <a class="page-link p-4" @click="onPageChange(endRange ? hpStore.totalPage : endPage + 1)">></a>
            </li>
            <li class="page-item">
                <a class="page-link p-4" @click="onPageChange(hpStore.totalPage)">>></a>
            </li>
        </ul>
    </div>
</template>

<style>
.page-item.active .page-link {
    background-color: #5eb0f7;
    border: #5eb0f7;
    color: white;
}
</style>
