export const mockCategories = [
	{
		"id": 2,
		"name": "Смартфоны",
		"isDiverged": false,
		"children": [
			{
				"id": 3,
				"name": "Apple",
				"isDiverged": false,
				"children": [],
				"productAmountOfCategory": 2
			},
			{
				"id": 4,
				"name": "Samsung",
				"isDiverged": false,
				"children": [],
				"productAmountOfCategory": 1
			},
			{
				"id": 5,
				"name": "Дополнительные продукты для смартфонов",
				"isDiverged": true,
				"children": [
					{
						"id": 6,
						"name": "Наушники",
						"isDiverged": false,
						"children": [],
						"productAmountOfCategory": 1
					},
					{
						"id": 7,
						"name": "Чехлы",
						"isDiverged": false,
						"children": [],
						"productAmountOfCategory": 1
					}
				],
				"productAmountOfCategory": 2
			}
		],
		"productAmountOfCategory": 7
	},
	{
		"id": 8,
		"name": "Аудиооборудование",
		"isDiverged": false,
		"children": [
			{
				"id": 6,
				"name": "Наушники",
				"isDiverged": false,
				"children": [],
				"productAmountOfCategory": 1
			},
			{
				"id": 9,
				"name": "Портативные колонки",
				"isDiverged": false,
				"children": [],
				"productAmountOfCategory": 1
			}
		],
		"productAmountOfCategory": 2
	},
	{
		"id": 10,
		"name": "Ноутбуки",
		"isDiverged": false,
		"children": [
			{
				"id": 11,
				"name": "Apple",
				"isDiverged": false,
				"children": [],
				"productAmountOfCategory": 2
			},
			{
				"id": 12,
				"name": "Lenovo",
				"isDiverged": false,
				"children": [],
				"productAmountOfCategory": 2
			}
		],
		"productAmountOfCategory": 4
	}
]

export const mockProducts = [
	{
		"id": 3,
		"name": "Samsung Galaxy S8",
		"price": 40000.0,
		"amount": 15,
		"description": "Samsung Galaxy S8 - это смартфон без рамки, который сочетает в себе все последние технические достижения бренда. Изогнутый с обеих сторон экран с диагональю 5,8 дюйма имеет удлиненный формат (18,5:9),что позволяет разместить виртуальные кнопки. Это также обеспечивает больший комфорт при работе в многоэкранном режиме. Мощный 8-ядерный процессор Exynos 8895 обеспечивает безупречную производительность при снижении энергопотребления на 20%. 4 ГБ оперативной и 64 ГБ встроенной памяти гарантируют бесперебойную работу любых приложений, доступных на рынке.",
		"discount": 0,
		"rating": 0.0,
		"category": "Samsung",
		"properties": {
			"Число ядер": [
				"4",
				"8"
			],
			"Вес": [
				null,
				"0.185"
			],
			"Объем оперативной памяти": [
				"4 GB"
			]
		},
		"photos": [
			{
				"url": "https://storage.yandexcloud.net/e-shop/sum1S8.jpg"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/sumS8.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/sum3S8.png"
			}
		],
		"comments": [],
		"status_amount": "В наличии"
	},
	{
		"id": 4,
		"name": "Наушники Apple AirPods Pro",
		"price": 20000.0,
		"amount": 30,
		"description": "Белый и хороший",
		"discount": 0,
		"rating": 0.0,
		"category": "Наушники",
		"properties": {
			"Вес": [
				"0.3"
			],
			"Версия Bluetooth": [
				"5.0/BLE"
			]
		},
		"photos": [
			{
				"url": "https://storage.yandexcloud.net/e-shop/airpods1.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/airpods2.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/airpods3.png"
			}
		],
		"comments": [],
		"status_amount": "В наличии"
	},
	{
		"id": 5,
		"name": "Чехол Huawei P50",
		"price": 1000.0,
		"amount": 43,
		"description": "Кожаный чехол",
		"discount": 0,
		"rating": 0.0,
		"category": "Чехлы",
		"properties": {
			"Вес": [
				null,
				"0.123"
			],
			"Цвет": [
				"Серый"
			]
		},
		"photos": [
			{
				"url": "https://storage.yandexcloud.net/e-shop/chehol1.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/chehol2.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/chehol3.png"
			}
		],
		"comments": [],
		"status_amount": "В наличии"
	},
	{
		"id": 2,
		"name": "iPhone 13",
		"price": 80000.0,
		"amount": 4,
		"description": "iPhone 13",
		"discount": 0,
		"rating": 0.0,
		"category": "Apple",
		"properties": {
			"Число ядер": [
				"6"
			],
			"Вес": [
				null,
				"0.173"
			],
			"Объем оперативной памяти": [
				"8 GB"
			]
		},
		"photos": [
			{
				"url": "https://storage.yandexcloud.net/e-shop/apple-iphone-12-violet-back.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/14plus.jpg"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/phone.jpg"
			}
		],
		"comments": [],
		"status_amount": "Мало"
	},
	{
		"id": 6,
		"name": "Huawei P50",
		"price": 30000.0,
		"amount": 26,
		"description": "Этот смартфон HUAWEI работает с предустановленной платформой App Gallery, разработанной собственными силами. App Gallery - третий по популярности магазин приложений в мире, предлагающий высококачественные сервисы и приложения. Откройте для себя смартфоны HUAWEI с App Gallery прямо сейчас!",
		"discount": 0,
		"rating": 0.0,
		"category": "Смартфоны",
		"properties": {
			"Число ядер": [
				"8"
			],
			"Вес": [
				"0.194",
				"5556"
			],
			"Объем оперативной памяти": [
				"4 GB"
			]
		},
		"photos": [
			{
				"url": "https://storage.yandexcloud.net/e-shop/p50_1.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/p50_2.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/p50_3.png"
			}
		],
		"comments": [],
		"status_amount": "В наличии"
	},
	{
		"id": 1,
		"name": "iPhone 14",
		"price": 100000.0,
		"amount": 14,
		"description": "Тонкий и легкий смартфон из новой линейки Apple iPhone 14 оснащен OLED-экраном с высоким разрешением, достаточным объемом памяти и увеличенным временем автономной работы в водонепроницаемом корпусе. Усовершенствованные камеры позволяют снимать фотографии и видео в отличном качестве даже при слабом освещении. Функция экстренной помощи со спутниковой связью станет спасательным кругом при отсутствии доступа в Интернет. Мощный процессор открывает новые возможности для игр и развлечений.",
		"discount": 0,
		"rating": 0.0,
		"category": "Apple",
		"properties": {
			"Число ядер": [
				"8"
			],
			"Вес": [
				"0.206"
			],
			"Объем оперативной памяти": [
				"16 GB"
			]
		},
		"photos": [
			{
				"url": "https://storage.yandexcloud.net/e-shop/11pro.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/12iphone.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/iphone11.jpg"
			}
		],
		"comments": [],
		"status_amount": "В наличии"
	},
	{
		"id": 12,
		"name": "iPhone 15",
		"price": 100000.0,
		"amount": 14,
		"description": "Тонкий и легкий смартфон из новой линейки Apple iPhone 14 оснащен OLED-экраном с высоким разрешением, достаточным объемом памяти и увеличенным временем автономной работы в водонепроницаемом корпусе. Усовершенствованные камеры позволяют снимать фотографии и видео в отличном качестве даже при слабом освещении. Функция экстренной помощи со спутниковой связью станет спасательным кругом при отсутствии доступа в Интернет. Мощный процессор открывает новые возможности для игр и развлечений.",
		"discount": 0,
		"rating": 0.0,
		"category": "Apple",
		"properties": {
			"Число ядер": [
				"8"
			],
			"Вес": [
				"0.206"
			],
			"Объем оперативной памяти": [
				"16 GB"
			]
		},
		"photos": [
			{
				"url": "https://storage.yandexcloud.net/e-shop/11pro.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/12iphone.png"
			},
			{
				"url": "https://storage.yandexcloud.net/e-shop/iphone11.jpg"
			}
		],
		"comments": [],
		"status_amount": "В наличии"
	}
]

