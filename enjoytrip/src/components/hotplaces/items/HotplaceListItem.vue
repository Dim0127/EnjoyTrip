<script setup>
defineProps(['hotplace']);
</script>


<template>
    <div class="card d-flex justify-items-start">
        <div class="card-body d-flex flex-column">
            <img src="@/assets/img/customs/default_place.png" class="img-fluid p-1 mb-2" loading="lazy"
                style="height: 230px; width: 230px; border-radius: 5%; align-self: center;" />
            <h5 style="height: 52px;">
                <i class="material-icons" aria-hidden="true" style="font-size: 20px; vertical-align: middle;">loyalty</i>
                <a class="text-dark font-weight-bolds" style="justify-self: center;" >{{ hotplace.hotplaceName }}</a>
            </h5>
            <h6> 
                <i class="material-icons" aria-hidden="true" style="font-size: 16px; vertical-align: middle;">location_on</i>             
                {{ hotplace.hotplaceAddress }} 
            </h6>
            <p> 
                <i class="material-icons" aria-hidden="true" style="font-size: 12px; vertical-align: middle;">mode_comment</i>
                {{ hotplace.hotplaceCategory}} 
            </p>
            <p> 
                <i class="material-icons" aria-hidden="true" style="font-size: 12px; vertical-align: middle;">call</i>
                {{ hotplace.hotplacePhone ? hotplace.hotplacePhone : "없음"}} 
            </p>

            <div class="mt-auto d-flex justify-content-end">
                <RouterLink :to="{ name: 'hotplaceDetail', params: { hotplaceId: hotplace.hotplaceId } }">
                    <span class="text-md icon-move-right" style="color:#5f91c7">
                        자세히 보기
                        <i class="fas fa-arrow-right text-xs ms-1"></i>
                    </span>
                </RouterLink>
            </div>
        </div>
    </div>

</template>


<style>
@font-face {
    font-family: 'TTLaundryGothicB';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

div,
h1,
h3,
h5,
h6,
p,
span {
    font-family: 'TTLaundryGothicB';
}
</style>