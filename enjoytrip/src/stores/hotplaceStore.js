import { ref } from "vue";
import { defineStore } from "pinia";
import { getAll } from "@/api/hotplace.js";

export const hotplaceStore = defineStore("hotplaceStore", () => {
  const hotplaces = ref([]);
  const currentPage = ref(1);
  const totalPage = ref(1);

  const getAllHotplace = async (listParams) => {
    hotplaces.value = [];
    try {
      const hotplaceData = await getAll(listParams);
      hotplaces.value = hotplaceData.hotplaceList;
      currentPage.value = hotplaceData.currentPage;
      totalPage.value = hotplaceData.totalPage;
    } catch (error) {
      console.log(error);
    }
  };

  return { hotplaces, currentPage, totalPage, getAllHotplace };
});
