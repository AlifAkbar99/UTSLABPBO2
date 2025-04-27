// ClassA.java

public class ClassA {
    private String jenis;
    private int lamaParkir;
    private double biayaParkir;

    // Constructor menerima jenis kendaraan
    public ClassA(String jenis) {
        this.jenis = jenis;
    }

    // Method menghitung biaya parkir berdasarkan manual input
    public void hitungBiaya(int durasi) {
        this.lamaParkir = durasi;
        this.biayaParkir = hitungTarif(durasi);
    }

    // Method menghitung biaya parkir berdasarkan jam masuk dan keluar
    public void hitungBiaya(int jamMasuk, int jamKeluar) {
        int durasi = jamKeluar - jamMasuk;
        if (durasi < 0) {
            durasi += 24; // Misal jam masuk sore, keluar lewat tengah malam
        }
        this.lamaParkir = durasi;
        this.biayaParkir = hitungTarif(durasi);
    }

    // Method bantu untuk menghitung tarif berdasarkan jenis kendaraan
    private double hitungTarif(int durasi) {
        double tarifPerJam = 0;

        if (jenis.equalsIgnoreCase("Motor")) {
            tarifPerJam = 2000;
        } else if (jenis.equalsIgnoreCase("Mobil")) {
            tarifPerJam = 5000;
        } else if (jenis.equalsIgnoreCase("Truk")) {
            tarifPerJam = 9000;
        }

        double total = tarifPerJam * durasi;

        // Diskon 10% jika lama parkir lebih dari 5 jam
        if (durasi > 5) {
            total *= 0.9;
        }
        return total;
    }

    // Menampilkan ringkasan parkir
    public void tampilRingkasan() {
        System.out.println("---- PARKING SUMMARY ----");
        System.out.println("Vehicle Type : " + jenis);
        System.out.println("Parking Time : " + lamaParkir + " hour(s)");
        System.out.println("Total Fee    : Rp" + biayaParkir);
    }

    public double getBiayaParkir() {
        return biayaParkir;
    }
}
