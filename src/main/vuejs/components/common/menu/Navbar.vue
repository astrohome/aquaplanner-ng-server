<template>
  <nav class="navbar is-light" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <a class="navbar-item" href="https://bulma.io">
        <img src="https://bulma.io/images/bulma-logo.png" alt="Bulma: a modern CSS framework based on Flexbox" width="112" height="28">
      </a>

      <button class="button navbar-burger">
        <span></span>
        <span></span>
        <span></span>
      </button>
    </div>
    <div class="navbar-menu">
      <div class="navbar-item has-dropdown is-hoverable">
        <div class="navbar-link">LED</div>
        <div class="navbar-dropdown">
          <router-link :to="{name: 'led-scheduling'}" class="navbar-item">
            <b-icon icon="schedule"></b-icon>
            <span>Scheduling</span>
          </router-link>
          <router-link :to="{name: 'led-settings'}" class="navbar-item">
            <b-icon icon="settings"></b-icon>
            <span>Settings</span>
          </router-link>
        </div>
      </div>
      <div class="navbar-item has-dropdown is-hoverable">
        <div class="navbar-link">Peripheral</div>
        <div class="navbar-dropdown">
          <router-link :to="{name: 'peripheral-scheduling'}" class="navbar-item">
            <b-icon icon="schedule"></b-icon>
            <span>Scheduling</span>
          </router-link>
          <router-link :to="{name: 'peripheral-settings'}" class="navbar-item">
            <b-icon icon="settings"></b-icon>
            <span>Settings</span>
          </router-link>
        </div>
      </div>

    </div>
  </nav>
</template>


<script>
  export default {
    name: 'top-navbar'
  }
</script>
