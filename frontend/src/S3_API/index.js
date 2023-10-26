import EasyYandexS3 from "easy-yandex-s3";

// Инициализация
let s3 = new EasyYandexS3({
  auth: {
    accessKeyId: "YCAJEXyMZNiN1e-Ag5J6oT3Ww",
    secretAccessKey: "YCOxd4OshmUVP2p0VN_poBDwuculuJquoeb41hO7",
  },
  Bucket: "e-shop", // например, "my-storage",
  debug: false, // Дебаг в консоли, потом можете удалить в релизе
});

export default async function upload() {
  let upload = await s3.Upload({
    path: "../assets/14.PNG",
  });
  console.log(upload);
}
