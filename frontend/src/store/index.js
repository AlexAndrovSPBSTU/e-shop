import { defineStore } from "pinia";

const user = JSON.parse(localStorage.getItem("user"));
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null };

export const useStore = defineStore("storeId", {
  // arrow function recommended for full type inference
  state: () => {
    return {
      initialState,
      count: 0,
    };
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
