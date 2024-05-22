<script setup>
defineProps(['attraction'])
</script>

<template>
    <div class="card card-profile w-100 my-3">
        <div class="row mx-3">
            <div class="col-lg-4 col-md-6 col-12">
                <div class="img-wrapper p-3 pe-md-0">
                    <img v-if="attraction.image" class="border-radius-md img-fluid" :src="attraction.image" />
                    <img v-else class="border-radius-md img-fluid" src="@/assets/img/customs/default_place.png" />
                </div>
            </div>
            <div class="col-lg-8 col-md-6 col-12">
                <div class="card-body ps-lg-0">
                    <h5 class="mb-2">{{ attraction.name }}</h5>
                    <h6> 주소 : {{ attraction.address }}</h6>
                    <p class="mb-0"> 전화번호 : {{ attraction.phone }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
.img-wrapper {
    width: 260px;
    /* 원하는 너비로 설정 */
    height: 180px;
    /* 원하는 높이로 설정 */
}

.img-wrapper img {
    width: 120%;
    height: 100%;
    object-fit: cover;
    /* 이미지가 요소에 가득 차도록 설정 */
}

@font-face {
    font-family: 'TTLaundryGothicB';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

div,
h1,
h6 {
    font-family: 'TTLaundryGothicB';
}
</style>