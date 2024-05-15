import { ref } from "vue";
import { defineStore } from "pinia";
import { getAll } from "@/api/hotplace.js";

export const hotplaceStore = defineStore("hotplaceStore", () => {
  const hotplaces = ref([]);

  const getAllHotplace = async function () {
    hotplaces.value = [];
    try {
      const allHotplaces = await getAll();
      hotplaces.value = allHotplaces;
    } catch (error) {
      console.log(error);
    }
  };
  return { hotplaces, getAllHotplace };
});
