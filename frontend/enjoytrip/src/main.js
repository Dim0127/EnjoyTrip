import { createApp } from "vue";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import { createPinia } from "pinia";

import { useMemberStore } from "@/stores/member";

import App from "./App.vue";
import router from "./router";

import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import materialKit from "./material-kit";

import vue3StarRatings from "vue3-star-ratings";

import { storage } from "./firebase"; // Firebase 모듈 가져오기

const app = createApp(App);

app.use(createPinia().use(piniaPluginPersistedstate));
app.use(router);
app.use(materialKit);
const memberStore = useMemberStore();
memberStore.initializeStore();

app.component("vue3-star-ratings", vue3StarRatings);

app.config.globalProperties.$storage = storage; // Vue 앱에서 사용할 수 있도록 등록

app.mount("#app");
