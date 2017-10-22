<template>
    <nav class="navbar" role="navigation" aria-label="main navigation">
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
                    <div class="navbar-dropdown is-boxed">
                        <router-link to="/led/tasks" class="navbar-item">Tasks</router-link>
                        <router-link to="/led/channels" class="navbar-item">Channel settings</router-link>
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