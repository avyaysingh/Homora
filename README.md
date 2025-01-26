# **Homora - Property Listing Platform**

Homora is a user-friendly platform for property listings, where users can register, list properties, browse available options, and manage their accounts securely.

## **Features:**

- User registration and login using email with OTP verification
- Ability to list, edit, and query properties
- JWT-based authentication for secure access
- Image upload capability for property listings
- Searchable listings with filters for title, location, price, and property type

---

## **Getting Started**

### 1. Clone the repository

```bash
git clone https://github.com/avyaysingh/Homora.git
cd homora
```

### 2. Prerequisites

- Java 17+
- Spring Boot 2.7.0+
- MySQL Database (or any compatible database)
- Environment variables (explained below)

---

## **Environment Setup**

### **1. For VS Code**:

To run the project in VS Code, follow these steps:

- Go to **Run and Debug (Ctrl + Shift + D)** in the menu, and select **Create a launch.json file**.
- In **launch.json**, set up your environment variables:

```json
{
  "type": "java",
  "name": "HomoraApplication",
  "request": "launch",
  "mainClass": "com.avyay.homora.HomoraApplication",
  "projectName": "homora",
  "env": {
    "DATASOURCE_URL": "your-datasource-url",
    "DATASOURCE_USERNAME": "your-username",
    "DATASOURCE_PASSWORD": "your-password",
    "MAIL_USERNAME": "your-email",
    "MAIL_PASSWORD": "your-email-password",
    "JWT_SECRET_KEY": "your-jwt-secret-key"
  }
}
```

- Use **Google App password** for `MAIL_PASSWORD`.

### **2. For IntelliJ IDEA**:

To add environment variables in IntelliJ IDEA, follow these steps:

- Open **Run/Debug Configurations** by clicking on the dropdown at the top right and selecting **Edit Configurations**.
- In the **Run/Debug Configurations** window, under the **Configuration tab**, scroll down to the **Environment Variables** section.
- Click on **Environment Variables** and a dialog will appear.
- Add the following environment variables:

```txt
DATASOURCE_URL=your-datasource-url
DATASOURCE_USERNAME=your-username
DATASOURCE_PASSWORD=your-password
MAIL_USERNAME=your-email
MAIL_PASSWORD=your-email-password
JWT_SECRET_KEY=your-jwt-secret-key
```

---

## **Run the Application**

1. **Create tables**:

   - If you're using **PostgreSQL on Render**, set up the PostgreSQL database and create the necessary tables as per your schema. You can use **DBeaver** (a free database management tool) to connect to your Render PostgreSQL database and manage it effectively.
   - Alternatively, you can use **MySQL Workbench** to set up a MySQL database and create the tables required by the application.

2. **Start the application**: Once all environment variables are set, run the application with the following steps:

   - **In VS Code**:

     - Go to **Run and Debug (Ctrl + Shift + D)** in the menu.
     - Ensure your **launch.json** file is already configured with the environment variables.
     - Click the green **Run** button (or press F5) to start the application in debug mode. The environment variables will be loaded from the `launch.json` configuration.

   - **In IntelliJ IDEA**:
     - Go to **Run > Run 'Homora'** (or the name of your configuration).
     - Ensure **Run with Configuration** is selected, and the environment variables will be loaded automatically.

3. **Access the application**: The app will be running at `http://localhost:8080/`.

---

## **API Endpoints**

### **1. User Registration & Login**

#### **Signup**:

```http
POST /auth/signup
```

Request body: `UserRequest`  
Response: Confirmation message

#### **Verify OTP**:

```http
POST /auth/verify
```

Request parameters:

- `email`: Registered email address
- `otp`: OTP received via email

#### **Login**:

```http
POST /auth/login
```

Request body: `LoginRequest`  
Response: JWT Token

---

### **2. Property Endpoints**

#### **Create a Property**:

```http
POST /api/properties
```

Request body:

- `CreatePropertyRequest`: Property details
- `Authorization`: JWT token from login

#### **Edit a Property**:

```http
PUT /api/properties/{id}
```

Request body:

- `CreatePropertyRequest`: Updated property details
- `Authorization`: JWT token from login

#### **Get All Properties**:

```http
GET /api/properties
```

Request parameters:

- `title`: Property title (optional)
- `location`: Property location (optional)
- `minPrice`: Minimum price (optional)
- `maxPrice`: Maximum price (optional)
- `type`: Property type (optional)
- `page`: Page number (optional)
- `size`: Number of listings per page (optional)

---

## **Built With**

- **Java** (Spring Boot)
- **MySQL** (or compatible database)
- **JWT** (for authentication)

---

## **License**

By following this README, you'll be able to get your **Homora** project up and running locally for development purposes. If you have any issues, feel free to open an issue on the repository.
