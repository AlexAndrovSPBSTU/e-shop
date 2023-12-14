<template>
  <div v-if="this.$store.state.initialState.status.loggedIn">
    <div v-if="this.$store.state.initialState.user.role === 'ADMIN'">
      <v-btn
        variant="outlined"
        color="orange"
        class="text-none text-h6"
        @click="goToAdmin"
      >
        Админ панель
      </v-btn>
    </div>
  </div>
</template>

<script>


export default {
  data: () => ({
    
  }),

  methods: {
    goToAdmin() {
      this.$router.push("/admin");
    },

  },
};
</script>

<style>

</style>
