# TUGAS PRAKTIKUM PERTEMUAN 6
## Data Manipulation Language (Bagian-2)

---

## a) Membuat Database dan Tabel Pasien

```sql
CREATE DATABASE apotek;
USE apotek;
CREATE TABLE pasien(
  id_pasien INT(10),
  nama CHAR(20),
  alamat VARCHAR(30),
  penyakit CHAR(20),
  no_tlp CHAR(15),
  PRIMARY KEY(id_pasien)
);
DESC pasien;
```

---

## b) Menambahkan Data ke Tabel Pasien

```sql
INSERT INTO pasien VALUES
  ('10001', 'Tatang', 'Ciputat', 'Amandel', '08561009111'),
  ('10002', 'Tuti', 'Tangerang', 'Animia', '08561009134'),
  ('10003', 'Tono', 'Jakarta', 'Alergi', '08561009566'),
  ('10004', 'Budi', 'Bekasi', 'Demam', '08561009177');
```

---

## c) Menampilkan Seluruh Data Tabel Pasien

```sql
SELECT * FROM pasien;
```

**Output:**

| id_pasien | nama   | alamat    | penyakit | no_tlp      |
|-----------|--------|-----------|----------|-------------|
| 10001     | Tatang | Ciputat   | Amandel  | 08561009111 |
| 10002     | Tuti   | Tangerang | Animia   | 08561009134 |
| 10003     | Tono   | Jakarta   | Alergi   | 08561009566 |
| 10004     | Budi   | Bekasi    | Demam    | 08561009177 |

---

## d) Menampilkan Field Tertentu Saja

**Query 1:**
```sql
SELECT nama FROM pasien;
```

**Output:**

| nama   |
|--------|
| Tatang |
| Tuti   |
| Tono   |
| Budi   |

**Query 2:**
```sql
SELECT id_pasien, penyakit FROM pasien;
```

**Output:**

| id_pasien | penyakit |
|-----------|----------|
| 10001     | Amandel  |
| 10002     | Animia   |
| 10003     | Alergi   |
| 10004     | Demam    |

---

## e) Mengganti Nama Field pada Output

```sql
SELECT id_pasien AS 'Kode Pasien', nama, penyakit FROM pasien;
```

**Output:**

| Kode Pasien | nama   | penyakit |
|-------------|--------|----------|
| 10001       | Tatang | Amandel  |
| 10002       | Tuti   | Animia   |
| 10003       | Tono   | Alergi   |
| 10004       | Budi   | Demam    |

---

## f) Membuat Database Penjualan dan Tabel Barang

```sql
CREATE DATABASE penjualan;
USE penjualan;
CREATE TABLE barang(
  kode_barang INT(10) PRIMARY KEY,
  nama_barang VARCHAR(20),
  satuan VARCHAR(10),
  harga_satuan INT(8),
  stok INT(10)
);

INSERT INTO barang VALUES
  (11001, 'Sabun', 'Bungkus', 3000, 1010),
  (11002, 'Detergen', 'Kg', 10000, 1044),
  (11003, 'Shampo', 'Botol', 7000, 578),
  (11004, 'Kopi', 'Bungkus', 5000, 466),
  (11005, 'Gula', 'Kg', 12000, 500),
  (11006, 'Teh', 'Bungkus', 5000, 600),
  (11007, 'Tepung', 'Kg', 7000, 100);
```

**Tabel Data Barang:**

| Kode_barang | Nama_barang | Satuan  | Harga_satuan | Stok |
|-------------|-------------|---------|--------------|------|
| 11001       | Sabun       | Bungkus | 3000         | 1010 |
| 11002       | Detergen    | Kg      | 10000        | 1044 |
| 11003       | Shampo      | Botol   | 7000         | 578  |
| 11004       | Kopi        | Bungkus | 5000         | 466  |
| 11005       | Gula        | Kg      | 12000        | 500  |
| 11006       | Teh         | Bungkus | 5000         | 600  |
| 11007       | Tepung      | Kg      | 7000         | 100  |

---

## g) Menampilkan Seluruh Data Tabel Barang

```sql
SELECT * FROM barang;
```

**Output:**

| kode_barang | nama_barang | satuan  | harga_satuan | stok |
|-------------|-------------|---------|--------------|------|
| 11001       | Sabun       | Bungkus | 3000         | 1010 |
| 11002       | Detergen    | Kg      | 10000        | 1044 |
| 11003       | Shampo      | Botol   | 7000         | 578  |
| 11004       | Kopi        | Bungkus | 5000         | 466  |
| 11005       | Gula        | Kg      | 12000        | 500  |
| 11006       | Teh         | Bungkus | 5000         | 600  |
| 11007       | Tepung      | Kg      | 7000         | 100  |

---

## h) Menampilkan Isi Data Field Tertentu

