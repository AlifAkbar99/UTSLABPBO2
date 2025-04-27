package soal1;

import java.util.ArrayList;

// Class Karyawan
public class ClassA {
    public static class Karyawan {
        private String id;
        private String nama;
        private String posisi;
        private double gaji;

        public Karyawan(String id, String nama, String posisi, double gaji) {
            this.id = id;
            this.nama = nama;
            this.posisi = posisi;
            setGaji(gaji);
        }

        public String getId() {
            return id;
        }

        public String getNama() {
            return nama;
        }

        public String getPosisi() {
            return posisi;
        }

        public double getGaji() {
            return gaji;
        }

        public void setPosisi(String posisi) {
            this.posisi = posisi;
        }

        public void setGaji(double gaji) {
            if (gaji >= 0) {
                this.gaji = gaji;
            } else {
                System.out.println("Gaji tidak boleh negatif!");
            }
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Nama: " + nama + ", Posisi: " + posisi + ", Gaji: " + gaji;
        }
    }

    // Class Perusahaan
    public static class Perusahaan {
        private ArrayList<Karyawan> daftarKaryawan;

        public Perusahaan() {
            daftarKaryawan = new ArrayList<>();
        }

        public boolean tambahKaryawan(Karyawan karyawan) {
            if (cariKaryawanById(karyawan.getId()) == null) {
                daftarKaryawan.add(karyawan);
                return true;
            }
            return false;
        }

        public boolean hapusKaryawan(String id) {
            Karyawan karyawan = cariKaryawanById(id);
            if (karyawan != null) {
                daftarKaryawan.remove(karyawan);
                return true;
            }
            return false;
        }

        public boolean ubahPosisiKaryawan(String id, String posisiBaru) {
            Karyawan karyawan = cariKaryawanById(id);
            if (karyawan != null) {
                karyawan.setPosisi(posisiBaru);
                return true;
            }
            return false;
        }

        public boolean ubahGajiKaryawan(String id, double gajiBaru) {
            Karyawan karyawan = cariKaryawanById(id);
            if (karyawan != null) {
                karyawan.setGaji(gajiBaru);
                return true;
            }
            return false;
        }

        public ArrayList<Karyawan> getSemuaKaryawan() {
            return daftarKaryawan;
        }

        private Karyawan cariKaryawanById(String id) {
            for (Karyawan k : daftarKaryawan) {
                if (k.getId().equals(id)) {
                    return k;
                }
            }
            return null;
        }
    }
}
