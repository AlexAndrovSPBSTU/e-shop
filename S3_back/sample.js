// Импорт клиентов и команд AWS SDK для работы с Node.js
import { PutObjectCommand } from "@aws-sdk/client-s3";
import { s3Client } from "./s3Client.js";
import multer from "multer";
import express from "express";
import cors from "cors";
import { v4 as uuidv4 } from "uuid";

let app = express();
app.use(multer().any());
app.use(cors());

// Установка параметров

app.post("/uploadFile", (req, res) => {
  let goodStatus = 200

  let urls = []
  req.files.forEach((file) => {
    let str = file.originalname;
    let format = str.slice(str.indexOf("."));

    const params = {
      Bucket: "e-shop",
      Key: uuidv4() + format,
      Body: file.buffer,
      ContentType: file.mimetype,
    };
    //console.log(params)
    let response = uploadFile(params)
    urls.push(params.Key)

    //console.log(urls)
  });


  console.log(goodStatus)
  console.log(urls)
  /*if(errorStatus !== 0){
    res.status(errorStatus).send(errorRes);
  }*/
  res.status(goodStatus).send(urls);
});

async function uploadFile(params) {
  try {
    let upload = await s3Client.send(new PutObjectCommand(params));
    console.log(
      "Successfully created " +
      params.Key +
      " and uploaded it to " +
      params.Bucket +
      "/" +
      params.Key
    );
    return upload
  } catch (err) {
    console.log("Error", err);
  } //https://storage.yandexcloud.net/e-shop/123.jpg
}

const PORT = 8001;

app.listen(PORT, () => console.log("server started on PORT " + PORT));
