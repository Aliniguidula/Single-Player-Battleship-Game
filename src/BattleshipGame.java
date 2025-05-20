import java.util.InputMismatchException;
import java.util.Scanner;

public class BattleshipGame {
    private Ocean ocean;
    private Scanner input;

    public BattleshipGame() {
        this.ocean = new Ocean();
        this.input = new Scanner(System.in);
        ocean.placeAllShipsRandomly();
    }

    public void play() {
        printGreeting();
        while (!ocean.isGameOver()) {
            int[] coordinates = getCoordinates();
            addShot(coordinates[0], coordinates[1]);
        }
        printEnding();
    }


    private void printGreeting() {
        System.out.println("""
            ╔════════════════════════════════════════╗
            ║            🌊 𝔹𝔸𝕋𝕋𝕃𝔼𝕊ℍ𝕀ℙ 🌊            ║
            ╚════════════════════════════════════════╝
            """);
        System.out.println("The seas are yours to conquer! Your fleet awaits your orders.");
        System.out.println("Your mission: Seek and destroy the enemy fleet.");
        System.out.println("Good hunting, Captain! \uD83D\uDEF3\uFE0F\n" + "\n");
        ocean.print();
    }

    private int[] getCoordinates() {
        while (true) {
            try {
                System.out.println("Enter your shot \uD83C\uDFAF");
                System.out.print("Enter row (0-9): ");
                int row = input.nextInt();
                System.out.print("Enter column (0-9): ");
                int col = input.nextInt();

                if (isValidInput(row, col)) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Invalid input. Coordinates must be between 0 and 9. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Make sure to enter numbers. Try again.");
                input.next();
            }
        }
    }

    private boolean isValidInput(int row, int col) {
        return row >= 0 && row <= 9 && col >= 0 && col <= 9;
    }

    private void addShot(int row, int column) {
        Ship shipAtLocation = ocean.getShipArray()[row][column];

        if (ocean.shootAt(row, column)) {
            boolean stillStanding = shipAtLocation.shootAt(row, column);
            ocean.print();
            System.out.println("Hit!");
            if (!stillStanding) {
                String shipType = shipAtLocation.getShipType();
                System.out.printf("You just sunk a %s! \n", shipType);
            }
        } else {
            ocean.print();
            System.out.println("Miss!");
        }
    }

    private void printEnding() {
        System.out.println();
        System.out.println("Game over! All enemy ships have been sunk.");
        System.out.println("=========================================");
        System.out.println("         🚢 BATTLESHIP RESULTS 🚢         ");
        System.out.println("=========================================");
        System.out.printf("          Shots fired ------ %3d  \n", ocean.getShotsFired());
        System.out.printf("          Hit count -------- %3d  \n", ocean.getHitCount());
        System.out.println("=========================================");
        System.out.println("     🌊 Thanks for playing Battleship! 🌊 ");
        System.out.println("=========================================");
    }
}