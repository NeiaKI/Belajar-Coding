# TUGAS PRAKTIKUM PERTEMUAN 9
## Data Manipulation Language (Bagian-5)

---

## a) Buat Struktur Database `penjualan` dan Tabel `buku`

Ketik perintah berikut di MySQL:

```sql
CREATE DATABASE penjualan;
USE penjualan;

CREATE TABLE buku_2 (
  isbn VARCHAR(15),
  judul CHAR(20),
  pengarang CHAR(30),
  harga VARCHAR(15),
  stok CHAR(10),
  PRIMARY KEY(isbn)
);

DESC buku;
```

**Output DESC buku:**
```
+----------+-------------+------+-----+---------+-------+
| Field    | Type        | Null | Key | Default | Extra |
+----------+-------------+------+-----+---------+-------+
| isbn     | varchar(15) | NO   | PRI | NULL    |       |
| judul    | char(20)    | YES  |     | NULL    |       |
| pengarang| char(30)    | YES  |     | NULL    |       |
| harga    | varchar(15) | YES  |     | NULL    |       |
| stok     | char(10)    | YES  |     | NULL    |       |
+----------+-------------+------+-----+---------+-------+
```

---

## b) Tambahkan Data ke Tabel `buku`

Ketik perintah berikut:

```sql
INSERT INTO buku_2 VALUES ('11231', 'Matematika Diskrit', 'Hanafi', '60000', '25');
INSERT INTO buku_2 VALUES ('11232', 'Pintar Java', 'Median', '50000', '20');
INSERT INTO buku_2 VALUES ('11233', 'Struktur Data', 'Andrianto', '70000', '15');
INSERT INTO buku_2 VALUES ('11234', 'Algoritma', 'SintaSari', '45000', '16');
INSERT INTO buku_2 VALUES ('11235', 'Kewarganegaraan', 'Ramdani', '64000', '22');
INSERT INTO buku_2 VALUES ('11236', 'Basisdata', 'Suginanto', '46000', '33');
INSERT INTO buku_2 VALUES ('11237', 'Sistem Berkas', 'Suginanto', '60000', '20');
INSERT INTO buku_2 VALUES ('11238', 'Web PHP', 'Median', '50000', '25');
```

**Data Tabel Buku:**
| isbn  | judul              | pengarang  | harga | stok |
|-------|--------------------|------------|-------|------|
| 11231 | Matematika Diskrit | Hanafi     | 60000 | 25   |
| 11232 | Pintar Java        | Median     | 50000 | 20   |
| 11233 | Struktur Data      | Andrianto  | 70000 | 15   |
| 11234 | Algoritma          | SintaSari  | 45000 | 16   |
| 11235 | Kewarganegaraan    | Ramdani    | 64000 | 22   |
| 11236 | Basisdata          | Suginanto  | 46000 | 33   |
| 11237 | Sistem Berkas      | Suginanto  | 60000 | 20   |
| 11238 | Web PHP            | Median     | 50000 | 25   |

---

## c) Menampilkan Data dengan DISTINCT (Pengarang)

Ketik perintah berikut:

```sql
SELECT DISTINCT pengarang FROM buku_2;
```

**Output:**

```
+------------+
| pengarang  |
+------------+
| Hanafi     |
| Median     |
| Andrianto  |
| SintaSari  |
| Ramdani    |
| Suginanto  |
+------------+
```

---

## d) Menampilkan Data dengan DISTINCT (Harga)

Ketik perintah berikut:

```sql
SELECT DISTINCT harga FROM buku_2;
```

**Output:**
```
+-------+
| harga |
+-------+
| 60000 |
| 50000 |
| 70000 |
| 45000 |
| 64000 |
| 46000 |
+-------+
```

---

## e) Menampilkan Data dengan Operator Aritmatika

Ketik perintah berikut:

```sql
SELECT ABS(-90);
SELECT ACOS(-0.90);
SELECT ASIN(-0.90);
```

**Output:**
```sql
mysql> SELECT ABS(-90);
+----------+
| ABS(-90) |
+----------+
|       90 |
+----------+
1 row in set (0.00 sec)

mysql> SELECT ACOS(-0.90);
+---------------------+
| ACOS(-0.90)         |
+---------------------+
| 2.6905658417935308  |
+---------------------+
1 row in set (0.05 sec)

mysql> SELECT ASIN(-0.90);
+----------------------+
| ASIN(-0.90)          |
+----------------------+
| -1.1197695149986342  |
+----------------------+
```

---

## f) Menampilkan Data dengan Berbagai Operator Aritmatika

Ketik perintah berikut:

