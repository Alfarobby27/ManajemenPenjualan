import java.util.Scanner;

// class untuk mendefinisikan objek barang
class Barang {
    public BarangCategory kategori; // kategori barang
    public String nama; // nama barang
    public int stok; // stok barang
    public int harga; // harga barang
    public String satuan;
    public int berat; //berat barang
    public String deskripsi; //deskripsi barang
}

// class untuk mendefinisikan objek kategori barang
class BarangCategory {
    public String nama; // nama kategori barang
    public BarangCategory(String nama) {
        this.nama = nama;
    }
}

//class utama applikasi
public class Penjualan {

    // Main
    public static void main (String[]args) {
        cetakJudul();
        persiapan();
        tahan();
        menu();
    }



    //=================================== JUDUL ================================

    //cetak judul Aplikasi
    private static void cetakJudul() {
        clearScreen();
        System.out.println("+-------------------------------------+");                
        System.out.println("|                                     |");
        System.out.println("|     Aplikasi Manajemen Penjualan    |");
        System.out.println("|                                     |");
        System.out.println("+-------------------------------------+");
        System.out.println("|                                     |");
        System.out.println("|         *CRUD By Alfarobby27*       |");
        System.out.println("|                                     |");
        System.out.println("+-------------------------------------+");
    }


    // cetak judul menu
    private static void cetakJudul(String judul) {
        clearScreen();
        System.out.println("-------------------------------------");
        System.out.println(judul);
        System.out.println("-------------------------------------");
    }



    //======================================== MENU =============================
    
    // menu utama
    private static void menu() {
        cetakJudul("Menu Utama");
        System.out.println("1. Menjadi Penjual");
        System.out.println("2. Menjadi Pembeli");
        System.out.println();
        System.out.println("0. Keluar");
        System.out.println();
        System.out.print("Silahkan pilih menu (0-2) : ");
        switch(bacaInput()) {
            case "1" : penjual(); break;
            case "2" : pembeli(); break;
            case "0" : keluar(); break;
            default: 
                pesanKesalahan("Nomor menu tidak valid! silakan pilih menu dengan angka 0 sampai 5.");
                menu(); break;
        }
    }


    // menu penjual
    private static void penjual() {
        cetakJudul("Menu Penjual");
        System.out.println("1. Tampilkan Daftar Barang");
        System.out.println("2. Lihat Rincian Barang");
        System.out.println("3. Tambah Data Barang");
        System.out.println("4. Ubah Data Barang");
        System.out.println("5. Hapus Data Barang");
        System.out.println();
        System.out.println("0. Kembali ke menu utama");
        System.out.println();
        System.out.print("Silakan pilih menu (0-5) : ");
        switch(bacaInput()) {
            case "1": daftarBarangPenjual(true); break;
            case "2": rincianBarang(); break;
            case "3": tambahBarang(); break;
            case "4": ubahBarang(); break;
            case "5": hapusBarang(); break;
            case "0": menu(); break;
            default: 
            pesanKesalahan("Nomor menu tidak valid! silakan pilih menu dengan angka 0 sampai 5.");
            penjual(); break;
        }
    }
    

    // menu pembeli
    private static void pembeli() {
        cetakJudul("Menu Pembeli");
        System.out.println("1. Lihat Daftar Barang");
        System.out.println("2. Rincian Barang");
        System.out.println("3. Beli Barang");
        System.out.println();
        System.out.println("0. Kembali ke menu utama");
        System.out.println();
        System.out.print("Silakan pilih menu (0-3) : ");
        switch(bacaInput()) {
            case "1": daftarBarangPembeli(true); break;
            case "2": rincianBarang(); break;
            case "3": beliBarang(); break;
            case "0": menu(); break;
            default: 
            pesanKesalahan("Nomor menu tidak valid! silakan pilih menu dengan angka 0 sampai 5.");
            pembeli(); break;
        }
    }



    //================================== FITUR =================================

