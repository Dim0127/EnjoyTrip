<script setup>
defineProps(['attraction'])
</script>

<template>
    <div class="card card-profile w-100 my-3">
        <div class="row mx-3">
            <div class="col-lg-4 col-md-6 col-12">
                <div class="p-3 pe-md-0">
                    <img class="w-100 h-100 border-radius-md shadow-lg img-fluid" :src="attraction.image"/>
                </div>
            </div>
            <div class="col-lg-8 col-md-6 col-12 my-auto">
                <div class="card-body ps-lg-0">
                    <h5 class="mb-0">{{ attraction.name }}</h5>
                    <h6> 주소 : {{ attraction.address }}</h6>
                    <p class="mb-0"> 전화번호 : {{ attraction.phone }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
</style>