public class barangJualan {
    public String nama;
    public double harga;
    public int Persediaan;
    public String nama1;
    public double harga2;
    public int Persediaan2;
    public barangJualan(String nama, String nama1,double harga, double harga2 , int Persediaan, int Persediaan2) {
        this.nama = nama;
        this.nama1 = nama1;
        this.harga = harga;
        this.harga2 = harga2;
        this.Persediaan = Persediaan;
        this.Persediaan2 = Persediaan2;
    }
    public void displayProduct() {
        double hargadiskon = harga * 0.9;
        double hargadiskon2 = harga * 1.9;
        System.out.println("Nama Produk:" + nama);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Harag Diskon: Rp" + hargadiskon);
        System.out.println("Persediaan: " + Persediaan);
        System.out.println("Nama Produk:" + nama1);
        System.out.println("Harga: Rp" + harga2);
        System.out.println("Harag Diskon: Rp" + hargadiskon2);
        System.out.println("Persediaan: " + Persediaan2);
    }
}
class PersediaanBarang {
    public barangJualan barangJualan;
    public String lokasi;
    public PersediaanBarang(barangJualan barangJualan, String lokasi) {
        this.barangJualan = barangJualan;
        this.lokasi = lokasi;
    }
    public void tampilanstock() {
        System.out.println("lokasi:" + lokasi);
        barangJualan.displayProduct();
    }
}
class Main_app {
    public static void main(String[] args) {
        barangJualan barang = new barangJualan("SADPHONE", "HappyPhone", 1500000, 1500000, 999, 10);
        PersediaanBarang inv = new PersediaanBarang(barang, "DEWA cell");
        inv.tampilanstock();
        inv.tampilanstock();

    }

}