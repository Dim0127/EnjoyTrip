<script setup>
defineProps(['hotplace']);
</script>

<template>
    <div class="card d-flex justify-content-center align-items-center" style="height: 400px;">
        <div class="card-body">
            <div>
                <img src="@/assets/img/customs/default_place.png" class="img-fluid p-1 mb-2" loading="lazy"
                    style="height: 230px; width: 230px; border-radius: 5%;" />
            </div>
            <h5>
                <a class="text-dark font-weight-bold">{{ hotplace.hotplaceName }}</a>
            </h5>
            <div>
                <p> {{ hotplace.hotplaceAddress }} </p>
            </div>
            <div>
                <p> {{ hotplace.hotplacePhone }} </p>
            </div>

            <div class="d-flex justify-content-end">
                <RouterLink :to="{ name: 'hotplaceDetail', params: { hotplaceId: hotplace.hotplaceId } }">
                    <span class="text-sm icon-move-right text-success">
                        자세히 보기
                        <i class="fas fa-arrow-right text-xs ms-1"></i>
                    </span>
                </RouterLink>
            </div>
        </div>
    </div>
</template>