    // 1. tampilkan daftar barang
    private static void daftarBarang(boolean navigasi) {

        // susun kata yang ingin ditampilkan ke tabel
        for (int i = 0; i < jumlah_data_barang; i++) {
            int stok = data_barang[i].stok;
            int harga = data_barang[i].harga;

            isi_data_tabel_barang[i][0] = Integer.toString(i + 1);
            isi_data_tabel_barang[i][1] = data_barang[i].nama;
            isi_data_tabel_barang[i][2] = Integer.toString(stok);
            isi_data_tabel_barang[i][3] = formatAngka(harga);
            isi_data_tabel_barang[i][4] = data_barang[i].kategori.nama;
        }

        //tampilkan tabel daftar data barang yang rapih
        cetakTabel(judul_kolom_tabel_barang, lebar_kolom_tabel_barang, isi_data_tabel_barang, jumlah_data_barang );

    }


    // navigasi Penjual
    private static void daftarBarangPenjual(boolean navigasi) {
        //jika dari navigasi menu, maka tampilkan judul menunya
        if (navigasi) {
            cetakJudul("Daftar Barang");
        }

        daftarBarang(true);

        // jika dari navigasi menu, maka tahan dan kembali ke menu penjual
        if (navigasi) {
            tahan();
            penjual();
        }

    }


    // navigasi Pembeli
    private static void daftarBarangPembeli(boolean navigasi) {
        //jika dari navigasi menu, maka tampilkan judul menunya
        if (navigasi) {
            cetakJudul("Daftar Barang");
        }
    
        daftarBarang(true);
    
        // jika dari navigasi menu, maka tahan dan kembali ke menu penjual
        if (navigasi) {
            tahan();
            pembeli();
        }
    
    }


    // 2. menampilkan rincian Barang
    private static void rincianBarang(Barang... a) {
        Barang data = new Barang();

        // jika dari navigasi menu, maka tampilkan judul menu nya dan munculkan interaksi pilih barang
        if (a.length == 0) {
            cetakJudul("Rincian Data Barang");

            // pilih barang yang mau diubah
            int id = pilihNomorBarang("rincian");
            System.out.println();

            // persiapkan data yang ingin ditampilkan
            data = data_barang[id-1];

        // jika dari tambah atau ubah barang, tampilkan rincian barang nya saja
        } else {            
            data = a[0];
        }

        int stok = data.stok;
        int harga = data.harga;
        String satuan = data.satuan;
        int berat = data.berat;
        String deskripsi = data.deskripsi;

        // tampilkan rincian dari barang yang dipilih
        System.out.println("Nama Barang           : " + data.nama);
        System.out.println("Nama Kategori Barang  : " + data.kategori.nama);
        System.out.println("Stok                  : " + stok);
        System.out.println("Harga Barang          : " + formatAngka(harga));
        System.out.println("Berat Barang          : " + berat + "  " + satuan);
        System.out.println("Deskripsi Barang      : "+ deskripsi);


        
        // jika dari navigasi menu, maka konfirmasi apakah ingin melihat rincian barang yang lain atau tidak
        if (a.length==0) {
            pesanSukses("melihat rincian barang", "Rincian barang berhasil dilihat!");
            rincianBarang();
        }
    }

    // 3, tampilan untuk menambahkan data barang
    private static void tambahBarang() {
        cetakJudul("Tambah Data Barang");

        // pastikan kapasitas array nya masih cukup, apabila kurang maka lipatgandakan kapasitas nya terlebih dahulu
        if (jumlah_data_barang == data_barang.length) {
            isi_data_tabel_barang = new String[data_barang.length * 2][5];
            Barang[] data_barang_baru = new Barang[data_barang.length * 2];
            System.arraycopy(data_barang, 0, data_barang_baru, 0, data_barang.length);
            data_barang = data_barang_baru;
        }

        // input data barang dan tambahkan kedalam array
        Barang data = inputBarang();
        data_barang[jumlah_data_barang] = data;
        jumlah_data_barang++;

        // tampilkan pesan sukses dan konfirmasi apakah ingin menambah data barang kembali
        pesanSukses("menambah data barang", "Data barang berhasil ditambahkan!", data);

        // jika pilih y, maka lanjut menambah data barang kembali
        tambahBarang();
    }


