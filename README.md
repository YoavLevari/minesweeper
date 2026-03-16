# 🧨 Minesweeper — Java Console Game

A fully interactive **Minesweeper** game implemented in Java.  
The game runs in the terminal and allows the player to choose the board size, number of mines, and reveal cells by entering coordinates. The program handles mine placement, neighbor counting, win/loss detection, and prints the board after every move.

---

## 📘 How the Game Works

When the program starts, the player is prompted to enter:

1. **Board width**  
2. **Board height**  
3. **Number of mines**

After receiving this information, the game:

- Creates a hidden minefield  
- Ensures the **first clicked cell is always safe**  
- Calculates how many mines surround each cell  
- Prints the board after every move  
- Ends when:
  - The player clicks a mine (**loss**)  
  - All safe cells are revealed (**win**)  

### 🕹️ Gameplay Flow

1. The board is printed with all cells hidden (`X`).
2. The player enters a **vertical index** and **horizontal index**.
3. The game reveals the selected cell:
   - If it contains a mine → **Game Over**
   - If it is safe → displays the number of adjacent mines
4. The game continues until the player wins or loses.
5. At the end, the full minefield is printed so the player can see all mine locations.

---

## 🧩 Code Overview

The project is organized into four main classes:

src/main/java/org/example/
├── Play2.java
├── Field2.java
├── Mine.java
└── Position.java

### **`Play2.java` — Game Controller**

This class manages:

- User input (`Scanner`)
- Asking for board size and mine count
- Creating the `Field2` object
- Running the main game loop
- Printing the board after each move
- Detecting win/loss conditions

**Example of the main loop:**

```java
while (!gameOver && field.minesGuessed != validMines) {
    int verticalIndex = scan.nextInt();
    int horizontalIndex = scan.nextInt();
    gameOver = field.chooseMine(verticalIndex, horizontalIndex);
    field.printField();
}
