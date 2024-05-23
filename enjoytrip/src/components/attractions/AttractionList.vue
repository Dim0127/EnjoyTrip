<script setup>
import AttractionListItem from '@/components/attractions/items/AttractionListItem.vue';
defineProps(['attractions'])
</script>

<template>
    <class class="row justify-content-center">
        <div v-if="attractions">
            <div v-if="attractions.length == 0 ">
                <div class="card-body ps-lg-0 my-3 p-5">
                    <h5 class="mb-2 text-center"> 
                        <i class="material-icons" aria-hidden="true" style="font-size: 24px; vertical-align: middle;">dangerous</i> 
                        검색 결과 없음
                        <i class="material-icons" aria-hidden="true" style="font-size: 24px; vertical-align: middle;">dangerous</i>
                    </h5>
                </div>
            </div>
            <div v-else>
                <AttractionListItem v-for="attraction in attractions" :key="attraction.id" :attraction="attraction">
                </AttractionListItem>
            </div>
        </div>
    </class>
</template>

<style></style>