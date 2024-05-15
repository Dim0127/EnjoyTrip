import { createApp } from "vue";
// import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import materialKit from "./material-kit";

import vue3StarRatings from "vue3-star-ratings";

const app = createApp(App);
//const pinia = createPinia();

app.use(router);
app.use(createPinia());
//app.use(pinia);
app.use(materialKit);

app.component("vue3-star-ratings", vue3StarRatings);
app.mount("#app");
