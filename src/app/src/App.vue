<template>
  <div id="app">
    <section class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">Trevorism Predictions</h1>
      </div>
    </section>

    <HelloWorld/>

  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
  body::-webkit-scrollbar {
    display: none;
  }
</style>