```sql
SELECT nama_barang FROM barang;
```

**Output:**

| nama_barang |
|-------------|
| Sabun       |
| Detergen    |
| Shampo      |
| Kopi        |
| Gula        |
| Teh         |
| Tepung      |

```sql
SELECT kode_barang AS 'nama_barang', harga_satuan FROM barang;
```

**Output:**

| nama_barang | harga_satuan |
| ----------- | ------------ |
| 11001       | 3000         |
| 11002       | 10000        |
| 11003       | 7000         |
| 11004       | 5000         |
| 11005       | 12000        |
| 11006       | 5000         |
| 11007       | 7000         |

---

## i) Mengubah Nama Field pada Output Tabel Barang

```sql
SELECT kode_barang AS 'id barang', nama_barang, harga_satuan FROM barang;
atau untuk mengubah nama field
ALTER TABLE barang CHANGE kode_barang id_barang INT(10);
SELECT id_barang, nama_barang, harga_satuan FROM barang;
```

**Output:**

| id_barang | nama_barang | harga_satuan |
|-----------|-------------|--------------|
| 11001     | Sabun       | 3000         |
| 11002     | Detergen    | 10000        |
| 11003     | Shampo      | 7000         |
| 11004     | Kopi        | 5000         |
| 11005     | Gula        | 12000        |
| 11006     | Teh         | 5000         |
| 11007     | Tepung      | 7000         |

---

# TUGAS PENDAHULUAN

## 1. Jelaskan menurut anda tentang SELECT pada query MySQL!

Perintah `SELECT` adalah perintah dasar SQL yang digunakan untuk mengambil dan menampilkan data dari satu atau lebih tabel dalam database. Dengan `SELECT`, kita dapat memilih kolom tertentu, menampilkan seluruh data, atau bahkan melakukan filtering dan sorting data sesuai kebutuhan pengguna.

---

## 2. Bagaimana menampilkan seluruh data pada sebuah tabel dalam sebuah database?

Untuk menampilkan seluruh data pada tabel, gunakan perintah:
```sql
SELECT * FROM nama_tabel;
```
Tanda `*` (asterisk) berarti semua kolom akan ditampilkan.

---

## 3. Apakah sebuah data dapat ditampilkan sesuai dengan kebutuhan user? Jelaskan!

Ya, data dapat ditampilkan sesuai kebutuhan user dengan berbagai cara:
- Memilih kolom tertentu saja menggunakan `SELECT kolom1, kolom2 FROM tabel;`
- Menggunakan `WHERE` untuk memfilter data berdasarkan kondisi tertentu
- Menggunakan `ORDER BY` untuk mengurutkan data
- Menggunakan `AS` untuk mengganti nama kolom pada output agar lebih deskriptif

---

## 4. Apa yang menjadi alasan sebuah data perlu ditampilkan dalam sebuah database?

Data perlu ditampilkan dalam database agar dapat:
- Dianalisis dan dipahami oleh pengguna untuk pengambilan keputusan
- Diverifikasi keakuratannya untuk memastikan integritas data
- Digunakan untuk membuat laporan atau presentasi
- Memudahkan monitoring, maintenance, dan audit database
- Memberikan informasi yang diperlukan untuk operasional bisnis

---

## 5. Mengapa dalam sebuah perintah SELECT perlu mengganti sebuah judul pada output tampilan data yang dicari?

Mengganti judul kolom dengan keyword `AS` membuat output lebih mudah dipahami oleh pengguna non-teknis, lebih informatif, dan sesuai dengan konteks penggunaan data. Misalnya, `id_pasien` diganti menjadi `Kode Pasien` agar lebih deskriptif, mudah dibaca, dan sesuai dengan bahasa yang digunakan dalam laporan atau aplikasi.

---

# TUGAS AKHIR

## Kesimpulan

Praktikum ini memberikan pemahaman tentang penggunaan perintah `SELECT` dalam Data Manipulation Language (DML) untuk menampilkan data dari database. Melalui latihan, kita dapat:

1. Membuat database dan tabel dengan struktur yang sesuai kebutuhan
2. Menambahkan data ke tabel menggunakan perintah `INSERT`
3. Menampilkan seluruh data atau kolom tertentu menggunakan `SELECT`
4. Mengubah nama kolom pada output sementara dengan keyword `AS` untuk meningkatkan keterbacaan
5. Mengubah nama kolom secara permanen pada struktur tabel dengan perintah `ALTER TABLE`

Kemampuan ini sangat penting dalam pengelolaan database karena memungkinkan kita untuk mengekstrak informasi yang relevan dengan cepat dan efisien sesuai kebutuhan pengguna atau sistem aplikasi. Dengan memahami berbagai cara menampilkan data, kita dapat menyajikan informasi database dalam format yang paling sesuai untuk analisis, pelaporan, dan pengambilan keputusan.