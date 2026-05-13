📱 TapGame (libGDX Reaction Game)
🇬🇧 English Version
🎮 Description

TapGame is a simple but fast-paced reaction game developed using libGDX in Java.
The player must tap circles that appear randomly on the screen before they disappear.

The game becomes progressively harder over time, introducing obstacles and power-ups.

This project was developed as part of a university course on mobile application development.

🚀 Features
Random circle spawning system
Increasing difficulty over time
Score system (+1 per correct tap)
Timer (survival time)
Obstacles (cause game over if tapped)
Power-ups (temporary invulnerability)
Ripple animation effect on tap
Sound effects (tap feedback)
Multiple screens:
Main Menu
Game Screen
Game Over Screen
Data persistence (best score and time saved locally)
🧠 What We Learned
How to use libGDX framework for cross-platform game development
Game architecture using Game + Screen system
Real-time game loop using render() and delta time
Handling input (touch & mouse)
Object management using arrays
Collision detection (distance-based)
Rendering with:
ShapeRenderer (for circles)
SpriteBatch (for UI)
Animation systems (ripple effects, color transitions)
Difficulty scaling over time
Saving data using Preferences
Managing game states and screen transitions
🧱 Project Structure
TapGame.java → Main game controller
MainMenuScreen.java → Start screen
GameScreen.java → Core gameplay logic
GameOverScreen.java → End screen + stats
Circle.java → Game entity (normal, obstacle, power-up)
Lwjgl3Launcher.java → Desktop launcher
⚙️ Technologies Used
Java
libGDX
OpenGL (via libGDX)
Gradle
📚 Academic Context

This project follows Option C (libGDX game) requirements:

Screen-based architecture (Game + Screen)
Timer and best time tracking
Obstacle counter and best score saving
Power-ups with temporary invulnerability
▶️ How to Run
Clone the repository
Open with Android Studio
Run:
Desktop version → Lwjgl3Launcher
Android version → via emulator or device
🔮 Possible Improvements
Better UI / graphics (textures instead of shapes)
Sound/music system expansion
More power-ups and mechanics
Pause menu
Online leaderboard
🇪🇸 Versión en Español
🎮 Descripción

TapGame es un juego de reflejos desarrollado con libGDX en Java.
El jugador debe pulsar círculos que aparecen aleatoriamente antes de que desaparezcan.

La dificultad aumenta progresivamente, añadiendo obstáculos y power-ups.

Este proyecto fue realizado como parte de una asignatura universitaria de desarrollo de aplicaciones móviles.

🚀 Características
Generación aleatoria de círculos
Aumento de dificultad con el tiempo
Sistema de puntuación (+1 por acierto)
Temporizador (tiempo de supervivencia)
Obstáculos (provocan game over)
Power-ups (invulnerabilidad temporal)
Animación de ondas (ripple) al pulsar
Efectos de sonido
Sistema de pantallas:
Menú principal
Pantalla de juego
Pantalla de fin de partida
Guardado de récords (tiempo y puntuación)
🧠 Qué hemos aprendido
Uso del framework libGDX para desarrollo multiplataforma
Arquitectura basada en Game y Screen
Bucle de juego con render() y delta time
Gestión de input (pantalla táctil y ratón)
Manejo de colecciones de objetos (Array)
Detección de colisiones (distancia)
Renderizado con:
ShapeRenderer (figuras)
SpriteBatch (texto/UI)
Animaciones (ondas, cambios de color)
Escalado de dificultad
Persistencia de datos (Preferences)
Gestión de estados del juego
🧱 Estructura del proyecto
TapGame.java → Controlador principal
MainMenuScreen.java → Menú inicial
GameScreen.java → Lógica principal del juego
GameOverScreen.java → Resultados
Circle.java → Entidad del juego
Lwjgl3Launcher.java → Lanzador de escritorio
⚙️ Tecnologías utilizadas
Java
libGDX
OpenGL
Gradle
📚 Contexto académico

El proyecto cumple con la Opción C (libGDX):

Sistema de pantallas (Game + Screen)
Temporizador + mejor tiempo guardado
Contador de obstáculos + récord
Power-ups con invulnerabilidad
▶️ Cómo ejecutar
Clonar el repositorio
Abrir en Android Studio
Ejecutar:
Desktop → Lwjgl3Launcher
Android → emulador o dispositivo
🔮 Mejoras futuras
Mejor interfaz gráfica (texturas en lugar de shapes)
Música y más sonidos
Más tipos de power-ups
Menú de pausa
Ranking online
