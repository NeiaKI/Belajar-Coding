# TUGAS PRAKTIKUM PERTEMUAN 9
## Data Manipulation Language (Bagian-5)

---

## a) Membuat Database dan Tabel Mahasiswa

```sql
CREATE DATABASE univ;
USE univ;

CREATE TABLE mahasiswa (
  nim VARCHAR(10),
  nama VARCHAR(30),
  jurusan VARCHAR(30),
  alamat VARCHAR(50),
  PRIMARY KEY(nim)
);

DESC mahasiswa;
```

---

## b) Menambahkan Data ke Tabel Mahasiswa

```sql
INSERT INTO mahasiswa VALUES ('1234567890', 'Adi Nugroho', 'Teknik Informatika', 'Tangerang');
INSERT INTO mahasiswa VALUES ('1234567891', 'Budi Santoso', 'Sistem Informasi', 'Jakarta');
INSERT INTO mahasiswa VALUES ('1234567892', 'Citra Dewi', 'Teknik Informatika', 'Bekasi');
INSERT INTO mahasiswa VALUES ('1234567893', 'Doni Pratama', 'Sistem Informasi', 'Depok');
INSERT INTO mahasiswa VALUES ('1234567894', 'Eka Putri', 'Teknik Informatika', 'Bogor');
```

**Data Tabel Mahasiswa:**
| nim        | nama          | jurusan              | alamat     |
|------------|---------------|----------------------|------------|
| 1234567890 | Adi Nugroho   | Teknik Informatika   | Tangerang  |
| 1234567891 | Budi Santoso  | Sistem Informasi     | Jakarta    |
| 1234567892 | Citra Dewi    | Teknik Informatika   | Bekasi     |
| 1234567893 | Doni Pratama  | Sistem Informasi     | Depok      |
| 1234567894 | Eka Putri     | Teknik Informatika   | Bogor      |

---

## c) Menampilkan Data Jurusan dengan DISTINCT

Menampilkan jurusan yang berbeda (tanpa duplikasi):

```sql
SELECT DISTINCT jurusan FROM mahasiswa;
```

**Output:**
| jurusan              |
|----------------------|
| Teknik Informatika   |
| Sistem Informasi     |

---

## d) Membuat Tabel Buku dengan Stok

```sql
CREATE TABLE buku (
  isbn INT,
  judul VARCHAR(50),
  pengarang VARCHAR(30),
  penerbit VARCHAR(30),
  harga INT,
  stok INT,
  PRIMARY KEY(isbn)
);

INSERT INTO buku VALUES (2001145, 'IPS Terpadu', 'Tim Guru', 'Erlangga', 54000, 10);
INSERT INTO buku VALUES (2005666, 'Cerdas Berbahasa', 'Srikanti', 'KompasMedia', 60000, 8);
INSERT INTO buku VALUES (2007575, 'Teknik Industri', 'Suryanto', 'Penerbit Andi', 50000, 12);
INSERT INTO buku VALUES (2000698, 'Akuntansi Lanjut', 'Tonikurnia', 'Graha Ilmu', 40000, 15);
INSERT INTO buku VALUES (2000543, 'Good English', 'Michael R', 'ElexMedia', 45000, 20);
INSERT INTO buku VALUES (2054449, 'Kimia Dasar', 'Michael Purba', 'Erlangga', 60000, 5);
```

**Data Tabel Buku:**
| isbn    | judul              | pengarang     | penerbit       | harga  | stok |
|---------|--------------------|---------------|----------------|--------|------|
| 2001145 | IPS Terpadu        | Tim Guru      | Erlangga       | 54000  | 10   |
| 2005666 | Cerdas Berbahasa   | Srikanti      | KompasMedia    | 60000  | 8    |
| 2007575 | Teknik Industri    | Suryanto      | Penerbit Andi  | 50000  | 12   |
| 2000698 | Akuntansi Lanjut   | Tonikurnia    | Graha Ilmu     | 40000  | 15   |
| 2000543 | Good English       | Michael R     | ElexMedia      | 45000  | 20   |
| 2054449 | Kimia Dasar        | Michael Purba | Erlangga       | 60000  | 5    |

---

## e) Menggunakan Operator Aritmatika

Menampilkan harga buku setelah diskon 10%:

```sql
SELECT judul, harga, (harga * 0.9) AS harga_setelah_diskon FROM buku;
```

**Output:**
| judul              | harga | harga_setelah_diskon |
|--------------------|-------|----------------------|
| IPS Terpadu        | 54000 | 48600                |
| Cerdas Berbahasa   | 60000 | 54000                |
| Teknik Industri    | 50000 | 45000                |
| Akuntansi Lanjut   | 40000 | 36000                |
| Good English       | 45000 | 40500                |
| Kimia Dasar        | 60000 | 54000                |

