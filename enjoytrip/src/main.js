import { createApp } from "vue";
// import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import materialKit from "./material-kit";

const app = createApp(App);
//const pinia = createPinia();

app.use(router);
app.use(createPinia());
//app.use(pinia);
app.use(materialKit);

app.mount("#app");
