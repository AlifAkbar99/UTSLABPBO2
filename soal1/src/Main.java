package soal1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private ClassA.Perusahaan perusahaan;
    private JTable tabelKaryawan;
    private DefaultTableModel tabelModel;

    private JTextField fieldID, fieldNama, fieldPosisi, fieldGaji;

    public Main() {
        perusahaan = new ClassA.Perusahaan();

        setTitle("Sistem Manajemen Data Karyawan");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // ada spasi antar panel

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data Karyawan"));

        panelInput.add(new JLabel("ID:"));
        fieldID = new JTextField();
        panelInput.add(fieldID);

        panelInput.add(new JLabel("Nama:"));
        fieldNama = new JTextField();
        panelInput.add(fieldNama);

        panelInput.add(new JLabel("Posisi:"));
        fieldPosisi = new JTextField();
        panelInput.add(fieldPosisi);

        panelInput.add(new JLabel("Gaji:"));
        fieldGaji = new JTextField();
        panelInput.add(fieldGaji);

        // Panel Tombol
        JPanel panelButton = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton btnTambah = new JButton("Tambah");
        JButton btnHapus = new JButton("Hapus");
        JButton btnUpdatePosisi = new JButton("Update Posisi");
        JButton btnUpdateGaji = new JButton("Update Gaji");
        JButton btnTampilkan = new JButton("Tampilkan Semua");

        panelButton.add(btnTambah);
        panelButton.add(btnHapus);
        panelButton.add(btnUpdatePosisi);
        panelButton.add(btnUpdateGaji);
        panelButton.add(btnTampilkan);

        // Panel Data Table
        tabelModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Posisi", "Gaji"}, 0);
        tabelKaryawan = new JTable(tabelModel);
        JScrollPane scrollPane = new JScrollPane(tabelKaryawan);

        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Karyawan"));

        // Gabungkan ke Frame
        JPanel panelTop = new JPanel(new BorderLayout(10, 10));
        panelTop.add(panelInput, BorderLayout.CENTER);
        panelTop.add(panelButton, BorderLayout.SOUTH);

        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event Listener
        btnTambah.addActionListener(e -> tambahKaryawan());
        btnHapus.addActionListener(e -> hapusKaryawan());
        btnUpdatePosisi.addActionListener(e -> updatePosisi());
        btnUpdateGaji.addActionListener(e -> updateGaji());
        btnTampilkan.addActionListener(e -> tampilkanSemua());
    }

    private void tambahKaryawan() {
        String id = fieldID.getText().trim();
        String nama = fieldNama.getText().trim();
        String posisi = fieldPosisi.getText().trim();
        double gaji;

        try {
            gaji = Double.parseDouble(fieldGaji.getText().trim());
            if (gaji < 0) {
                JOptionPane.showMessageDialog(this, "Gaji tidak boleh negatif!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gaji harus berupa angka!");
            return;
        }

        if (perusahaan.tambahKaryawan(new ClassA.Karyawan(id, nama, posisi, gaji))) {
            JOptionPane.showMessageDialog(this, "Karyawan berhasil ditambahkan!");
            tampilkanSemua();
        } else {
            JOptionPane.showMessageDialog(this, "ID sudah digunakan!");
        }
        clearField();
    }

    private void hapusKaryawan() {
        String id = fieldID.getText().trim();
        if (perusahaan.hapusKaryawan(id)) {
            JOptionPane.showMessageDialog(this, "Karyawan berhasil dihapus!");
            tampilkanSemua();
        } else {
            JOptionPane.showMessageDialog(this, "ID tidak ditemukan!");
        }
        clearField();
    }

    private void updatePosisi() {
        String id = fieldID.getText().trim();
        String posisi = fieldPosisi.getText().trim();
        if (perusahaan.ubahPosisiKaryawan(id, posisi)) {
            JOptionPane.showMessageDialog(this, "Posisi berhasil diupdate!");
            tampilkanSemua();
        } else {
            JOptionPane.showMessageDialog(this, "ID tidak ditemukan!");
        }
        clearField();
    }

    private void updateGaji() {
        String id = fieldID.getText().trim();
        double gaji;
        try {
            gaji = Double.parseDouble(fieldGaji.getText().trim());
            if (gaji < 0) {
                JOptionPane.showMessageDialog(this, "Gaji tidak boleh negatif!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gaji harus berupa angka!");
            return;
        }

        if (perusahaan.ubahGajiKaryawan(id, gaji)) {
            JOptionPane.showMessageDialog(this, "Gaji berhasil diupdate!");
            tampilkanSemua();
        } else {
            JOptionPane.showMessageDialog(this, "ID tidak ditemukan!");
        }
        clearField();
    }

    private void tampilkanSemua() {
        tabelModel.setRowCount(0); // reset data
        for (ClassA.Karyawan k : perusahaan.getSemuaKaryawan()) {
            tabelModel.addRow(new Object[]{
                    k.getId(), k.getNama(), k.getPosisi(), k.getGaji()
            });
        }
    }

    private void clearField() {
        fieldID.setText("");
        fieldNama.setText("");
        fieldPosisi.setText("");
        fieldGaji.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
