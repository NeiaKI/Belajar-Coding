# TUGAS PRAKTIKUM PERTEMUAN 7
## Data Manipulation Language (Bagian-3)

---

## a) Membuat Database Perusahaan dan Tabel Karyawan

```sql
CREATE DATABASE perusahaan;
USE perusahaan;

CREATE TABLE karyawan (
  nik CHAR(10) PRIMARY KEY,
  nama CHAR(20),
  alamat VARCHAR(30),
  email CHAR(20),
  no_tlp CHAR(15)
);

DESC karyawan;
```

---

## b) Menambahkan Data ke Tabel Karyawan

```sql
INSERT INTO karyawan VALUES
  ('10011', 'Rini', 'Ciputat', 'rini@yahoo.co.id', '08561009111'),
  ('10012', 'Agung', 'Tangerang', 'agung@yahoo.com', '08561009134'),
  ('10008', 'Cici', 'Jakarta', 'cici@gmail.com', '08561009566'),
  ('10004', 'Hendra', 'Tangerang', 'hendra@gmail.com', '08561009177'),
  ('10002', 'Gari', 'Tangerang', 'geri@yahoo.com', '08561009187');
```

---

## c) Menampilkan Data Karyawan yang Beralamat Tangerang

```sql
SELECT * FROM karyawan WHERE alamat = 'Tangerang';
```

**Output:**

| nik   | nama   | alamat    | email            | no_tlp      |
|-------|--------|-----------|------------------|-------------|
| 10002 | Gari   | Tangerang | geri@yahoo.com   | 08561009187 |
| 10004 | Hendra | Tangerang | hendra@gmail.com | 08561009177 |
| 10012 | Agung  | Tangerang | agung@yahoo.com  | 08561009134 |

---

## d) Menampilkan Data dengan Operator Matematika (NIK >= 10008)

```sql
SELECT * FROM karyawan WHERE nik >= '10008';
```

**Output:**

| nik   | nama  | alamat    | email            | no_tlp      |
|-------|-------|-----------|------------------|-------------|
| 10008 | Cici  | Jakarta   | cici@gmail.com   | 08561009566 |
| 10011 | Rini  | Ciputat   | rini@yahoo.co.id | 08561009111 |
| 10012 | Agung | Tangerang | agung@yahoo.com  | 08561009134 |

---

## e) Membuat Database Universitas dan Tabel Dosen

```sql
CREATE DATABASE universitas;
USE universitas;

CREATE TABLE dosen (
  nik CHAR(10) PRIMARY KEY,
  nama CHAR(20),
  alamat VARCHAR(30),
  email CHAR(20),
  no_telepon CHAR(15)
);

INSERT INTO dosen VALUES
  ('0809111', 'Anang', 'Serpong', 'anang@yahoo.com', '0856777777'),
  ('0809112', 'Baudiono', 'Jakarta', 'budi@gmail.com', '0856888888'),
  ('0809113', 'Sariyani', 'Ciputat', 'sari@gmail.com', '0812455555'),
  ('0809114', 'Wahyudi', 'Jakarta', 'wahyudi@yahoo.com', '0817676666'),
  ('0809115', 'Suharta', 'Tangerang', 'harta@yahoo.com', '0817674444');
```

**Tabel Data Dosen:**

| NIK     | Nama     | Alamat    | Email             | No_Telepon |
|---------|----------|-----------|-------------------|------------|
| 0809111 | Anang    | Serpong   | anang@yahoo.com   | 0856777777 |
| 0809112 | Baudiono | Jakarta   | budi@gmail.com    | 0856888888 |
| 0809113 | Sariyani | Ciputat   | sari@gmail.com    | 0812455555 |
| 0809114 | Wahyudi  | Jakarta   | wahyudi@yahoo.com | 0817676666 |
| 0809115 | Suharta  | Tangerang | harta@yahoo.com   | 0817674444 |

---

## f) Menampilkan Data Dosen yang Beralamat Jakarta

```sql
SELECT * FROM dosen WHERE alamat = 'Jakarta';
```

**Output:**

| nik     | nama     | alamat  | email             | no_telepon |
|---------|----------|---------|-------------------|------------|
| 0809112 | Baudiono | Jakarta | budi@gmail.com    | 0856888888 |
| 0809114 | Wahyudi  | Jakarta | wahyudi@yahoo.com | 0817676666 |

---

## g) Menampilkan Data dengan NIK > 0809113 ATAU Alamat Ciputat (Operator OR)

```sql
SELECT * FROM dosen WHERE nik > '0809113' OR alamat = 'Ciputat';
```

**Output:**

| nik     | nama     | alamat    | email             | no_telepon |
|---------|----------|-----------|-------------------|------------|
| 0809113 | Sariyani | Ciputat   | sari@gmail.com    | 0812455555 |
| 0809114 | Wahyudi  | Jakarta   | wahyudi@yahoo.com | 0817676666 |
| 0809115 | Suharta  | Tangerang | harta@yahoo.com   | 0817674444 |

---

## h) Menampilkan Data dengan No_Telepon = 0856777777 ATAU NIK = 0809113 (Operator OR)

```sql
SELECT * FROM dosen WHERE no_telepon = '0856777777' OR nik = '0809113';
```

**Output:**

