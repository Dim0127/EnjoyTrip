<script setup>
import { ref, onMounted } from 'vue';
import { RouterLink } from 'vue-router'
import { hotplaceStore } from "@/stores/hotplaceStore.js";

import MaterialButton from '@/components/materials/MaterialButton.vue';
import MaterialInput from '@/components/materials/MaterialInput.vue';
import HotplaceListItem from "@/components/hotplaces/items/HotplaceListItem.vue";
import HotplaceListNavigation from "@/components/hotplaces/items/HotplaceListNavigation.vue";

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
})

const callGetAllHotplace = async () => {
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
  <div class="container mt-5 d-flex align-items-center justify-content-">

    <div class="">


      <div class="row">
        <div class="row w-100 d-flex align-items-center">
          <div class="dropdown col-4">
            <MaterialButton variant="gradient" color="success" class="dropdown-toggle mx-1"
              :class="{ show: showDropFilter }" id="dropdownMenuButton" data-bs-toggle="dropdown"
              :aria-expanded="showDropFilter" @click="showDropFilter = !showDropFilter" v-model="selectedFilter">
              {{ selectedFilter ? selectedFilter : "Filter" }}
            </MaterialButton>

            <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropFilter }" aria-labelledby="dropdownMenuButton">
              <li v-for="filterName of filterOption" :key="filterName">
                <a class="dropdown-item border-radius-md"
                  @click="selectedFilter = filterName; showDropFilter = !showDropFilter">{{ filterName }}</a>
              </li>
            </ul>

            <MaterialButton variant="gradient" color="success" class="dropdown-toggle mx-1"
              :class="{ show: showDropSorting }" id="dropdownMenuButton" data-bs-toggle="dropdown"
              :aria-expanded="showDropSorting" @click="showDropSorting = !showDropSorting" v-model="selectedSorting">
              {{ selectedSorting ? selectedSorting : "Sorting" }}
            </MaterialButton>
            <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropSorting }" aria-labelledby="dropdownMenuButton">
              <li v-for="sortingName of sortingOption" :key="sortingName">
                <a class="dropdown-item border-radius-md"
                  @click="selectedSorting = sortingName; showDropSorting = !showDropSorting">{{ sortingName }}</a>
              </li>
            </ul>
          </div>

          <div class="col-8 d-flex align-items-center">
            <MaterialInput class="input-group-dynamic col-10 mx-3" type="text" placeholder="Search"
              @inputEvent="(inputValue) => serachKeyword = inputValue" />
            <MaterialButton variant="gradient" color="success" class="button col-2 mx-3" @click="callGetAllHotplace">
              Search!
            </MaterialButton>
          </div>

        </div>
      </div>

      <div class="row ">

        <div class="col-4 col-lg-3 col-sm-6 mb-4" v-for="hotplace in hpStore.hotplaces" :key="hotplace.hotplaceId">
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

      <HotplaceListNavigation @pageChange="onPageChange" />
    </div>
  </div>
</template>

<style scoped></style>