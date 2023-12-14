<template>
  <div class="">

    <div class="card card-container">
        <div class="title">Регистрация</div>
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <Form @submit="handleRegister" :validation-schema="schema">
        <div v-if="!successful">
          <div class="form-group">
            <label for="name">Имя</label>
            <Field name="name" type="text" class="form-control" />
            <ErrorMessage name="name" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="surname">Фамилия</label>
            <Field name="surname" type="text" class="form-control" />
            <ErrorMessage name="surname" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="email">Почта</label>
            <Field name="email" type="email" class="form-control" />
            <ErrorMessage name="email" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="password">Пароль</label>
            <Field name="password" type="password" class="form-control" />
            <ErrorMessage name="password" class="error-feedback" />
          </div>

          <div class="form-group">
            <v-btn class="btn" type="submit"> Отправить </v-btn>
          </div>
        </div>
      </Form>

      <div
        v-if="message"
        class="alert__register"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
        <div class="go-to-login">
          <router-link to="/login" class="go-to-login__link">Авторизоваться</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";

export default {
  name: "Register",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      name: yup
        .string()
        .required("Это поле обязательно")
        .min(2, "Должно быть минимум два символа!")
        .max(100, "Должно быть максимум сто символов!"),
      surname: yup
        .string()
        .required("Это поле обязательно")
        .min(2, "Должно быть минимум два символа!")
        .max(100, "Должно быть максимум сто символов!"),
      email: yup
        .string()
        .required("Это поле обязательно")
        .email("Неверный формат почты")
        .max(100, "Должно быть максимум сто символов!"),
      password: yup
        .string()
        .required("Это поле обязательно")
        .min(2, "Должно быть минимум два символа!")
        .max(100, "Должно быть максимум сто символов!"),
    });

    return {
      successful: false,
      loading: false,
      message: "",
      schema,
    };
  },

  methods: {
    handleRegister(user) {
      this.message = "Вы были успешно зарегистрированы!!!";
      this.successful = true;
      this.loading = false;

      console.log(JSON.stringify(user))

      fetch("http://localhost:8080/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user),
      }).catch(reason => console.log(reason))
      
    },
  },
};
</script>

<style src="@/styles/auth.scss"></style>