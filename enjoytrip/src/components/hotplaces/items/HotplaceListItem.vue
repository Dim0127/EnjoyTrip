<script setup>
defineProps(['hotplace']);
</script>

<template>
    <div class="card card-plain">
        <a class="d-block blur-shadow-image">
            <img src="@/assets/img/customs/default_place.png" class="img-fluid shadow border-radius-lg p-3 " loading="lazy"
                style="width: 100%;" />
        </a>
    </div>
    <div class="card-body pt-3" style=" background-color: white;">
        <h5>
            <a class="text-dark font-weight-bold">{{ hotplace.hotplaceName }}</a>
        </h5>
        <p>
            {{ hotplace.hotplaceAddress }}
        </p>
        <a class="text-sm icon-move-right" :class="`text-success`">{{ hotplace.hotplacePhone }}
            <i class="fas fa-arrow-right text-xs ms-1"></i>
        </a>
    </div>
</template>
