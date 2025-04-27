// Main.java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ClassA board = new ClassA();

        System.out.println("Welcome to E-Lottery Gosok");

        boolean mainLanjut = true;

        while (mainLanjut) {
            board.displayBoard();
            System.out.print("Masukkan tebakan anda (baris dan kolom) : ");
            int row = input.nextInt();
            int col = input.nextInt();

            boolean aman = board.guess(row, col);

            if (!aman) {
                board.displayBoard();
                System.out.println("BOOM! Anda menemukan bom! Permainan berakhir.");
                break;
            } else {
                System.out.println("Kotak Aman");
            }

            if (board.isGameOver()) {
                board.displayBoard();
                System.out.println("Selamat anda menang!");
                break;
            }
        }

        input.close();
    }
}
