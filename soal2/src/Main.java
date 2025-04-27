// Main.java

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<ClassA> daftarKendaraan = new ArrayList<>();
        String lanjut = "";

        System.out.println("======= Welcome to ParkingChan =======");

        do {
            System.out.print("Enter vehicle type (Motor/Mobil/Truk): ");
            String jenis = input.nextLine();
            ClassA kendaraan = new ClassA(jenis);

            System.out.print("Enter Duration (Manual/Time): ");
            String metode = input.nextLine();

            if (metode.equalsIgnoreCase("Manual")) {
                System.out.print("Enter Duration (in hour): ");
                int durasi = input.nextInt();
                kendaraan.hitungBiaya(durasi);
            } else if (metode.equalsIgnoreCase("Time")) {
                System.out.print("Enter entry time: ");
                int jamMasuk = input.nextInt();
                System.out.print("Enter exit time: ");
                int jamKeluar = input.nextInt();
                kendaraan.hitungBiaya(jamMasuk, jamKeluar);
            } else {
                System.out.println("Invalid input method!");
                continue;
            }

            daftarKendaraan.add(kendaraan);
            input.nextLine(); // konsumsi enter

            kendaraan.tampilRingkasan();

            System.out.print("\nAdd another vehicle? (y/n): ");
            lanjut = input.nextLine();

        } while (lanjut.equalsIgnoreCase("y"));

        // Ringkasan akhir
        double totalBiaya = 0;
        for (ClassA k : daftarKendaraan) {
            totalBiaya += k.getBiayaParkir();
        }

        System.out.println("\n======= FINAL REPORT =======");
        System.out.println("Total Vehicle Final : " + daftarKendaraan.size());
        System.out.println("Total Parking Fees Final : Rp" + totalBiaya);
        System.out.println("Thank You.....");

        input.close();
    }
}
