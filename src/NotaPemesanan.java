import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class NotaPemesanan {
    private static class Pesanan {
        String nama;
        double harga;

        Pesanan(String nama, double harga) {
            this.nama = nama;
            this.harga = harga;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();

        // Input jumlah item
        int jumlahItem;
        while (true) {
            System.out.print("Masukkan jumlah item yang dipesan:");
            try {
                jumlahItem = scanner.nextInt();
                scanner.nextLine(); // Membaca newline
                if (jumlahItem <= 0) {
                    System.out.println("Jumlah item harus lebih dari 0.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Bersihkan input
            }
        }

        // Input pesanan
        for (int i = 0; i < jumlahItem; i++) {
            System.out.print("Nama item " + (i + 1) + ":");
            String nama = scanner.nextLine();

            double harga;
            while (true) {
                System.out.print("Harga item " + (i + 1) + ":");
                try {
                    harga = scanner.nextDouble();
                    scanner.nextLine(); // Membaca newline
                    if (harga < 0) {
                        System.out.println("Harga tidak boleh negatif.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka untuk harga.");
                    scanner.nextLine(); // Bersihkan input
                }
            }

            daftarPesanan.add(new Pesanan(nama, harga));
        }

        // Input diskon
        double diskon;
        while (true) {
            System.out.print("Masukkan diskon (dalam persen, tanpa tanda %): ");
            try {
                diskon = scanner.nextDouble();
                if (diskon < 0 || diskon > 100) {
                    System.out.println("Diskon harus antara 0 dan 100.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka untuk diskon.");
                scanner.nextLine(); // Bersihkan input
            }
        }

        // Menghitung subtotal, diskon, pajak, dan total
        double subtotal = 0;
        for (Pesanan pesanan : daftarPesanan) {
            subtotal += pesanan.harga;
        }

        double totalDiskon = subtotal * (diskon / 100);
        double subtotalSetelahDiskon = subtotal - totalDiskon;
        double pajak = subtotalSetelahDiskon * 0.11;  // Pajak 11%
        double total = subtotalSetelahDiskon + pajak;

        // Menampilkan metode pembayaran dan kembalian jika tunai
        scanner.nextLine(); // Membaca newline
        String metodePembayaran;
        while (true) {
            System.out.printf("Total Pembelian: Rp %.2f\n", total);
            System.out.print("Metode pembayaran (cash/debit): ");
            metodePembayaran = scanner.nextLine().toLowerCase();
            if (metodePembayaran.equals("cash") || metodePembayaran.equals("debit")) {
                break;
            } else {
                System.out.println("Metode pembayaran tidak valid. Harap masukkan 'cash' atau 'debit'.");
            }
        }

        double pembayaran = 0;
        double kembalian = 0;
        if (metodePembayaran.equals("cash")) {
            while (true) {
                System.out.print("Masukkan jumlah pembayaran: ");
                try {
                    pembayaran = scanner.nextDouble();
                    if (pembayaran < total) {
                        System.out.println("Jumlah pembayaran tidak mencukupi. Harap masukkan jumlah yang benar.");
                        System.out.printf("Total Pembelian: Rp %.2f\n", total);
                        continue;
                    }
                    kembalian = pembayaran - total;
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka untuk pembayaran.");
                    scanner.nextLine(); // Bersihkan input
                }
            }
        }

        // Menampilkan nota
        System.out.println("\n========= Nota Pemesanan =========");
        System.out.println("Tanggal: " + LocalDate.now());
        System.out.println("\nDaftar Pesanan:");
        for (Pesanan pesanan : daftarPesanan) {
            System.out.printf("- %s: Rp %.2f\n", pesanan.nama, pesanan.harga);
        }
        System.out.printf("\nSubtotal: Rp %.2f\n", subtotal);
        System.out.printf("Diskon (%.2f%%): Rp %.2f\n", diskon, totalDiskon);
        System.out.printf("Subtotal Setelah Diskon: Rp %.2f\n", subtotalSetelahDiskon);
        System.out.printf("Pajak PPN (11%%): Rp %.2f\n", pajak);
        System.out.printf("Total Pembelian: Rp %.2f\n", total);
        System.out.println("Metode Pembayaran: " + metodePembayaran);

        if (metodePembayaran.equals("cash")) {
            System.out.printf("Jumlah Pembayaran: Rp %.2f\n", pembayaran);
            System.out.printf("Kembalian: Rp %.2f\n", kembalian);
        }

        System.out.println("===================================");
        scanner.close();
    }
}
