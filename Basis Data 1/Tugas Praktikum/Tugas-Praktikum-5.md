# TUGAS PRAKTIKUM – Pertemuan 5

## Data Manipulation Language (Bagian-1)

---

## a) Buat Struktur Database dan Tabel

Ketik perintah berikut di MySQL:
```sql
CREATE DATABASE universitas;
USE universitas;

CREATE TABLE mahasiswa (
  nim      INT(10)     PRIMARY KEY,
  nama     CHAR(20),
  alamat   VARCHAR(30),
  email    CHAR(20),
  no_tlp   VARCHAR(13)
);
```

## b) Tambahkan Data pada Tabel `mahasiswa`

Ketik perintah berikut (satu perintah `INSERT INTO ... VALUES`):
```sql
INSERT INTO mahasiswa (nim, nama, alamat, email, no_tlp) VALUES
  (080911002, 'Sari', 'Pamulang', 'sari@gmail.com', '08561009999'),
  (080911001, 'Lala', 'Jakarta',  'lala@gmail.com', '08561009124'),
  (080911003, 'Tono', 'Parung',   'tono@gmail.com', '08561009888'),
  (080911006, 'Toti', 'Ciputat',  'toti@gmail.com', '08561009555'),
  (080911014, 'Ida',  'Serpong',  'ida@gmail.com',  '08561009777'),
  (080911004, 'Iya',  'Semarang', 'iya@gmail.com',  '08561009999');
```

## c) Ubah Data pada Tabel `mahasiswa`

Ketik perintah berikut:
```sql
UPDATE mahasiswa SET alamat='Ciputat'       WHERE nim=080911002;
UPDATE mahasiswa SET email='jaya@yahoo.com'  WHERE nim=080911002;
UPDATE mahasiswa SET nama='Bambang'          WHERE nim=080911003;
UPDATE mahasiswa SET alamat='Ciputat'        WHERE nim=080911003;
UPDATE mahasiswa SET email='yaho@yahoo.com'  WHERE nim=080911004;
UPDATE mahasiswa SET email='ida@yahoo.com'   WHERE nim=080911004;
```

## d) Hapus Data dari Tabel `mahasiswa`

Ketik perintah berikut:
```sql
DELETE FROM mahasiswa WHERE nim=080911004;
DELETE FROM mahasiswa WHERE nim=080911014;
```

## e) Buat Database `perusahaan` dan Tabel `karyawan`

Ketik perintah berikut:
```sql
CREATE DATABASE perusahaan;
USE perusahaan;

CREATE TABLE karyawan (
  nik         CHAR(9)     PRIMARY KEY,
  nama        VARCHAR(20),
  alamat      VARCHAR(30),
  email       VARCHAR(30),
  no_telepon  VARCHAR(15)
);

INSERT INTO karyawan (nik, nama, alamat, email, no_telepon) VALUES
  ('080911001','Susi',  'Serpong',   'susi@yahoo.com',   '0856777777'),
  ('080911002','Nuri',  'Jakarta',   'nuri@gmail.com',   '0856888888'),
  ('080911003','Santi', 'Ciputat',   'santi@gmail.com',  '0812455555'),
  ('080911006','Nunu',  'Kebayoran', 'nunu@yahoo.com',   '0817677776');
```

## f) Ubah Data pada Tabel `karyawan`

Ketik perintah berikut sesuai modul:
```sql
UPDATE karyawan SET nama='Susi',  alamat='Muncul', no_telepon='0851236789'  WHERE nik='080911001';
UPDATE karyawan SET nama='Sari',  alamat='Jakarta', email='sari@ymail.com'   WHERE nik='080911002';
UPDATE karyawan SET nama='Sindy', alamat='Cinere',  email='sindyyahoo.com', no_telepon='0812454564' WHERE nik='080911003';
UPDATE karyawan SET nama='Sandra', alamat='Kebayoran', email='sandrayahoo.com' WHERE nik='080911006';
```

Hasil akhir (lihat dengan `SELECT * FROM karyawan;`):

| nik       | nama  | alamat    | email             | no_telepon   |
|-----------|-------|-----------|-------------------|--------------|
| 080911001 | Susi  | Muncul    | susi@yahoo.com    | 0851236789   |
| 080911002 | Sari  | Jakarta   | sari@ymail.com    | 0856888888   |
| 080911003 | Sindy | Cinere    | sindyyahoo.com    | 0812454564   |
| 080911006 | Sandra| Kebayoran | sandrayahoo.com   | 0817677776   |

---

## TUGAS PENDAHULUAN

1. **Apa yang dimaksud dengan DML?**  
   Data Manipulation Language (DML) adalah perintah SQL untuk memanipulasi data dalam basis data, mencakup `SELECT`, `INSERT`, `UPDATE`, dan `DELETE`.

2. **Apa yang dimaksud `INSERT INTO ... VALUES` pada sebuah tabel?**  
   Perintah untuk menambahkan satu atau lebih baris data baru ke dalam tabel yang sudah ada, dengan menentukan nilai untuk setiap kolom.

3. **Sebutkan dan jelaskan perintah yang terdapat dalam DML!**  
   - **SELECT**: Mengambil data dari tabel.  
   - **INSERT**: Menambah data baru ke tabel.  
   - **UPDATE**: Memodifikasi data yang sudah ada.  
   - **DELETE**: Menghapus data dari tabel.

4. **Apa perbedaan `DROP` dan `DELETE` dalam sebuah tabel?**  
   - **DELETE** menghapus baris data sesuai kondisi, struktur tabel tetap ada.  
   - **DROP** menghapus seluruh tabel beserta struktur dan isinya.

5. **Buatlah sebuah database dan tabel sederhana dengan perintah DML!**  
```sql
CREATE DATABASE contoh;
USE contoh;
CREATE TABLE siswa (
  id INT PRIMARY KEY,
  nama VARCHAR(15)
);
INSERT INTO siswa VALUES (1,'Budi'),(2,'Ayu');
```

---

## TUGAS AKHIR

**Kesimpulan Praktikum:**  

##### Praktikum ini membahas penggunaan **Data Manipulation Language** (DML) pada SQL: membuat, memasukkan, memodifikasi, dan menghapus data dalam tabel basis data sesuai modul praktikum.