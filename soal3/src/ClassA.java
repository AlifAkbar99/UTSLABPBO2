// ClassA.java

import java.util.Random;

public class ClassA {
    private char[][] board;
    private boolean[][] revealed;
    private int[][] data;
    private int rows = 4;
    private int cols = 5;

    public ClassA() {
        board = new char[rows][cols];
        revealed = new boolean[rows][cols];
        data = new int[rows][cols];
        generateBoard();
    }

    // Generate papan dan taruh 2 bom secara acak
    public void generateBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '*';
                revealed[i][j] = false;
                data[i][j] = 0; // 0 = aman
            }
        }

        // Taruh 2 bom secara random
        Random rand = new Random();
        int bomCount = 0;
        while (bomCount < 2) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (data[r][c] == 0) {
                data[r][c] = 1; // 1 = bom
                bomCount++;
            }
        }
    }

    // Tampilkan papan saat ini
    public void displayBoard() {
        System.out.println();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    if (data[i][j] == 1) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Tebakan pemain
    public boolean guess(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Tebakan di luar batas!");
            return true;
        }
        if (revealed[row][col]) {
            System.out.println("Kotak telah dibuka sebelumnya!");
            return true;
        }

        revealed[row][col] = true;
        if (data[row][col] == 1) {
            board[row][col] = 'X';
            return false; // Kena bom
        } else {
            board[row][col] = 'O';
            return true; // Aman
        }
    }

    // Cek apakah permainan selesai
    public boolean isGameOver() {
        int amanTerbuka = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] == 0 && revealed[i][j]) {
                    amanTerbuka++;
                }
            }
        }
        return amanTerbuka == 18;
    }
}
