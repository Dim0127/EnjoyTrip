<script setup>
import { useRouter, RouterLink } from "vue-router";
import { ref, watch } from "vue";
import { useWindowsWidth } from "../../assets/js/useWindowsWidth";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

import ArrDark from "@/assets/img/down-arrow-dark.svg";
import DownArrWhite from "@/assets/img/down-arrow-white.svg";

const memberStore = useMemberStore();
const { isLogin } = storeToRefs(memberStore);
const { userLogout } = memberStore;


const router = useRouter()

const logout = () => {
  userLogout();
  router.replace({ name: "main" })
};

const props = defineProps({
  action: {
    type: Object,
    route: String,
    color: String,
    label: String,
    default: () => ({
      route: "https://www.creative-tim.com/product/vue-material-kit",
      color: "bg-gradient-success",
      label: "Free Download",
    }),
  },
  transparent: {
    type: Boolean,
    default: false,
  },
  light: {
    type: Boolean,
    default: false,
  },
  dark: {
    type: Boolean,
    default: false,
  },
  sticky: {
    type: Boolean,
    default: false,
  },
  darkText: {
    type: Boolean,
    default: false,
  },
});

function getArrowColor() {
  if (props.transparent && textDark.value) {
    return ArrDark;
  } else if (props.transparent) {
    return DownArrWhite;
  } else {
    return ArrDark;
  }
}

const getTextColor = () => {
  let color;
  if (props.transparent && textDark.value) {
    color = "text-dark";
  } else if (props.transparent) {
    color = "text-white";
  } else {
    color = "text-dark";
  }

  return color;
};

let textDark = ref(props.darkText);
const { type } = useWindowsWidth();

if (type.value === "mobile") {
  textDark.value = true;
} else if (type.value === "desktop" && textDark.value == false) {
  textDark.value = false;
}

watch(
  () => type.value,
  (newValue) => {
    if (newValue === "mobile") {
      textDark.value = true;
    } else {
      textDark.value = false;
    }
  }
);
</script>