    // tampilan untuk merubah data barang
    private static void ubahBarang() {
        cetakJudul("Ubah Data Barang");

        // pilih barang yang mau diubah
        int id = pilihNomorBarang("ubah");

        clearScreen();
        cetakJudul("Ubah "+data_barang[id-1].nama);
        System.out.println("Anda ingin ubah bagian mana?");
        System.out.println("1. Nama Barang");
        System.out.println("2. Stok Barang ");
        System.out.println("3. Harga Barang");
        System.out.println("4. Berat Barang");
        System.out.println("5. Deskripsi Barang");
        System.out.println("6. Ganti Barang");
        System.out.println();
        System.out.println("0. Batal");
        System.out.println();
        System.out.print("Silahkan pilih nomor (0-6) : ");
        switch(bacaInput()) {
            case "1": String nama = inputNama();
                       data_barang[id-1].nama = nama; break;
            case "2": int stok = inputStok();
                      data_barang[id-1].stok = stok; break;
            case "3": int harga = inputHarga();
                      data_barang[id-1].harga = harga; break;
            case "4": String satuan = inputSatuan();
                      int berat = inputBerat();
                      data_barang[id-1].satuan = satuan;
                      data_barang[id-1].berat = berat; break;
            case "5": String deskripsi = inputDeskripsi();
                      data_barang[id-1].deskripsi = deskripsi; break;
            case "6": // perbarui data barang sesuai inputan baru user
                      Barang data = inputBarang();
                      data_barang[id-1] = data; break;
            case "0": ubahBarang(); break;
            default: 
            pesanKesalahan("Nomor menu tidak valid! silakan pilih menu dengan angka 0 sampai 5.");
            ubahBarang(); break;
        }
        // tampilkan pesan sukses dan konfirmasi apakah ingin merubah data barang kembali
        pesanSukses("mengubah data barang", "Data barang berhasil diubah!");
        // jika pilih y, maka lanjut merubah data barang kembali
        ubahBarang();
    }

    
    // tampilan untuk menghapus data barang
    private static void hapusBarang() {
        cetakJudul("Hapus Data Barang");

        // pilih barang yang mau dihapus
        int id = pilihNomorBarang("hapus");

        // konfirmasi apakah yakin ingin menghapus data barang
        System.out.print("Apakah anda yakin ingin menghapus "+data_barang[id-1].nama+" ? [y/n] : ");
        if (bacaInput().equalsIgnoreCase("y")) {

            // hapus data dengan cara membuat array baru dan mengisi nya dengan data yang lama selain data yang dihapus
            Barang[] data_barang_baru = new Barang[data_barang.length];
            int j = 0;
            for (int i = 0; i < jumlah_data_barang; i++) {
                if (i != id) {
                    data_barang_baru[j++] = data_barang[i];
                }
            }
            jumlah_data_barang--;
            data_barang = data_barang_baru;

            // tampilkan pesan sukses dan konfirmasi apakah ingin menghapus data barang kembali
            pesanSukses("menghapus data barang", "Data barang berhasil dihapus!");
        }
        hapusBarang();
    }


    // ================== Fitur khusus Pembeli ==================================

