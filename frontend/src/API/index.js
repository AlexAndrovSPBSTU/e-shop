import { getJWT } from "@/utils"
import { mockCart, mockCategories, mockCharacteristics, mockProduct, mockProducts } from './mockData'
const MODE = import.meta.env.MODE
const SERVER = import.meta.env.VITE_SERVER

export async function getProduct(path) {

  if (MODE === 'development') {
    return mockProduct

  } else {
    const res = await fetch(SERVER + path, {
      method: "GET",
    })

    return res.json()
  }
}

export async function getProducts(path) {

  if (MODE === 'development') {
    return mockProducts.slice(0, 5)
  } else {
    const res = await fetch(SERVER + path, {
      method: "GET",
    })

    return res.json()
  }
}

export async function getCurrentBatchProducts(path) {

  if (MODE === 'development') {

    const regex = /[?&]page=(\d+)/
    const match = regex.exec(path)
    const pageValue = match && match[1]

    console.log(pageValue)

    if (pageValue == 1) {
      return mockProducts.slice(0, 5)
    }
    else if (pageValue == 2) {
      return mockProducts.slice(5)
    }
    else {
      return []
    }
  } else {

    const res = await fetch(SERVER + path, {
      method: "GET",
    })

    return res.json()
  }
}

export async function getCatalog() {

  if (MODE === 'development') {
    return mockCategories

  } else {
    const res = await fetch(SERVER + "/categories", {
      method: "GET",
    })

    return res.json()
  }
}

export async function addNewProduct(body, id) {

  await fetch(
    SERVER + "/products/new?categoryId=" + id,
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
    SERVER + "/comments/new?productId=" + id,
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
  )

}

export async function deleteComment(id) {

  await fetch(
    SERVER + "/comments/" + id,
    {
      method: "DELETE",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function deletePhotos(url) {

  await fetch(
    SERVER + "/photos?" + url,
    {
      method: "DELETE",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function addNewPhotos(photos, where, id) {
  //http://localhost:8080/photos/new?commentId=1
  let url = SERVER + "/photos/new?"

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
  )
}

export async function changeProducts(queryParameters, id) {
  await fetch(SERVER + "/products/change/" + id + "?" + queryParameters,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      },
    }
  )
}

export async function getAllProducts(id) {

  if (MODE === 'development') {

    const key = "id"
    return [...new Map(mockProducts.map((item) => [item[key], item])).values()]
  } else {

    let page = 1
    let array = []

    while (true) {

      const res = await fetch(
        SERVER + "/categories/" + id + "/products?page=" + page,
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

    const key = "id"
    return [...new Map(array.map((item) => [item[key], item])).values()]
  }
}

export async function deleteSpecificProduct(id) {

  fetch(SERVER + "/products/" + id, {
    method: "DELETE",
    headers: {
      Authorization: getJWT(),
    },
  })
}

export async function addCategory(name, isDiverged, id) {

  await fetch(
    SERVER + "/categories/new?parentId=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT(),
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
        isDiverged: isDiverged,
      }),
    }
  )
}

export async function deleteCategory(id) {

  await fetch(
    SERVER + "/categories/" + id,
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
    SERVER + "/categories/rename/" + id + "?newName=" + name,
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
    SERVER + "/categories/insert/" + idChild + "?parentId=" + idParent,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function getCharacteristic(id, isRange) {

  if (MODE === 'development') {
    return mockCharacteristics
  } else {
    let url = isRange
      ? SERVER + "/categories/" + id + "/characteristics" + isRange
      : SERVER + "/categories/" + id + "/characteristics"

    const res = await fetch(url, {
      method: "GET",
    })
    return res.json()
  }
}

export async function addCharacteristic(body, id) {

  await fetch(
    SERVER + "/characteristics/new?categoryId=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT(),
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }
  )
}

export async function deleteCharacteristic(id) {

  await fetch(
    SERVER + "/characteristics/" + id,
    {
      method: "DELETE",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function renameCharacteristic(name, id) {

  await fetch(
    SERVER + "/characteristics/rename/" + id + "?newName=" + name,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function pushNewProperty(body, id) {

  await fetch(
    SERVER + "/properties/new?characteristicId=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT(),
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }
  )
}

export async function deleteProperty(id) {

  await fetch(
    SERVER + "/properties/" + id,
    {
      method: "DELETE",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function renameProperty(name, id) {

  await fetch(
    SERVER + "/properties/rename/" + id + "?newValue=" + name,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function addPropertyInProduct(idProduct, value) {

  await fetch(
    SERVER + "/products/" + idProduct + "/addProperty?" + value,
    {
      method: "PATCH",
      headers: {
        Authorization: getJWT(),
      }
    }
  )
}

export async function addToCard(id) {
  //http://localhost:8080/add?id=2

  await fetch(
    SERVER + "/add?id=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT()
      }
    }
  )
}

export async function reduceItemOnCard(id) {
  //http://localhost:8080/add?id=2

  await fetch(
    SERVER + "/reduce?id=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT()
      }
    }
  )
}

export async function deleteItemsOnCard(id) {
  //http://localhost:8080/add?id=2

  await fetch(
    SERVER + "/delete?id=" + id,
    {
      method: "POST",
      headers: {
        Authorization: getJWT()
      }
    }
  )
}

export async function getCart() {

  if (MODE === 'development') {
    return mockCart

  } else {

    const res = await fetch(
      SERVER + "/cart",
      {
        method: "GET",
        headers: {
          Authorization: getJWT(),
        }
      }
    )
    return res.json()
  }
}

export async function buyCart(queryIds) {
  const response = await fetch(
    SERVER + "/buy?" + queryIds,
    {
      method: "POST",
      headers: {
        Authorization: getJWT(),
      },
    }
  )

  if (!response.ok) {
    const errorBody = await response.json()
    return errorBody
  }

  return { ok: "ok" }
}

export async function register(user) {
  const res = await fetch(SERVER + "/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  })

  return res
}

export async function login(user) {
  const res = await fetch(SERVER + "/authenticate", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  })

  return res
}