<template>
  <nav class="navbar navbar-expand-lg top-0" :class="{
    'z-index-3 w-100 shadow-none navbar-transparent position-absolute my-3': props.transparent,
    'my-3 blur border-radius-lg z-index-3 py-2 shadow py-2 start-0 end-0 mx-4 position-absolute mt-4':
      props.sticky,
    'navbar-light bg-white py-3': props.light,
    ' navbar-dark bg-gradient-dark z-index-3 py-3': props.dark,
  }">
    <div :class="props.transparent || props.light || props.dark ? 'container' : 'container-fluid px-0'">
      <RouterLink class="navbar-brand d-none d-md-block" :class="[
        (props.transparent && textDark.value) || !props.transparent
          ? 'text-dark font-weight-bolder ms-sm-3'
          : 'text-white font-weight-bolder ms-sm-3',
      ]" :to="{ name: 'main' }" rel="tooltip" title="Designed and Coded by Creative Tim" data-placement="bottom">
        EnjoyTrip
      </RouterLink>

      <button class="navbar-toggler shadow-none ms-2" type="button" data-bs-toggle="collapse"
        data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon mt-2">
          <span class="navbar-toggler-bar bar1"></span>
          <span class="navbar-toggler-bar bar2"></span>
          <span class="navbar-toggler-bar bar3"></span>
        </span>
      </button>

      <div class="collapse navbar-collapse w-100 pt-3 pb-2 py-lg-0" id="navigation">
        <ul class="navbar-nav navbar-nav-hover ms-auto">
          <RouterLink :to="{ name: 'attraction' }">
            <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24">
              <path
                d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15h-2v-6h2v6zm0-8h-2V7h2v2zm6 8h-2v-6h2v6zm0-8h-2V7h2v2z" />
            </svg>
            관광지 검색
          </RouterLink>
        </ul>

        <ul class="navbar-nav navbar-nav-hover">
          <li class="nav-item dropdown dropdown-hover mx-2">
            <a role="button" class="nav-link ps-2 d-flex cursor-pointer align-items-center" :class="getTextColor()"
              id="dropdownMenuPages" data-bs-toggle="dropdown" aria-expanded="false">
              <i class="fas fa-map-marker-alt text-sm"></i>
              핫플레이스
              <img :src="getArrowColor()" alt="down-arrow" class="arrow ms-2 d-lg-block d-none" />
              <img :src="getArrowColor()" alt="down-arrow" class="arrow ms-1 d-lg-none d-block ms-auto" />
            </a>
            <div class="dropdown-menu dropdown-menu-animation ms-n3 dropdown-md p-3 border-radius-xl mt-0 mt-lg-3"
              aria-labelledby="dropdownMenuPages">
              <div class="row d-none d-lg-block">
                <div class="col-12 px-4 py-2">
                  <div class="row">
                    <div class="position-relative">
                      <RouterLink :to="{ name: 'hotplaceList' }" class="dropdown-item border-radius-md">
                        <span>핫플레이스 게시판</span>
                      </RouterLink>
                      <RouterLink :to="{ name: 'hotplaceSearch' }" class="dropdown-item border-radius-md">
                        <span>핫플레이스 등록하기</span>
                      </RouterLink>
                    </div>
                  </div>
                </div>
              </div>

              <div class="d-lg-none">
                <div class="dropdown-header text-dark font-weight-bolder d-flex align-items-center px-0">
                  Landing Pages
                </div>
              </div>
            </div>
          </li>
        </ul>

        <template v-if="!isLogin">
          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink :to="{ name: 'login' }" class="d-flex justify-content-center">
              <i class="material-icons ms-2" aria-hidden="true">favorite</i>
              로그인
            </RouterLink>
          </ul>

          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink :to="{ name: 'join' }" class="d-flex justify-content-center">
              <i class="material-icons ms-2" aria-hidden="true">favorite</i>
              회원가입
            </RouterLink>
          </ul>
        </template>
        <template v-else>

          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink :to="{ name: 'myreview' }">
              <svg width="20px" height="20px" class="material-icons me-2 opacity-6" viewBox="0 0 24 24"
                aria-hidden="true" data-testid="GitHubIcon" :fill="props.transparent && '#fff'">
                <path
                  d="M12 1.27a11 11 0 00-3.48 21.46c.55.09.73-.28.73-.55v-1.84c-3.03.64-3.67-1.46-3.67-1.46-.55-1.29-1.28-1.65-1.28-1.65-.92-.65.1-.65.1-.65 1.1 0 1.73 1.1 1.73 1.1.92 1.65 2.57 1.2 3.21.92a2 2 0 01.64-1.47c-2.47-.27-5.04-1.19-5.04-5.5 0-1.1.46-2.1 1.2-2.84a3.76 3.76 0 010-2.93s.91-.28 3.11 1.1c1.8-.49 3.7-.49 5.5 0 2.1-1.38 3.02-1.1 3.02-1.1a3.76 3.76 0 010 2.93c.83.74 1.2 1.74 1.2 2.94 0 4.21-2.57 5.13-5.04 5.4.45.37.82.92.82 2.02v3.03c0 .27.1.64.73.55A11 11 0 0012 1.27">
                </path>
              </svg>
              내 리뷰
            </RouterLink>
          </ul>

          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink :to="{ name: 'mypage' }">
              <svg width="20px" height="20px" class="material-icons me-2 opacity-6" viewBox="0 0 24 24"
                aria-hidden="true" data-testid="GitHubIcon" :fill="props.transparent && '#fff'">
                <path
                  d="M12 1.27a11 11 0 00-3.48 21.46c.55.09.73-.28.73-.55v-1.84c-3.03.64-3.67-1.46-3.67-1.46-.55-1.29-1.28-1.65-1.28-1.65-.92-.65.1-.65.1-.65 1.1 0 1.73 1.1 1.73 1.1.92 1.65 2.57 1.2 3.21.92a2 2 0 01.64-1.47c-2.47-.27-5.04-1.19-5.04-5.5 0-1.1.46-2.1 1.2-2.84a3.76 3.76 0 010-2.93s.91-.28 3.11 1.1c1.8-.49 3.7-.49 5.5 0 2.1-1.38 3.02-1.1 3.02-1.1a3.76 3.76 0 010 2.93c.83.74 1.2 1.74 1.2 2.94 0 4.21-2.57 5.13-5.04 5.4.45.37.82.92.82 2.02v3.03c0 .27.1.64.73.55A11 11 0 0012 1.27">
                </path>
              </svg>
              마이페이지
            </RouterLink>
          </ul>

          <ul class="navbar-nav navbar-nav-hover">
            <a href="https://www.github.com/creativetimofficial/vue-material-kit"
              class="nav-link d-flex cursor-pointer align-items-center" @click.prevent="logout">
              <svg width="20px" height="20px" class="material-icons me-2 opacity-6" viewBox="0 0 24 24"
                aria-hidden="true" data-testid="GitHubIcon" :fill="props.transparent && '#fff'">
                <path
                  d="M12 1.27a11 11 0 00-3.48 21.46c.55.09.73-.28.73-.55v-1.84c-3.03.64-3.67-1.46-3.67-1.46-.55-1.29-1.28-1.65-1.28-1.65-.92-.65.1-.65.1-.65 1.1 0 1.73 1.1 1.73 1.1.92 1.65 2.57 1.2 3.21.92a2 2 0 01.64-1.47c-2.47-.27-5.04-1.19-5.04-5.5 0-1.1.46-2.1 1.2-2.84a3.76 3.76 0 010-2.93s.91-.28 3.11 1.1c1.8-.49 3.7-.49 5.5 0 2.1-1.38 3.02-1.1 3.02-1.1a3.76 3.76 0 010 2.93c.83.74 1.2 1.74 1.2 2.94 0 4.21-2.57 5.13-5.04 5.4.45.37.82.92.82 2.02v3.03c0 .27.1.64.73.55A11 11 0 0012 1.27">
                </path>
              </svg>
              로그아웃
            </a>
          </ul>
        </template>


        <!-- 
        <ul class="navbar-nav d-lg-block d-none">
          <li class="nav-item">
            <a
              :href="action.route"
              class="btn btn-sm mb-0"
              :class="action.color"
              onclick="smoothToPricing('pricing-soft-ui')"
              >{{ action.label }}</a
            >
          </li>
        </ul> -->
      </div>
    </div>
  </nav>
</template>