    private static void beliBarang() {
        cetakJudul("Beli Barang");

        // pilih barang yang mau dibeli
        int id = pilihNomorBarang("beli");
        System.out.println();
        
        System.out.print("Masukkan Qty : "); String inputan = bacaInput();
        int qty = toInteger(inputan);

        if(!isValidNumber(inputan) || qty<=0) {
            //tampilkan pesan kesalahan
            pesanKesalahan("Qty tidak valid! silahkan masukan angka yang benar");
            beliBarang();
        } else if( data_barang[id-1].stok == 0) {
            //tampilkan pesan kesalahan
            pesanKesalahan("Stok "+data_barang[id-1].nama+" habis, penjual belum restock barang ini lagi");
            pesanKesalahan("Silahkan beli barang yang lain");
            beliBarang();
            
        } else if( qty > data_barang[id-1].stok) {
            //tampilkan pesan kesalahan
            pesanKesalahan("Stok kurang, stok "+data_barang[id-1].nama+" tersisa "+data_barang[id-1].stok);
            beliBarang();
        } else {
            // konfirmasi apakah yakin ingin membeli barang
            System.out.print("Apakah anda yakin ingin membeli "+data_barang[id-1].nama+" ? [y/n] : ");
            if (bacaInput().equalsIgnoreCase("y")) {

                data_barang[id-1].stok -= qty;

                // tampilkan pesan sukses dan konfirmasi apakah ingin membeli barang kembali
                pesanSukses("membeli barang", "Barang berhasil dibeli!");
            }
            beliBarang();
        }
    }

    //======================================= FITUR LAINNYA ========================================

    // keluar dari aplikasi
    private static void keluar() {
        System.out.println();
        System.out.print("Apakah anda yakin ingin keluar ? [y/n] : ");
        if (bacaInput().equalsIgnoreCase("y")) {
            cetakJudul();
            System.out.println("Terima kasih...");
            System.out.println();
            System.out.println("Copyright © 2023 Kelompok 7 X2E");
            System.out.println();
            System.exit(0);
        } else {
            menu();
        }
    }


    // untuk clear screen (membersihkan tampilan di terminal)
    private static void clearScreen() {
        try {
            // apabila menggunakan os windows maka lakukan perintah cls di cmd
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();

            // apabila tidak menggunakan os windows dan support kode ANSI maka clear menggunakan kode ANSI
            } else if (isSupportANSICode()) {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.err.println("Tidak bisa clear screen");
        }
    }


    // tahan tampilan agar tidak langsung di clear
    private static void tahan() {
        System.out.println();
        System.out.print("Silakan tekan ENTER ⏎ untuk melanjutkan..."); bacaInput();
    }


    // tampilkan pesan sukses
    private static void pesanSukses(String aksi, String text, Barang... a) {
        if (text!="") {        
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println(text);
        }
        if (a.length>0) {
            System.out.println();
            rincianBarang(a[0]);
        }
        System.out.println();
        System.out.print("Apakah anda ingin "+aksi+ " kembali ? [y/n] : ");
        if (!bacaInput().equalsIgnoreCase("y")) {
            penjual();
        }
    }


    // tampilkan pesan kesalahan
    private static void pesanKesalahan(String text) {
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println(text);
        tahan();
    }



    //================================= Deklarasi Variabel =======================================

    // konstanta jumlah data kategori barang = 5 (scope dari aplikasi ini tidak sampai merubah isi data kategori barang)
    private static final int jumlah_data_kategori_barang = 5;
    private static BarangCategory[] data_kategori_barang = new BarangCategory[5];

    // variabel untuk kebutuhan cetak tabel agar rapih
    private static String[] judul_kolom_tabel_kategori_barang = new String[2];
    private static int[] lebar_kolom_tabel_kategori_barang = new int[2];
    private static String[][] isi_data_tabel_kategori_barang = new String[5][2];

    // inisiasi jumlah data barang = 0
    private static int jumlah_data_barang = 0;

    // inisiasi variabel data_barang dengan kapasitas 10 
    // kapasitas nya akan dilipatgandakan apabila kapasitasnya sudah penuh
    private static Barang[] data_barang = new Barang[10];

    // variabel untuk kebutuhan cetak tabel agar rapih
    private static String[] judul_kolom_tabel_barang = new String[5];
    private static int[] lebar_kolom_tabel_barang = new int[5];
    private static String[][] isi_data_tabel_barang = new String[10][5];