```sql
SELECT ATAN(10);
SELECT ATAN2(5, 4);
SELECT BIN(10);
SELECT CEILING(6.123);
SELECT CONV(5, 15, 4);
SELECT COS(9);
SELECT COT(9);
SELECT DEGREES(5);
SELECT MOD(15, 5);
SELECT PI();
SELECT RADIANS(150);
SELECT EXP(9);
SELECT FLOOR(8.98);
SELECT FORMAT(12345.67, 4);
SELECT GREATEST(9, 200, 344, 4, 3, 1);
SELECT HEX(15);
SELECT LEAST(15, 3, 0, 100, 355);
SELECT LOG(10);
SELECT LOG10(15);
SELECT OCT(18);
SELECT POW(5, 4);
SELECT RAND(180);
SELECT ROUND(6.43);
SELECT RAND(10);
SELECT ROUND(5.4315, 3);
SELECT SIGN(-5.5637);
SELECT SIN(300);
SELECT SQRT(50);
SELECT TAN(150);
SELECT TRUNCATE(1234.56789, 2);
```

---

## g) Menampilkan Data dengan Fungsi Agregat (COUNT)

Ketik perintah berikut:

```sql
SELECT COUNT(*) FROM buku_2;
SELECT COUNT(*) FROM buku WHERE harga = 60000;
```

**Output:**
```sql
mysql> SELECT COUNT(*) FROM buku;
+----------+
| COUNT(*) |
+----------+
|        8 |
+----------+

mysql> SELECT COUNT(*) FROM buku WHERE harga = 60000;
+----------+
| COUNT(*) |
+----------+
|        2 |
+----------+
```

---

## h) Menampilkan Fungsi Agregat untuk Nilai Tertinggi, Terendah dan Rata-rata

Ketik perintah berikut:

```sql
SELECT MAX(harga) FROM buku_2;
SELECT MIN(harga) FROM buku_2;
SELECT AVG(harga) FROM buku_2;
```

**Output:**
```sql
mysql> SELECT MAX(harga) FROM buku;
+------------+
| MAX(harga) |
+------------+
| 70000      |
+------------+

mysql> SELECT MIN(harga) FROM buku;
+------------+
| MIN(harga) |
+------------+
| 45000      |
+------------+

mysql> SELECT AVG(harga) FROM buku;
+------------+
| AVG(harga) |
+------------+
| 55625.0000 |
+------------+
```

---

## i) Menampilkan Total Seluruh Harga dengan Fungsi SUM

Ketik perintah berikut:

```sql
SELECT SUM(harga) FROM buku_2;
```

**Output:**
```sql
mysql> SELECT SUM(harga) FROM buku;
+------------+
| SUM(harga) |
+------------+
|     445000 |
+------------+
```

---

## j) Menampilkan Total Keseluruhan Harga setelah Harga Dikalikan dengan Stok

Ketik perintah berikut:

```sql
SELECT SUM(harga * stok) FROM buku_2;
```

**Output:**

```sql
mysql> SELECT SUM(harga * stok) FROM buku;
+-------------------+
| SUM(harga * stok) |
+-------------------+
|          11595000 |
+-------------------+
```

---

# TUGAS PENDAHULUAN

1. **Jelaskan perbedaan operator aritmatika dengan operator pembanding!**  
   - **Operator Aritmatika**: Digunakan untuk melakukan operasi matematis seperti penjumlahan (+), pengurangan (-), perkalian (*), dan pembagian (/).
   - **Operator Pembanding**: Digunakan untuk membandingkan nilai seperti sama dengan (=), lebih besar (>), lebih kecil (<), tidak sama dengan (!=).

2. **Apa yang dimaksud dengan DISTINCT pada MySQL? Jelaskan dan contohkan!**  
   DISTINCT digunakan untuk menghilangkan duplikasi data pada hasil query dan hanya menampilkan nilai yang unik. Contoh: `SELECT DISTINCT pengarang FROM buku;` akan menampilkan daftar pengarang tanpa duplikasi.

3. **Mengapa user memerlukan tampilan data dengan fungsi DISTINCT?**  
   Untuk mendapatkan informasi yang lebih ringkas dan menghindari redundansi data, terutama saat ingin melihat variasi nilai dalam suatu kolom tanpa pengulangan.

4. **Apa yang dimaksud dengan Fungsi Agregat pada MySQL?**  
   Fungsi agregat adalah fungsi yang digunakan untuk melakukan perhitungan pada sekelompok data dan menghasilkan satu nilai, seperti COUNT(), SUM(), AVG(), MAX(), dan MIN().

5. **Untuk mengetahui jumlah data yang terdapat pada sebuah tabel menggunakan fungsi apa? Jelaskan!**  
   Menggunakan fungsi `COUNT(*)` yang akan menghitung semua baris dalam tabel. Contoh: `SELECT COUNT(*) FROM buku;` akan menampilkan total jumlah record dalam tabel buku.

---

# TUGAS AKHIR

**Kesimpulan Praktikum:**  
Praktikum ini membahas Data Manipulation Language (DML) bagian 5 dengan fokus pada penggunaan DISTINCT untuk menghilangkan duplikasi data, operator aritmatika untuk perhitungan matematis, dan fungsi agregat untuk melakukan analisis statistik data. Melalui praktikum ini, mahasiswa dapat memahami cara mencari, menampilkan, dan menganalisis data dengan berbagai fungsi built-in MySQL yang berguna untuk pengolahan data yang lebih kompleks.