const user = JSON.parse(localStorage.getItem("user"));
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null };

import { createStore } from "vuex";

export default createStore({
  state: () => ({
    initialState,
    count: 0,
    bucket: []
  }),

  getters: {
    getCount(state) {
      return state.count
    },

    getSumPrice(state){
      return state.bucket.reduce((sum, current) => sum + current.price * current.totalCount, 0)
    },

    getAmountItem(state){
      return state.bucket.reduce((sum, current) => sum + current.totalCount, 0)
    },

  },

  mutations: {
    increment(state) {
      state.count++
    },

    decrement(state) {
      state.count--
    },

    setItem(state, data) {
      let item = state.bucket.findIndex(value => value.title === data.title)
      item !== -1
        ? state.bucket[item].totalCount++
        : state.bucket.push({ id: Date.now(), totalCount: 1, ...data })
    },

    delItem(state, data) {
      let item = state.bucket.findIndex(value => value.title === data.title)

      state.bucket[item].totalCount - 1 == 0 
        ? state.bucket = state.bucket.filter((d) => d !== data)
        : state.bucket[item].totalCount--
    },

    delAllItems(state, data){
      state.count -= data.totalCount
      state.bucket = state.bucket.filter((d) => d !== data)
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
