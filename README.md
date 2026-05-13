# 📱 TapGame (libGDX Reaction Game)

---

## 🇬🇧 English

### 🎮 Description
TapGame is a simple but fast-paced reaction game developed using libGDX in Java.

The player must tap circles that appear randomly on the screen before they disappear.

The game becomes progressively harder over time, introducing obstacles and power-ups.

This project was developed as part of a university course on mobile application development.

---

### 🚀 Features
- Random circle spawning system  
- Increasing difficulty over time  
- Score system (+1 per correct tap)  
- Timer (survival time)  
- Obstacles (cause game over if tapped)  
- Power-ups (temporary invulnerability)  
- Ripple animation effect  
- Sound effects  
- Multiple screens:
  - Main Menu  
  - Game Screen  
  - Game Over Screen  
- Data persistence (best score and time saved locally)

---

### 🧠 What We Learned
- libGDX framework basics  
- Game + Screen architecture  
- Game loop using render() and delta time  
- Input handling (touch & mouse)  
- Collision detection (distance-based)  
- Rendering:
  - ShapeRenderer (game objects)
  - SpriteBatch (UI)
- Animation systems (ripple, color transitions)  
- Difficulty scaling  
- Saving data using Preferences  
- Screen transitions and game states  

---

### 🧱 Project Structure
- `TapGame.java` → Main game controller  
- `MainMenuScreen.java` → Start screen  
- `GameScreen.java` → Core gameplay logic  
- `GameOverScreen.java` → End screen + stats  
- `Circle.java` → Game entity  
- `Lwjgl3Launcher.java` → Desktop launcher  

---

### ⚙️ Technologies Used
- Java  
- libGDX  
- OpenGL  
- Gradle  

---

### 📚 Academic Context
This project follows Option C (libGDX game):
- Game + Screen system  
- Timer + best time saved  
- Obstacle counter + best score saved  
- Power-ups with temporary invulnerability  

---

### ▶️ How to Run
1. Clone the repository  
2. Open with Android Studio  
3. Run:
   - Desktop → `Lwjgl3Launcher`  
   - Android → emulator or device  

---

### 🔮 Possible Improvements
- Better UI (textures instead of shapes)  
- More sound/music  
- More power-ups  
- Pause menu  
- Online leaderboard  

---

## 🇪🇸 Español

### 🎮 Descripción
TapGame es un juego de reflejos desarrollado con libGDX en Java.

El jugador debe pulsar círculos que aparecen aleatoriamente antes de que desaparezcan.

La dificultad aumenta progresivamente, añadiendo obstáculos y power-ups.

Este proyecto fue realizado como parte de una asignatura universitaria.

---

### 🚀 Características
- Generación aleatoria de círculos  
- Aumento de dificultad  
- Sistema de puntuación  
- Temporizador  
- Obstáculos (game over)  
- Power-ups (invulnerabilidad)  
- Animaciones tipo ripple  
- Sonido  
- Sistema de pantallas:
  - Menú  
  - Juego  
  - Game Over  
- Guardado de récords  

---

### 🧠 Qué hemos aprendido
- Uso de libGDX  
- Arquitectura Game + Screen  
- Bucle de juego (render + delta)  
- Gestión de input  
- Detección de colisiones  
- Renderizado (ShapeRenderer y SpriteBatch)  
- Animaciones  
- Escalado de dificultad  
- Persistencia con Preferences  

---

### 🧱 Estructura
- `TapGame.java` → Control principal  
- `MainMenuScreen.java` → Menú  
- `GameScreen.java` → Juego  
- `GameOverScreen.java` → Resultados  
- `Circle.java` → Entidad  
- `Lwjgl3Launcher.java` → Launcher  

---

### ▶️ Ejecución
1. Clonar repositorio  
2. Abrir en Android Studio  
3. Ejecutar:
   - Desktop → `Lwjgl3Launcher`  
   - Android → emulador o móvil  

---

### 🔮 Mejoras futuras
- Mejor interfaz gráfica  
- Más sonidos  
- Más power-ups  
- Menú de pausa  
- Ranking online  