| nik     | nama     | alamat  | email           | no_telepon |
|---------|----------|---------|-----------------|------------|
| 0809111 | Anang    | Serpong | anang@yahoo.com | 0856777777 |
| 0809113 | Sariyani | Ciputat | sari@gmail.com  | 0812455555 |

**Penjelasan:** Query ini menggunakan operator **OR**, sehingga menampilkan data yang memenuhi **salah satu** dari kedua kondisi:
- Data dengan `no_telepon = '0856777777'` (NIK 0809111 - Anang)
- Data dengan `nik = '0809113'` (NIK 0809113 - Sariyani)

---

## i) Menampilkan 4 Data Dosen dengan ORDER BY dan LIMIT

```sql
SELECT * FROM dosen ORDER BY nama LIMIT 4;
```

**Output:**

| nik     | nama     | alamat    | email             | no_telepon |
|---------|----------|-----------|-------------------|------------|
| 0809111 | Anang    | Serpong   | anang@yahoo.com   | 0856777777 |
| 0809112 | Baudiono | Jakarta   | budi@gmail.com    | 0856888888 |
| 0809113 | Sariyani | Ciputat   | sari@gmail.com    | 0812455555 |
| 0809115 | Suharta  | Tangerang | harta@yahoo.com   | 0817674444 |

**Penjelasan:** Query ini menampilkan **seluruh kolom** dari tabel dosen, diurutkan berdasarkan kolom `nama` secara alfabetis (A-Z), dan dibatasi hanya 4 baris pertama.

---

# TUGAS PENDAHULUAN

## 1. Jelaskan penggunaan query WHERE pada MySQL!

Query `WHERE` digunakan untuk memfilter atau menyeleksi data berdasarkan kriteria tertentu. Hanya data yang memenuhi kondisi dalam `WHERE` yang akan ditampilkan. Contoh:
```sql
SELECT * FROM karyawan WHERE alamat = 'Jakarta';
```
Perintah di atas hanya menampilkan karyawan yang beralamat di Jakarta.

---

## 2. Apa yang dimaksud perintah SELECT dengan operasi memakai operator matematika?

Perintah `SELECT` dengan operator matematika menggunakan operator seperti `>`, `<`, `>=`, `<=`, `=`, `!=` untuk membandingkan nilai numerik atau karakter. Contoh:
```sql
SELECT * FROM karyawan WHERE nik >= '10008';
```
Perintah ini menampilkan karyawan dengan NIK lebih besar atau sama dengan 10008.

---

## 3. Apa yang dimaksud perintah SELECT dengan operasi memakai operator logika?

Perintah `SELECT` dengan operator logika menggunakan `AND`, `OR`, dan `NOT` untuk menggabungkan beberapa kondisi. 
- `AND`: Semua kondisi harus terpenuhi
- `OR`: Salah satu kondisi terpenuhi sudah cukup
- `NOT`: Membalikkan kondisi

Contoh:
```sql
SELECT * FROM dosen WHERE nik > '0809113' OR alamat = 'Ciputat';
```

---

## 4. Perintah apa yang digunakan untuk menampilkan dua kondisi yang akan ditampilkan, jelaskan!

Untuk menampilkan data dengan dua kondisi, gunakan:
- **Operator AND**: Jika kedua kondisi harus terpenuhi
  ```sql
  SELECT * FROM dosen WHERE nik = '0809111' AND alamat = 'Serpong';
  ```
- **Operator OR**: Jika salah satu kondisi terpenuhi
  ```sql
  SELECT * FROM dosen WHERE alamat = 'Jakarta' OR alamat = 'Ciputat';
  ```

---

## 5. Buatlah database sederhana menggunakan perintah SELECT dengan AND dan OR!

```sql
CREATE DATABASE toko;
USE toko;

CREATE TABLE produk (
  id INT PRIMARY KEY,
  nama VARCHAR(20),
  harga INT,
  stok INT
);

INSERT INTO produk VALUES
  (1, 'Buku', 15000, 50),
  (2, 'Pensil', 3000, 100),
  (3, 'Penghapus', 2000, 80),
  (4, 'Penggaris', 5000, 60);

-- Menggunakan AND
SELECT * FROM produk WHERE harga > 3000 AND stok > 60;

-- Menggunakan OR
SELECT * FROM produk WHERE harga < 5000 OR stok > 90;
```

---

# TUGAS AKHIR

## Kesimpulan

Praktikum ini memberikan pemahaman tentang penggunaan perintah `WHERE` dengan operator matematika dan operator logika (`AND`, `OR`) dalam Data Manipulation Language (DML) untuk menampilkan data berdasarkan kriteria tertentu.

Melalui praktikum ini, kita belajar:
- Menggunakan `WHERE` untuk filter data dengan kondisi tunggal
- Menggunakan operator matematika (`>=`, `>`, `=`, dll.) untuk membandingkan nilai
- Menggunakan operator logika `AND` untuk kondisi yang harus terpenuhi semua
- Menggunakan operator logika `OR` untuk kondisi alternatif (salah satu terpenuhi)
- Menggunakan `ORDER BY` dan `LIMIT` untuk mengurutkan dan membatasi hasil query

Kemampuan ini sangat penting dalam pengelolaan database karena memungkinkan kita untuk mengambil data yang spesifik dan relevan sesuai kebutuhan aplikasi atau pelaporan.