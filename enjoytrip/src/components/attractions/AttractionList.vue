<script setup>
import AttractionListItem from '@/components/attractions/items/AttractionListItem.vue';
defineProps(['attractions'])
</script>

<template>
    <div class="row justify-content-center">
        <AttractionListItem v-for="attraction in attractions" :key="attraction.id" :attraction="attraction">
        </AttractionListItem>
    </div>
</template>

<style>
</style>