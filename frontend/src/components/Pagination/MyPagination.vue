<template>
  <div class="text-center">
    <v-container>
      <v-row justify="center">
        <v-col cols="8">
          <v-container class="max-width">
            <v-pagination
              :value="page"
              @click="emitPageChange"
              class="my-4"
              :length="Math.ceil(length / 6)"
              :total-visible="4"
            ></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  props: {
    page: {
      type: Number,
      required: true,
    },
    length: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      currentPage: 1,
      
    };
  },
  methods: {
    updatePage(event){
        //this.$emit('update:page', event.target.innerText)
        this.page = event.target.innerText
        console.log(this.page)
    },
    handlePageChange () {
        console.log(this.page)
        //this.$emit('update:page', this.currentPage)
    },
    emitPageChange(event) {
      this.$emit('update:page', event.target.innerText);
    }
  },
};
</script>
