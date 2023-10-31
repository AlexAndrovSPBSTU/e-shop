// Импорт клиентов и команд AWS SDK для работы с Node.js
import { PutObjectCommand } from "@aws-sdk/client-s3";
import { s3Client } from "./s3Client.js";
import multer from 'multer';
import express from 'express';
import cors from 'cors';
import { v4 as uuidv4 } from 'uuid';


let app = express();
app.use(multer().any());
app.use(cors());

// Установка параметров
const params = {
  Bucket: "e-shop", // Имя бакета, например 'sample-bucket-101'.
  Key: undefined, // Имя объекта, например 'sample_upload.txt'.
  Body: undefined, // Содержимое объекта, например 'Hello world!".
  ContentType: undefined,
};

app.get("/", function(request, response){
     
  // отправляем ответ
  response.send("<h2>Привет Express!</h2>");
});

app.post('/uploadFile',async (req, res) => {


  //try {
    //let str = req.files[0].name
    //console.log(req)
    console.log(req.files)
    //console.log(req.files[0])
    //params.Body = req.files[0].buffer; // Буфер загруженного файла
    //params.Key = uuidv4()+"."+str.slice(6)
    //params.Key = req.files[0].originalname
    //params.ContentType = req.files[0].mimetype
    
/*
    let upload = await s3Client.send(new PutObjectCommand(params));
    console.log(
        "Successfully created " +
        params.Key +
        " and uploaded it to " +
        params.Bucket +
        "/" +
        params.Key
    );
    res.send(upload); // Ответ сервера - ответ от Yandex Object Storage
  } catch (err) {
    console.log("Error", err);
  }//https://storage.yandexcloud.net/e-shop/123.jpg
*/
  
});


const PORT = 8001;

app.listen(PORT, () => console.log('server started on PORT ' + PORT))