import { S3Client } from "@aws-sdk/client-s3";
// Установка региона Object Storage
const REGION = "ru-central1";
// Установка эндпоинта Object Storage
const ENDPOINT = "https://storage.yandexcloud.net";

const KEY_ID = "YCAJEXyMZNiN1e-Ag5J6oT3Ww";
const SECRET_KEY = "YCOxd4OshmUVP2p0VN_poBDwuculuJquoeb41hO7";

// Создание клиента для Object Storage
const s3Client = new S3Client(
    { 
        region: REGION, 
        endpoint: ENDPOINT, 
        credentials: {
            accessKeyId: KEY_ID,
            secretAccessKey: SECRET_KEY
        }  
});
export { s3Client };