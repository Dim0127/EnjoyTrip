import { ref } from "vue";
import { defineStore } from "pinia";
import { getAll } from "@/api/hotplace.js";

import { getFirstImage } from "@/api/review";
const defaultImageUrl = import.meta.env.VITE_DEFAULT_PLACE_URL;

export const hotplaceStore = defineStore("hotplaceStore", () => {
  const hotplaces = ref([]);
  const currentPage = ref(1);
  const totalPage = ref(1);

  const getAllHotplace = async (listParams) => {
    hotplaces.value = [];
    try {
      const hotplaceData = await getAll(listParams);
      hotplaces.value = hotplaceData.hotplaceList;

      for (let i = 0; i < hotplaces.value.length; i++) {
        const tmpUrl = await getFirstImage(hotplaces.value[i].hotplaceId);
        hotplaces.value[i]["firstImage"] = tmpUrl ? tmpUrl : defaultImageUrl;
      }
      currentPage.value = hotplaceData.currentPage;
      totalPage.value = hotplaceData.totalPage;
    } catch (error) {
      console.log(error);
    }
  };

  return { hotplaces, currentPage, totalPage, getAllHotplace };
});
