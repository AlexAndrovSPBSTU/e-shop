package ru.alexandrov.backend.constants;

public interface ProjectConstants {
    String ROLE_USER = "ROLE_USER";
    String ROLE_ADMIN = "ROLE_ADMIN";
    String AMOUNT_STATUS_IN_STOCK = "В наличии";
    String AMOUNT_STATUS_OUT_OF_STOCK = "Нет в наличии";
    String AMOUNT_STATUS_FEW = "Мало";
    String JWT_KEY = "CU11iT9!dkKnxI6-sKWoOeMzpEoRHbV!sRo=/j0V/KkFVCQa1fZnnT0h7i8K2iwaEOlHU4c9HicA4fny3CQFX0nx3IFPghpCSrW=!8VhsiExIHsQm1teXVNdFdXA4!/0";
    String JWT_HEADER = "Authorization";
    int ONE_DAY = 3600_000 * 24;
    String JWT_ISSUER = "e-shop";
    String SUBJECT = "JWT Token";
    String EMAIL = "email";
    String ROLE = "role";
    int PAGE_SIZE = 5;

    String[] ADMIN_ENDPOINTS = {
            "/photos",

            "/comments/{comment_id}",

            "/characteristics/new",
            "/characteristics/rename/{characteristic_id}",
            "/characteristics/{characteristic_id}",

            "/categories/insert/{category_id}",
            "/categories/new",
            "/categories/rename/{category_id}",
            "/categories/{category_id}",

            "/properties/new",
            "/properties/rename/{property_id}",
            "/properties/{property_id}",

            "/products/change/{product_id}",
            "/products/new",
//            "/products/{product_id}" есть такой же эндпоинт но с методом GET, поэтому его надо указать явно
            "/products/{product_id}/addProperty"

    };

    //Список эндпоинтов, не требующих аутентификации
    String[][] WHITE_LIST = {
            {
                    "^/register$",
                    "^/authenticate$",
                    "^/categories$",
                    "^/products/\\d+$",
                    "^/categories/\\d+/characteristics$",
                    "^/categories/\\d+/products$"
            },
            {
                    "/register",
                    "/authenticate",
                    "/categories",
                    "/products/{product_id}",
                    "/categories/{category_id}/characteristics",
                    "/categories/{category_id}/products"
            }
    };
}
