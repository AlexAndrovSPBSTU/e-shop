<template>
  <div class="">
    <div class="card card-container">
      <div class="title">Авторизация</div>
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <Form @submit="handleLogin" :validation-schema="schema">
        <div class="form-group">
          <label for="email">Username</label>
          <Field name="email" type="email" class="form-control" />
          <ErrorMessage name="email" class="error-feedback" />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <Field name="password" type="password" class="form-control" />
          <ErrorMessage name="password" class="error-feedback" />
        </div>

        <div class="form-group">
          <v-btn class="btn" type="submit"> Отправить </v-btn>
        </div>

        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </div>
      </Form>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import { useStore } from '@/store/index.js'

export default {
  name: "Login",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      email: yup
        .string()
        .email("Неверный формат почты")
        .required("Введите email"),
      password: yup.string().required("Введите пароль"),
    });

    return {
      loading: false,
      message: "",
      schema,
    };
  },
  /*
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },*/
  methods: {
    handleLogin(user) {
      this.loading = true;

      const store = useStore();
      // call the action as a method of the store
      store.login(user).then(() => {
        this.$router.push("/");
      },
      (error) => {
          this.loading = false;
          this.message = "Error!!!"
            ;
        });

      /*
      this.$store.dispatch("auth/login", user).then(
        () => {
          this.$router.push("/profile");
        },
        (error) => {
          this.loading = false;
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );*/
    },
  },
};
</script>

<style src="@/styles/auth.scss"></style>
