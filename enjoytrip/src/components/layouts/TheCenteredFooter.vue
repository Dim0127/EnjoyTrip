<script setup>
defineProps({
  socials: {
    type: Array,
    icon: String,
    link: String,
    default: () => [
      {
        icon: '<i class="fa fa-instagram text-lg opacity-8" aria-hidden="true" >&nbsp;jimin</i>',
        link: "https://www.instagram.com/dim.ni_0127/",
      },
      {
        icon: '<i class="fa fa-instagram text-lg opacity-8" aria-hidden="true">&nbsp;eiseul</i>',
        link: "https://www.instagram.com/",
      },
      {
        icon: '<i class="fab fa-github text-lg opacity-8">&nbsp;mini-seuli</i>',
        link: "https://lab.ssafy.com/s11/a18/javaproject/finalproject/enjoytrip_08_mini-seuli",
      },
      {
        icon: '<i class="fab fa-youtube text-lg opacity-8">&nbsp;SSAFY</i>',
        link: "https://www.youtube.com/channel/UC_XI3ByFO1uZIIH-g-zJZiw/featured",
      },
    ],
  },

});
</script>
<template>
  <footer class="footer pt-3 mb-5">
    <div class="container">
      <div class="row">

        <div class="col-lg-8 mx-auto text-center mb-4 mt-2">
          <a v-for="{ icon, link } of socials" :key="link" :href="link" target="_blank"
            class="text-secondary me-xl-4 me-4" v-html="icon">
          </a>
        </div>
      </div>
      <div class="row">
        <div class="col-8 mx-auto text-center mt-1">
          <p class="mb-0 text-secondary">
            Copyright © {{ new Date().getFullYear() }} Material by Creative Tim.
          </p>
        </div>
      </div>
    </div>
  </footer>
</template>
<style>
@font-face {
  font-family: 'TTLaundryGothicB';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
  font-weight: 700;
  font-style: normal;
}

div,
h1,
h2,
h3,
h4,
h5,
p,
a {
  font-family: 'TTLaundryGothicB';
}
</style>