    // isi data kategori barang untuk digunakan setiap akan menambah atau merubah data barang
    private static void persiapan() {
        data_kategori_barang[0] = new BarangCategory("Fashion");
        data_kategori_barang[1] = new BarangCategory("Beauty");
        data_kategori_barang[2] = new BarangCategory("Elektronik");
        data_kategori_barang[3] = new BarangCategory("Peralatan Rumah");
        data_kategori_barang[4] = new BarangCategory("Makanan dan Minuman");
        judul_kolom_tabel_kategori_barang = new String[]{"No.", "Nama Kategori"};
        lebar_kolom_tabel_kategori_barang = new int[]{3, 24};
        for (int i = 0; i < jumlah_data_kategori_barang; i++) {
            isi_data_tabel_kategori_barang[i][0] = Integer.toString(i + 1);
            isi_data_tabel_kategori_barang[i][1] = data_kategori_barang[i].nama;
        }

        judul_kolom_tabel_barang = new String[]{"No.", "Nama Barang", "Stok Barang", "Harga Barang", "Kategori Barang"};
        lebar_kolom_tabel_barang = new int[]{3, 10, 15, 20, 20};
    }


    // tampilkan inputan untuk memilih barang.
    private static int pilihNomorBarang(String aksi) {
        daftarBarang(false);

        // jika data barang nya masih kosong, tampilkan pesan kesalahan.
        if (jumlah_data_barang == 0) {
            System.out.println();
            pesanKesalahan("Data barang masih kosong, silakan tambah data barang terlebih dahulu!");
            menu();
        }

        System.out.print("Masukan nomor barang (1-"+jumlah_data_barang+")  : "); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0 || nilai>jumlah_data_barang) {
            pesanKesalahan("Nomor barang tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan.");
            switch (aksi) {
                case "rincian": rincianBarang(); break;
                case "ubah": ubahBarang(); break;
                case "hapus": hapusBarang(); break;
                case "beli": beliBarang(); break;
            }

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai == 0) {
            penjual();
        }

