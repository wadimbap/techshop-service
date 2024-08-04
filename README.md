# TechShop Service API

`TechShop Service` предоставляет RESTful API для управления продуктами в магазине. Ниже приведено описание доступных конечных точек API, а также примеры запросов и ответов. 

Кроме того, вы можете просмотреть и протестировать все конечные точки API через интерфейс Swagger UI, который предоставляет удобный визуальный интерфейс для взаимодействия с API.

## Сборка проекта

1. Клонируйте репозиторий:

    ```sh
    git clone https://github.com/wadimbap/techshop-service.git
    cd techshop-service
    ```

2. В корневой папке проекта выполните команду:

    ```sh
    docker-compose build
    ```
2. Затем запустите:

    ```sh
    docker-compose up
    ```

## Доступ к Swagger UI

Для удобства работы с API, проект `TechShop Service` включает Swagger UI. Swagger UI позволяет вам просматривать описание API, тестировать запросы и получать информацию о возможных ответах прямо из браузера.

### Как получить доступ

1. **Запустите приложение** и убедитесь, что оно работает. Swagger UI будет доступен, когда приложение запущено.
2. Откройте веб-браузер и перейдите по следующему адресу:

    ```
    http://localhost:8080/swagger-ui.html
    ```

    Здесь `localhost` и `8080` - это стандартный адрес и порт. Если ваше приложение работает на другом порту, замените его в URL.

3. **Просматривайте** и **тестируйте** конечные точки API через интерфейс Swagger UI.

## Основные Конечные Точки

### 1. Получение всех продуктов

- **URL:** `/api/v1/products`
- **Метод:** `GET`
- **Описание:** Возвращает список всех продуктов.
- **Пример запроса:**

    ```http
    GET /api/v1/products
    ```

- **Пример ответа:**

    ```json
    [
      {
        "id": 1,
        "serialNumber": "12345",
        "manufacturer": "Test Manufacturer",
        "price": 100.0,
        "quantity": 10,
        "formFactor": "ATX"
      }
    ]
    ```

### 2. Получение продукта по ID

- **URL:** `/api/v1/products/{id}`
- **Метод:** `GET`
- **Описание:** Возвращает продукт с указанным `id`.
- **Параметры пути:**
  - `id` - ID продукта.
- **Пример запроса:**

    ```http
    GET /api/v1/products/1
    ```

- **Пример ответа:**

    ```json
    {
      "id": 1,
      "serialNumber": "12345",
      "manufacturer": "Test Manufacturer",
      "price": 100.0,
      "quantity": 10,
      "formFactor": "ATX"
    }
    ```

- **Ошибки:**
  - `404 Not Found` - Если продукт с указанным `id` не найден.

### 3. Создание нового продукта

- **URL:** `/api/v1/products`
- **Метод:** `POST`
- **Описание:** Создает новый продукт.
- **Тело запроса:**

    ```json
    {
      "serialNumber": "12345",
      "manufacturer": "Test Manufacturer",
      "price": 100.0,
      "quantity": 10,
      "formFactor": "ATX"
    }
    ```

- **Пример запроса:**

    ```http
    POST /api/v1/products
    Content-Type: application/json

    {
      "serialNumber": "12345",
      "manufacturer": "Test Manufacturer",
      "price": 100.0,
      "quantity": 10,
      "formFactor": "ATX"
    }
    ```

- **Пример ответа:**

    ```json
    {
      "id": 1,
      "serialNumber": "12345",
      "manufacturer": "Test Manufacturer",
      "price": 100.0,
      "quantity": 10,
      "formFactor": "ATX"
    }
    ```

### 4. Обновление продукта

- **URL:** `/api/v1/products/{id}`
- **Метод:** `PUT`
- **Описание:** Обновляет существующий продукт по `id`.
- **Параметры пути:**
  - `id` - ID продукта для обновления.
- **Тело запроса:**

    ```json
    {
      "serialNumber": "12345",
      "manufacturer": "Updated Manufacturer",
      "price": 120.0,
      "quantity": 15,
      "formFactor": "ATX"
    }
    ```

- **Пример запроса:**

    ```http
    PUT /api/v1/products/1
    Content-Type: application/json

    {
      "serialNumber": "12345",
      "manufacturer": "Updated Manufacturer",
      "price": 120.0,
      "quantity": 15,
      "formFactor": "ATX"
    }
    ```

- **Пример ответа:**

    ```json
    {
      "id": 1,
      "serialNumber": "12345",
      "manufacturer": "Updated Manufacturer",
      "price": 120.0,
      "quantity": 15,
      "formFactor": "ATX"
    }
    ```

- **Ошибки:**
  - `404 Not Found` - Если продукт с указанным `id` не найден.

### 5. Удаление продукта

- **URL:** `/api/v1/products/{id}`
- **Метод:** `DELETE`
- **Описание:** Удаляет продукт с указанным `id`.
- **Параметры пути:**
  - `id` - ID продукта для удаления.
- **Пример запроса:**

    ```http
    DELETE /api/v1/products/1
    ```

- **Пример ответа:**

    ```json
    {
      "message": "Product deleted successfully"
    }
    ```

- **Ошибки:**
  - `404 Not Found` - Если продукт с указанным `id` не найден.

## Обработка Ошибок

Все ошибки API возвращаются с соответствующим HTTP статусом и JSON объектом, содержащим сообщение об ошибке:

- **404 Not Found:**

    ```json
    {
      "message": "Product not found"
    }
    ```

- **400 Bad Request:**

    ```json
    {
      "message": "Invalid request data"
    }
    ```
