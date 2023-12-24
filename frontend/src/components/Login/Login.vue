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
      </Form>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import { login } from "@/API/index.js";

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
      schema,
    };
  },

  methods: {
    handleLogin(user) {
      this.loading = true;

      login(user)
        .then((response) => {
          if (!response.ok) {
            if (response.status === 401) {
              alert("Неверные учетные данные");
            }
          }
          return response.json();
        })
        .then((data) => {
          this.$store
            .dispatch("login", data)
            .then((_) => this.$router.push("/"));
        })
        .catch((error) => {
          console.error("Ошибка при выполнении fetch:", error);
        });
    },
  },
};
</script>

<style src="@/styles/auth.scss"></style>
