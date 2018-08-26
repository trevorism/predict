<template>
  <div id="app">
    <header class="navbar has-shadow"> <img src="./assets/header.png"/> </header>
    <div class="tile is-parent">
      <article class="tile is-child is-primary">How good are you at telling the future?</article>
    </div>
    <div class="section"><MainContent></MainContent></div>
  </div>
</template>

<script>
import MainContent from './components/MainContent.vue'

export default {
  name: 'App',
  components: {
    MainContent
  }
}
</script>

<style>
  body::-webkit-scrollbar {
    display: none;
  }
</style>
