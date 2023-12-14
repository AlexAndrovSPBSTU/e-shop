import { getJWT } from "@/utils"


export async function getProduct(path) {

  const res = await fetch("http://localhost:8080" + path, {
    method: "GET",
  })

  return res.json()
}

export async function getProducts(path) {

  const res = await fetch("http://localhost:8080" + path, {
    method: "GET",
  })

  return res.json()
}

export async function getCurrentBatchProducts(path, query) {


  const res = await fetch("http://localhost:8080" + path, {
    method: "GET",
  })

  return res.json()
}

export async function getCatalog() {

  const res = await fetch("http://localhost:8080/categories", {
    method: "GET",
  })

  return res.json()
}

export async function addNewProduct(body, id) {

  await fetch(
    "http://localhost:8080/products/new?categoryId=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT(),
        "Content-Type": "application/json",
      },
      body: body
    }
  )
}

export async function newComment(id, rating, photos, comment) {

  await fetch(
    "http://localhost:8080/comments/new?productId=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT(),
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        rating: rating,
        photos: photos,
        note: comment,
      }),
    }
  );

}

export async function deleteComment(id) {

  await fetch(
    "http://localhost:8080/comments/" + id,
    {
      method: "DELETE",
      headers: {
        Authorization: getJWT(),
      }
    }
  );
}

export async function deletePhotos(url) {

  await fetch(
    "http://localhost:8080/photos?" + url,
    {
      method: "DELETE",
      headers: {
        Authorization: getJWT(),
      }
    }
  );
}

export async function addNewPhotos(photos, where, id) {
  //http://localhost:8080/photos/new?commentId=1
  let url = "http://localhost:8080/photos/new?"

  where == "comment" ? url += "commentId=" : url += "productId="

  let obj = { photos: photos }

  console.log(JSON.stringify(obj))

  await fetch(
    url + id,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: getJWT(),
      },
      body: JSON.stringify(obj)
    }
  );
}

export async function changeProducts(queryParameters, id) {
  await fetch("http://localhost:8080/products/change/" + id + "?" + queryParameters,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      },
    }
  )
}

export async function getAllProducts(id) {

  let page = 1
  let array = []

  while (true) {

    const res = await fetch(
      "http://localhost:8080/categories/" + id + "/products?page=" + page,
      {
        method: "GET",
      }
    )

    let data = await res.json()

    if (data.length == 0) {
      break
    }
    else {
      page++
      array.push(...data)
    }

  }

  const key = "id";
  return [...new Map(array.map((item) => [item[key], item])).values()];

}

export async function deleteSpecificProduct(id) {

  fetch("http://localhost:8080/products/" + id, {
    method: "DELETE",
    headers: {
      Authorization: getJWT(),
    },
  })
}

export async function addCategory(name, id) {

  await fetch(
    "http://localhost:8080/categories/new?parentId=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT(),
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
      }),
    }
  )
}

export async function deleteCategory(id) {

  await fetch(
    "http://localhost:8080/categories/" + id,
    {
      method: "DELETE",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function renameCategory(name, id) {

  await fetch(
    "http://localhost:8080/categories/rename/" + id + "?newName=" + name,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function moveCategory(idChild, idParent) {

  await fetch(
    "http://localhost:8080/categories/insert/" + idChild + "?parentId=" + idParent,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}