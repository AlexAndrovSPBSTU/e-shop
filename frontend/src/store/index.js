const user = JSON.parse(localStorage.getItem("user"));
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null };

import { createStore } from "vuex";

export default createStore({
  state: () => ({
    initialState,
    count: 0,
  }),

  getters: {
    
  },

  mutations: {
    increment(state) {
      state.count++
    },

  },

  actions: {
    async login(user) {
      try {
        localStorage.setItem("user", JSON.stringify(user));
        this.initialState.user = user
        this.initialState.status.loggedIn = true;
      } catch (error) {
        return error
      }
    },
  },
});
