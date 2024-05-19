<script setup>
  import { ref, onMounted } from 'vue';
  import { RouterLink } from 'vue-router'
  import { hotplaceStore } from "@/stores/hotplaceStore.js";

  import HotplaceListItem from "@/components/hotplaces/items/HotplaceListItem.vue";
  import HotplaceListNavigation from "@/components/hotplaces/items/HotplaceListNavigation.vue";

  const hpStore = hotplaceStore();
  const listParams = ref({
      sizePerPage: import.meta.env.VITE_HOTPLACE_LIST_SIZE,
      currentPage: 1,
      totalPage: 1,
      start: null,
      filter: null,
      keyword: null,
      isDesc: false,
  })

  onMounted(async () => {
    await hpStore.getAllHotplace(listParams.value);
    listParams.value.currentPage = hpStore.currentPage ;
    listParams.value.totalPage = hpStore.totalPage;
  })

  const onPageChange = async (page) => {
    listParams.value.currentPage = page;
    console.log(listParams)
    await hpStore.getAllHotplace(listParams.value);
  };
</script>

<template>
  <div class="container mt-5">
    <div class="row">
      <div class="col-4 col-lg-3 col-sm-6" v-for="hotplace in hpStore.hotplaces" :key="hotplace.hotplaceId">
        <HotplaceListItem :hotplace="hotplace" />
      </div>

      <div class="col-4 col-lg-3 col-md-12 col-12">
        <div class="card card-blog card-background cursor-pointer">
          <div class="full-background" loading="lazy"
            style="background-image: url('https://d2v80xjmx68n4w.cloudfront.net/gigs/EOYa41702649874.jpg'); height: 100%;">
          </div>
          <div class="card-body">
            <div class="content-left text-start my-auto py-4">
              <h2 class="card-title text-white">핫플<br />검색하기</h2>
              <p class="card-description text-white">
                원하는 핫플레이스가 없나요? 검색해서 추가하세요!
              </p>
              <i class="fas fa-arrow-right text-xs ms-1" aria-hidden="true">
                <RouterLink :to="{ name: 'hotplaceSearch' }"> 핫플 검색하기 </RouterLink>
              </i>
            </div>
          </div>
        </div>
      </div>
    </div>

    <HotplaceListNavigation
      @pageChange="onPageChange" />
  </div>
</template>

<style scoped></style>