        return nilai;
    }


    // tampilan input data barang
    private static Barang inputBarang() {
        Barang data = new Barang();
        data.kategori = inputKategori();
        data.nama = inputNama();
        data.stok = inputStok();
        data.harga = inputHarga();
        data.satuan = inputSatuan();
        data.berat = inputBerat();
        data.deskripsi = inputDeskripsi();


        if (data.nama.length() > lebar_kolom_tabel_barang[1]) {
            lebar_kolom_tabel_barang[1] = data.nama.length();
        }
        return data;
    }


    // tampilkan inputan untuk memilih kategori barang
    private static BarangCategory inputKategori() {

        // tampilkan pilihan kategori dengan tabel yang rapih
        cetakTabel(judul_kolom_tabel_kategori_barang, lebar_kolom_tabel_kategori_barang, isi_data_tabel_kategori_barang, jumlah_data_kategori_barang);
        System.out.print("Pilih kategori barang (1-5)    : "); String inputan = bacaInput();

        int nilai = toInteger(inputan);
        
        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0 || nilai > jumlah_data_kategori_barang) {
            pesanKesalahan("Kategori tidak valid! silakan isi sesuai nomor kategori yang valid atau isi 0 untuk membatalkan."); 
            return inputKategori();

        // apabila diisi 0 maka kembali ke menu utama
        } else if (nilai == 0) {
            penjual();
        }

        // jika valid maka tampilkan sesuai index
        BarangCategory kategoriTerpilih = data_kategori_barang[nilai-1];

        // tampilkan keterangan kategori yang sudah dipilih
        // System.out.println(kategoriTerpilih.nama);

        return kategoriTerpilih;
    }


    // tampilkan inputan untuk mengisi nama barang.
    private static String inputNama() {
        System.out.print("Masukan nama barang                : "); 
        return bacaInput();
    }


    // tampilkan inputan untuk mengisi stok barang.
    private static int inputStok() {
        System.out.print("Masukan stok barang                : "); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<= 0) {
            pesanKesalahan("Stok barang tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            return inputStok();

        // apabila diisi 0 maka kembali ke menu penjual
        } else if (nilai==0) {
            penjual();
        }

        return nilai;
    }


    // tampilkan inputan untuk mengisi harga barang
    private static int inputHarga() {
        System.out.print("Masukan harga barang               : "); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0) {
            pesanKesalahan("Harga barang tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            return inputHarga();

        // apabila diisi 0 maka kembali ke menu penjual
        } else if (nilai==0) {
            penjual();
        }

        return nilai;
    }


    // tampilkan inputan untuk mengisi satuan berat barang.
    private static String inputSatuan() {
        System.out.print("Masukan satuan berat [ons/gram/kg] : ");
        return bacaInput();
    }


    // tampilkan inputan untuk mengisi berat barang.
    private static int inputBerat() {
        System.out.print("Masukan berat barang               : "); String inputan = bacaInput();
        int nilai = toInteger(inputan);

        // validasi input, apabila tidak valid maka beri pesan tidak valid dan kembali ke tampilan inputan
        if (!isValidNumber(inputan) || nilai<0) {
            pesanKesalahan("Berat barang tidak valid! silakan isi dengan angka yang valid atau isi 0 untuk membatalkan."); 
            return inputBerat();

        // apabila diisi 0 maka kembali ke menu penjual
        } else if (nilai == 0) {
            penjual();
        }
        
        return nilai;
    }


    // tampilkan inputan untuk mengisi deskripsi barang.
    private static String inputDeskripsi() {
        System.out.print("Masukan deskripsi barang           : ");
        return bacaInput();
    }


    // tampung scanner untuk pembacaan input user
    private static Scanner scanner;

    // pembacaan input oleh user
    private static String bacaInput() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }


    // validasi apakah yang diinput oleh user merupakan angka atau bukan.
    private static boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    // konversi dari string ke int dengan mengabaikan error.
    private static int toInteger(String str) {
        int res = 0;
        try {
            res = Integer.parseInt(str);
        } catch (Exception e) {
            
        }
        return res;
    }


    // format angka pecahan dengan pemisah ribuan menggunakan titik tanpa desimal.
    private static String formatAngka(double angka) {
        return String.format("%,.0f", angka).replace(',', '.');
    }


    // cek apakah console yang digunakan support kode ANSI atau tidak
    // untuk windows umum nya tidak support https://superuser.com/questions/413073/windows-console-with-ansi-colors-handling
    private static boolean isSupportANSICode() {
        return System.console() != null && System.getenv().get("TERM") != null;
    }



    //===================================== Tabel ======================================================

    // cetak tabel biar rapih
    private static void cetakTabel(String[] judul_kolom, int[] lebar_kolom, String[][] isi_data, int jumlah_data) {
        cetakGaris(lebar_kolom);
        cetakBaris(judul_kolom, lebar_kolom);
        cetakGaris(lebar_kolom);
        if (jumlah_data>0) {        
            for (int i = 0; i < jumlah_data; i++) {
                cetakBaris(isi_data[i], lebar_kolom);
            }
        }
        cetakGaris(lebar_kolom);
    }


    // cetak garis dari suatu tabel
    private static void cetakGaris(int[] lebar_kolom) {
        for (int l : lebar_kolom) {
            System.out.print("+-");
            for (int i = 0; i < l; i++) {
                System.out.print("-");
            }
            System.out.print("-");
        }
        System.out.println("+");
    }


    // cetak baris dari suatu tabel
    private static void cetakBaris(String[] kolom, int[] lebar_kolom) {
        for (int i = 0; i < kolom.length; i++) {
            System.out.print("|");
            cetakCell(kolom[i], lebar_kolom[i]);
        }
        System.out.println("|");
    }


    // cetak cell dari suatu tabel
    private static void cetakCell(String teks, int length) {
        int maxLength = (length - teks.length() + 1);

        // untuk angka maka rata kanan
        if ((teks.charAt(0) >= '0' && teks.charAt(0) <= '6') || teks.charAt(0) == '-') {
            for (int i = 0; i < maxLength; i++) {
                System.out.print(" ");
            }
            System.out.print(teks);
            System.out.print(" ");

        // untuk selain angka maka rata kiri
        } else {
            System.out.print(" ");
            System.out.print(teks);
            for (int i = 0; i < maxLength; i++) {
                System.out.print(" ");
            }
        }
    }
}