---

## f) Menggunakan Fungsi Aritmatika Built-in

```sql
SELECT ROUND(6.43) AS hasil_round;
SELECT ROUND(5.4315, 3) AS hasil_round_3_desimal;
SELECT SQRT(50) AS hasil_akar;
SELECT TRUNCATE(1234.56789, 2) AS hasil_truncate;
```

**Output:**
- `ROUND(6.43)` = 6
- `ROUND(5.4315, 3)` = 5.432
- `SQRT(50)` = 7.0710678118654755
- `TRUNCATE(1234.56789, 2)` = 1234.56

---

## g) Fungsi Agregat - COUNT

Menampilkan jumlah data record pada tabel buku:

```sql
SELECT COUNT(*) AS total_buku FROM buku;
```

**Output:**
| total_buku |
|------------|
| 6          |

Menampilkan jumlah buku dengan harga 60000:

```sql
SELECT COUNT(*) AS jumlah FROM buku WHERE harga = 60000;
```

**Output:**
| jumlah |
|--------|
| 2      |

---

## h) Fungsi Agregat - MAX, MIN, AVG

Menampilkan nilai tertinggi, terendah, dan rata-rata harga buku:

```sql
SELECT 
  MAX(harga) AS harga_tertinggi,
  MIN(harga) AS harga_terendah,
  AVG(harga) AS harga_rata_rata
FROM buku;
```

**Output:**
| harga_tertinggi | harga_terendah | harga_rata_rata |
|-----------------|----------------|-----------------|
| 60000           | 40000          | 51500           |

---

## i) Fungsi Agregat - SUM

Menampilkan total seluruh harga:

```sql
SELECT SUM(harga) AS total_harga FROM buku;
```

**Output:**
| total_harga |
|-------------|
| 309000      |

---

## j) Fungsi Agregat dengan Operasi Aritmatika

Menampilkan total keseluruhan harga setelah harga buku dikalikan dengan stok:

```sql
SELECT SUM(harga * stok) AS total_nilai_stok FROM buku;
```

**Output:**
| total_nilai_stok |
|------------------|
| 3390000          |

---

# TUGAS PENDAHULUAN

1. **Jelaskan perbedaan operator aritmatika dengan operator pembanding!**  
   - **Operator Aritmatika**: Digunakan untuk melakukan operasi matematika seperti penjumlahan (+), pengurangan (-), perkalian (*), dan pembagian (/).
   - **Operator Pembanding**: Digunakan untuk membandingkan nilai seperti sama dengan (=), lebih besar (>), lebih kecil (<), tidak sama dengan (!=).

2. **Apa yang dimaksud dengan DISTINCT pada MySQL? Jelaskan dan contohkan!**  
   DISTINCT digunakan untuk menghilangkan duplikasi data pada hasil query, hanya menampilkan data yang unik.  
   Contoh: `SELECT DISTINCT jurusan FROM mahasiswa;` akan menampilkan setiap jurusan hanya satu kali.

3. **Mengapa user memerlukan tampilan data dengan fungsi DISTINCT?**  
   Untuk mendapatkan daftar nilai unik tanpa duplikasi, misalnya untuk mengetahui jenis kategori atau kelompok yang ada dalam data.

4. **Apa yang dimaksud dengan Fungsi Agregat pada MySQL?**  
   Fungsi agregat adalah fungsi yang melakukan perhitungan pada sekumpulan nilai dan mengembalikan satu nilai tunggal, seperti COUNT(), SUM(), AVG(), MAX(), MIN().

5. **Untuk mengetahui jumlah data yang terdapat pada sebuah tabel menggunakan fungsi apa? Jelaskan!**  
   Menggunakan fungsi `COUNT()`. Contoh: `SELECT COUNT(*) FROM nama_tabel;` akan menghitung total baris/record dalam tabel.

---

# TUGAS AKHIR

**Kesimpulan:**  
Praktikum ini membahas Data Manipulation Language (DML) tingkat lanjut dengan fokus pada penggunaan DISTINCT untuk menghilangkan duplikasi data, operator aritmatika untuk melakukan perhitungan matematis pada data, dan fungsi agregat (COUNT, SUM, AVG, MAX, MIN) untuk melakukan analisis statistik pada data. Kombinasi antara operator aritmatika dan fungsi agregat sangat berguna untuk menghasilkan laporan dan analisis data yang kompleks dalam database.