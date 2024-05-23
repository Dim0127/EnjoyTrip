<script setup>
import { ref, onMounted } from 'vue';
import { RouterLink } from 'vue-router'
import { hotplaceStore } from "@/stores/hotplaceStore.js";

import { getFirstImage } from "@/api/review";

import MaterialButton from '@/components/materials/MaterialButton.vue';
import MaterialInput from '@/components/materials/MaterialInput.vue';
import HotplaceListItem from "@/components/hotplaces/items/HotplaceListItem.vue";
import HotplaceListNavigation from "@/components/hotplaces/items/HotplaceListNavigation.vue";

const defaultImageUrl = import.meta.env.VITE_DEFAULT_IMAGE_URL;

const hpStore = hotplaceStore();
const selectedFilter = ref(null);
const showDropFilter = ref(false);
const filterOption = ref([
  "Name",
  "Category",
  "CreatedAt",
])
const selectedSorting = ref(null);
const showDropSorting = ref(false);
const sortingOption = ref([
  "ASC",
  "DESC",
])
const serachKeyword = ref(null);

const listParams = ref({
  sizePerPage: import.meta.env.VITE_HOTPLACE_LIST_SIZE,
  currentPage: 1,
  start: null,
  filter: null,
  keyword: null,
  isDesc: false,
})

onMounted(async () => {
  await hpStore.getAllHotplace(listParams.value);
  listParams.value.currentPage = hpStore.currentPage;
  for (const hotplace of hpStore.hotplaces) {
    const tmpUrl = await getFirstImage(hotplace.hotplaceId);
    hotplace['firstImage'] = tmpUrl ? tmpUrl : defaultImageUrl;
  }
})

const callGetAllHotplace = async () => {
  console.log(serachKeyword.value);
  listParams.value.filter = "hotplace" + selectedFilter.value;
  listParams.value.keyword = serachKeyword.value;
  listParams.value.isDesc = selectedSorting.value === 'ASC' ? false : true;
  await hpStore.getAllHotplace(listParams.value);
}

const onPageChange = async (page) => {
  listParams.value.currentPage = page;
  await callGetAllHotplace();
};
</script>

<template>

  <div class="d-flex justify-content-center mb-5 mt-4 mx-0">
    <div class="row mx-0 my-0">
      <!-- 필터 -->
      <div class="col-md-2 me-3">
        <div class="dropdown">
          <MaterialButton style="background-color: #5eb0f7; color:white" class="dropdown-toggle mx-1 shadow"
            :class="{ show: showDropFilter }" id="filterDropdown" data-bs-toggle="dropdown"
            :aria-expanded="showDropFilter" @click="showDropFilter = !showDropFilter" v-model="selectedFilter">
            {{ selectedFilter ? selectedFilter : "Filter" }}
          </MaterialButton>
          <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropFilter }" aria-labelledby="filterDropdown">
            <li v-for="filterName of filterOption" :key="filterName">
              <a class="dropdown-item border-radius-md"
                @click="selectedFilter = filterName; showDropFilter = !showDropFilter">{{ filterName }}</a>
            </li>
          </ul>
        </div>
      </div>
      <!-- 정렬 -->
      <div class="col-md-2 me-5">
        <div class="dropdown">
          <MaterialButton style="background-color: #5eb0f7; color:white" class="dropdown-toggle mx-1 shadow"
            :class="{ show: showDropSorting }" id="sortingDropdown" data-bs-toggle="dropdown"
            :aria-expanded="showDropSorting" @click="showDropSorting = !showDropSorting" v-model="selectedSorting">
            {{ selectedSorting ? selectedSorting : "Sorting" }}
          </MaterialButton>
          <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropSorting }" aria-labelledby="sortingDropdown">
            <li v-for="sortingName of sortingOption" :key="sortingName">
              <a class="dropdown-item border-radius-md"
                @click="selectedSorting = sortingName; showDropSorting = !showDropSorting">{{ sortingName }}</a>
            </li>
          </ul>
        </div>
      </div>
      <!-- 검색어 -->
      <div class="col-md-4 me-4">
        <MaterialInput class="input-group-dynamic" type="text" placeholder="Search"
          @inputEvent="(inputValue) => { serachKeyword = inputValue; }" />
      </div>
      <div class="col-md-2">
        <MaterialButton style="background-color: #5eb0f7; color:white" class="button shadow"
          @click="callGetAllHotplace">
          검색
        </MaterialButton>
      </div>
    </div>
  </div>

  <div class=" d-flex justify-content-center">

    <div class="row col-10">
      <div class="col-3 mb-4" v-for="hotplace in hpStore.hotplaces" :key="hotplace.hotplaceId">
        <div class="d-flex flex-column h-100">
          <HotplaceListItem :hotplace="hotplace" class="flex-grow-1" />
        </div>
      </div>
      <div class="col-3 mb-4">
        <div class="card card-blog card-background cursor-pointer d-flex flex-column h-100">
          <div class="full-background" loading="lazy"
            style="background-image: url('https://cdn.pixabay.com/photo/2016/02/06/08/53/lighthouse-1182680_1280.jpg'); height: 100%;">
          </div>
          <div class="card-body flex-grow-1">
            <div class="content-left text-start my-auto py-4">
              <h2 class="card-title text-white mt-3">핫플<br />검색하기</h2>
              <h5 class="card-description text-white mb-0">
                찾는 핫플레이스가 없나요?<br>직접 등록하고,<br>공유해보세요!
              </h5>
              <h5 class="card-description text-white">
              </h5>
              <i class="fas fa-arrow-right ms-1" aria-hidden="true" style="font-size:20px;">
                <RouterLink :to="{ name: 'hotplaceSearch' }"> 핫플 검색하기 </RouterLink>
              </i>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-4 col-xl-3 mt-6">
      <HotplaceListNavigation @pageChange="onPageChange" />
    </div>
  </div>

</template>

<style scoped>
h2 {
  font-family: 'TTLaundryGothicB';
}

a:hover {
  color: #5eb0f7;
}
</style>