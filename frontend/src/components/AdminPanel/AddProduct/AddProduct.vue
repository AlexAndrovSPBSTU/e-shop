<template>
  
</template>

<script>
import uploadS3 from "@/S3_API/index.js";

export default {
  props: {
    AddDialog: {
      type: Boolean,
      required: true,
    },
  },

  data: () => ({
    product: {
      name: null,
      price: null,
      amount: null,
      description: null,
      photos: [],
      discount: null,
    },

    form: false,
    loading: false,
    alert: false,

    rules: {
      required: (value) => !!value || "Поле должно быть заполнено",
    },
  }),

  methods: {
    async write() {
      if (!this.form) return;

      this.loading = true;

      let url = await uploadS3(this.product.photos);
      console.log(url);

      this.product.name = null;
      this.product.price = null;
      this.product.amount = null;
      this.product.description = null;
      this.product.discount = null;
      this.product.photos = [];

      this.loading = await false;

      this.alert = await true;
      await setTimeout(() => (this.alert = false), 2000);
    },
  },
};
</script>

<style>
.alert {
  position: absolute !important;
  z-index: 99999999;
  bottom: 557px;
  animation: ani 2s cubic-bezier(0.12, 0.44, 0.46, 0.99);
}

@keyframes ani {
  0%,
  100% {
    opacity: 0;
  }
  20% {
    opacity: 1;
  }
  50% {
    opacity: 1;
  }
}
</style>