export const mockProduct = {
	"id": 2,
	"name": "iPhone 13",
	"price": 80000.0,
	"amount": 4,
	"description": "iPhone 13",
	"discount": 0,
	"rating": 0.0,
	"category": "Apple",
	"properties": {
		"Число ядер": [
			"6"
		],
		"Вес": [
			"0.173"
		],
		"Объем оперативной памяти": [
			"8 GB"
		]
	},
	"photos": [
		{
			"url": "https://storage.yandexcloud.net/e-shop/apple-iphone-12-violet-back.png"
		},
		{
			"url": "https://storage.yandexcloud.net/e-shop/14plus.jpg"
		},
		{
			"url": "https://storage.yandexcloud.net/e-shop/phone.jpg"
		}
	],
	"comments": [],
	"status_amount": "Мало"
}

export const mockCart = {
	"products": [
		{
			"productId": 3,
			"name": "Samsung Galaxy S8",
			"description": "Samsung Galaxy S8 - это смартфон без рамки, который сочетает в себе все последние технические достижения бренда. Изогнутый с обеих сторон экран с диагональю 5,8 дюйма имеет удлиненный формат (18,5:9),что позволяет разместить виртуальные кнопки. Это также обеспечивает больший комфорт при работе в многоэкранном режиме. Мощный 8-ядерный процессор Exynos 8895 обеспечивает безупречную производительность при снижении энергопотребления на 20%. 4 ГБ оперативной и 64 ГБ встроенной памяти гарантируют бесперебойную работу любых приложений, доступных на рынке.",
			"totalCount": 2,
			"img": "https://storage.yandexcloud.net/e-shop/sum1S8.jpg",
			"amount": 15,
			"price": 40000,
			"discount": 0
		}
	],
	"totalCount": 2,
	"totalPrice": 80000
}

export const mockCharacteristics = [
	{
		"id": 1,
		"name": "Объем оперативной памяти",
		"isRange": false,
		"properties": [
			{
				"id": 1,
				"value": "8 GB"
			},
			{
				"id": 2,
				"value": "16 GB"
			},
			{
				"id": 3,
				"value": "4 GB"
			}
		]
	},
	{
		"id": 2,
		"name": "Число ядер",
		"isRange": false,
		"properties": [
			{
				"id": 4,
				"value": "4"
			},
			{
				"id": 5,
				"value": "6"
			},
			{
				"id": 6,
				"value": "8"
			}
		]
	},
	{
		"id": 7,
		"name": "Вес",
		"isRange": true,
		"from": 0.0,
		"to": 100000.0,
		"properties": []
	}
]