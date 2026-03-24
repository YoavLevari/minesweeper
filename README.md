# 🟦 Minesweeper — Java Console Edition

A fully playable Minesweeper game implemented in Java.  
This project includes both the **first attempt** at the game logic (`Field`) and the **final, improved implementation** (`Field2`) used by the main game loop (`Play2`). The game runs entirely in the console and allows players to choose board size, number of mines, and interact with the grid using typed coordinates.

---

## 🎮 Gameplay Overview

When the program starts, the player is prompted to enter:

- The board width
- The board height
- The number of mines

The first click is always guaranteed to be safe.  
After that, the player continues selecting cells until:

- All safe cells are revealed → **Victory**
- A mine is clicked → **Game Over**

At the end of the game, the full mine layout is displayed.

---
# 🔮 Future Improvements

This project is designed with expansion in mind. Below are the planned enhancements that will significantly improve gameplay, usability, and visual clarity.

---

## 🎨 1. Graphical User Interface (GUI)

A full visual Minesweeper experience using **Java Swing** or **JavaFX**, including:

- Clickable buttons for each cell
- Color‑coded numbers
- Real‑time updates
- A cleaner, more intuitive layout
- Visual flags and animations

This will transform the game from a console tool into a polished desktop application.

---

## 🟦 2. Flood‑Fill Reveal for Zero Cells

Classic Minesweeper behavior:

- When a cell with **0 neighboring mines** is clicked, automatically reveal all surrounding cells
- Recursively expand until all empty regions are uncovered
- Greatly improves playability and reduces repetitive clicking

This is one of the most important upgrades for a smooth user experience.

---

## 🚩 3. Ability to Flag Mines

Add support for:

- Marking suspected mines with a flag
- Preventing flagged cells from being clicked
- Tracking remaining mines
- Displaying flags visually in the GUI version

This brings the game closer to the traditional Minesweeper ruleset.

---

## ⏱️ 4. Timer and Score Tracking

Optional enhancements:

- Track how long each game takes
- Store best times
- Add difficulty presets (Beginner, Intermediate, Expert)

---

## 🧠 Project Structure

    src/
    └── main/java/org/example/
    ├── Main.java        # Entry point
    ├── Play2.java       # Console game loop
    ├── Field2.java      # Final game logic
    ├── Mine.java        # Represents a single cell
    ├── Position.java    # Row/column coordinate
    ├── Play.java        # First attempt (kept for reference)
    └── Field.java       # First attempt (kept for reference)


---

## 🚀 Running the Program

### Using Maven

From the project root:

```bash
mvn clean compile exec:java
