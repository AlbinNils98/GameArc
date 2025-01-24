# GameArc

GameArc is a platform that allows users to search for any game they want, thanks to its integration with the IGDB API. It also helps users keep track of, rate, comment on, and archive the games they've played. The application is built with a Spring Boot backend and a React frontend, providing a seamless experience for managing your gaming history.

---

## Features

- **Game Search**: Look up information on any game using GameArc’s access to the IGDB API.
- **Ratings & Comments**: Rate games and leave personalized comments.
- **Game Archiving**: Organize your gaming journey and keep a record of games you’ve played.

---

## Prerequisites

Before starting the application, ensure you have the following:

1. **Java 23** (for the Spring Boot backend).
2. **Node.js** and **npm** (for the React frontend, developed with Vite).
3. **Twitch Client-ID** and **IGDB Access-Key** set as environment variables:
   - `IGDB_CLIENT_ID`
   - `IGDB_KEY`
   - See [IGDB API Docs](https://api-docs.igdb.com/#getting-started) for details on obtaining these keys.

---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/AlbinNils98/GameArc  
cd gamearc  
```
---

### 2. Start the Backend (Spring Boot)

### Setting up IGDB Credentials

You can set these variables in the following ways:

1. **Command Line (CMD or PowerShell)**:
    - Before running the Spring Boot app, you can set the variables directly in the terminal:
    
      - **For Windows (CMD)**:
        ```
        set IGDB_KEY=your_igdb_key
        set IGDB_CLIENT_ID=your_igdb_client_id
        ```
      - **For PowerShell**:
        ```
        $env:IGDB_KEY="your_igdb_key"
        $env:IGDB_CLIENT_ID="your_igdb_client_id"
        ```
      - **For Linux/macOS (Bash)**:
        ```
        export IGDB_KEY=your_igdb_key
        export IGDB_CLIENT_ID=your_igdb_client_id
        ```

2. **IDE Configuration**:
   You can also configure the system variables in your IDE (e.g., IntelliJ IDEA or Eclipse) by setting them as environment variables in the run configuration for your Spring Boot application.

3. **Directly in the `application.properties` File** (not recommended for production):
   You can set the values directly in the `application.properties` file like this:
```
   igdb.key=your_igdb_key 
   igdb.client-id=your_igdb_client_id
```
#### Using an IDE:
- Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
- Run the Spring Boot application from the `GameArcApplication` class.

#### Using Maven (Command Line):
```cmd 
mvn spring-boot:run  
```
---

### 3. Start the Frontend (React)

Navigate to the React project directory:
```bash
cd frontend/gamearc_ui  
npm install  
npm start  
```
The frontend should now be available at `http://localhost:5173`.

---

## Running the Application

Once both the backend and frontend are running:
- Access the application in your browser at `http://localhost:5173`.
- Ensure that both the backend (`http://localhost:8080`) and frontend (`http://localhost:5173`) are running concurrently.

---

## Technology Stack

- **Backend**: Spring Boot (Maven-based)
- **Frontend**: React (Vite-powered)
- **Database**: (MySQL)
- **APIs Used**: IGDB API for fetching game data

---

## Future Enhancements

- Integrate the React app directly into the Spring Boot application for a unified build and deployment process.
- Add more features, such as advanced filtering, game recommendations, or user social features.
