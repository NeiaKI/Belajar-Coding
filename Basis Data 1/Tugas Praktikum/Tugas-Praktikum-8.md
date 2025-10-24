# TUGAS PRAKTIKUM PERTEMUAN 8
## Data Manipulation Language (Bagian-4)

---

## a) Membuat Database dan Tabel Suplier

```sql
CREATE DATABASE penjualan;
USE penjualan;

CREATE TABLE suplier (
  id_suplier VARCHAR(15),
  nama_suplier CHAR(20),
  alamat VARCHAR(30),
  no_tlp CHAR(15),
  PRIMARY KEY(id_suplier)
);

DESC suplier;
```

---

## b) Menambahkan Data ke Tabel Suplier

```sql
INSERT INTO suplier VALUES ('11234', 'Elex Media', 'PalMerah', '0215455454');
INSERT INTO suplier VALUES ('11236', 'BIP', 'Cakung', '0215455234');
INSERT INTO suplier VALUES ('11235', 'AgroMedia', 'Ciganjur', '0215455776');
INSERT INTO suplier VALUES ('11232', 'Erlangga', 'Ciracas', '0215454242');
INSERT INTO suplier VALUES ('11231', 'Mizan', 'Cilandak', '0215455899');
INSERT INTO suplier VALUES ('11230', 'KompasMedia', 'PalMerah', '0215455433');
```

---

## c) Menampilkan Data dengan BETWEEN

Menampilkan data suplier yang mempunyai `id_suplier` antara 11231 dan 11235:

```sql
SELECT * FROM suplier WHERE id_suplier BETWEEN '11231' AND '11235';
```

**Output:**
| id_suplier | nama_suplier | alamat    | no_tlp       |
|------------|--------------|-----------|--------------|
| 11231      | Mizan        | Cilandak  | 0215455899   |
| 11232      | Erlangga     | Ciracas   | 0215454242   |
| 11234      | Elex Media   | PalMerah  | 0215455454   |
| 11235      | AgroMedia    | Ciganjur  | 0215455776   |

---

## d) Membuat Tabel Buku dan Mengisi Data

```sql
CREATE TABLE buku (
  isbn INT,
  judul VARCHAR(50),
  pengarang VARCHAR(30),
  penerbit VARCHAR(30),
  harga INT,
  PRIMARY KEY(isbn)
);

INSERT INTO buku VALUES (2001145, 'IPS Terpadu', 'Tim Guru', 'Erlangga', 54000);
INSERT INTO buku VALUES (2005666, 'Cerdas Berbahasa', 'Srikanti', 'KompasMedia', 60000);
INSERT INTO buku VALUES (2007575, 'Teknik Industri', 'Suryanto', 'Penerbit Andi', 50000);
INSERT INTO buku VALUES (2000698, 'Akuntansi Lanjut', 'Tonikurnia', 'Graha Ilmu', 40000);
INSERT INTO buku VALUES (2000543, 'Good English', 'Michael R', 'ElexMedia', 45000);
INSERT INTO buku VALUES (2054449, 'Kimia Dasar', 'Michael Purba', 'Erlangga', 60000);
```

**Data Tabel Buku:**
| isbn    | judul              | pengarang     | penerbit       | harga  |
|---------|--------------------|---------------|----------------|--------|
| 2001145 | IPS Terpadu        | Tim Guru      | Erlangga       | 54000  |
| 2005666 | Cerdas Berbahasa   | Srikanti      | KompasMedia    | 60000  |
| 2007575 | Teknik Industri    | Suryanto      | Penerbit Andi  | 50000  |
| 2000698 | Akuntansi Lanjut   | Tonikurnia    | Graha Ilmu     | 40000  |
| 2000543 | Good English       | Michael R     | ElexMedia      | 45000  |
| 2054449 | Kimia Dasar        | Michael Purba | Erlangga       | 60000  |

---

## e) Menampilkan Data dengan Kisaran Harga 50.000 - 60.000

```sql
SELECT * FROM buku WHERE harga BETWEEN 50000 AND 60000;
```

**Output:**
| isbn    | judul            | pengarang     | penerbit      | harga |
|---------|------------------|---------------|---------------|-------|
| 2001145 | IPS Terpadu      | Tim Guru      | Erlangga      | 54000 |
| 2005666 | Cerdas Berbahasa | Srikanti      | KompasMedia   | 60000 |
| 2007575 | Teknik Industri  | Suryanto      | Penerbit Andi | 50000 |
| 2054449 | Kimia Dasar      | Michael Purba | Erlangga      | 60000 |

---

## f) Menampilkan Data dengan LIKE (Penerbit Berawalan 'E')

```sql
SELECT * FROM buku WHERE penerbit LIKE 'E%';
```

**Output:**
| isbn    | judul       | pengarang     | penerbit  | harga |
|---------|-------------|---------------|-----------|-------|
| 2001145 | IPS Terpadu | Tim Guru      | Erlangga  | 54000 |
| 2000543 | Good English| Michael R     | ElexMedia | 45000 |
| 2054449 | Kimia Dasar | Michael Purba | Erlangga  | 60000 |

---

## g) Menampilkan Data dengan NOT LIKE (Penerbit Tidak Berawalan 'E')

```sql
SELECT * FROM buku WHERE penerbit NOT LIKE 'E%';
```

**Output:**
| isbn    | judul              | pengarang  | penerbit       | harga |
|---------|--------------------|------------|----------------|-------|
| 2005666 | Cerdas Berbahasa   | Srikanti   | KompasMedia    | 60000 |
| 2007575 | Teknik Industri    | Suryanto   | Penerbit Andi  | 50000 |
| 2000698 | Akuntansi Lanjut   | Tonikurnia | Graha Ilmu     | 40000 |

---

## h) Menampilkan Data dengan IN dan NOT IN

**Menggunakan IN:**
```sql
SELECT * FROM buku WHERE pengarang IN ('Srikanti', 'Suryanto', 'Tim Guru');
```

**Output:**
| isbn    | judul              | pengarang | penerbit       | harga |
|---------|--------------------|-----------|----------------|-------|
| 2001145 | IPS Terpadu        | Tim Guru  | Erlangga       | 54000 |
| 2005666 | Cerdas Berbahasa   | Srikanti  | KompasMedia    | 60000 |
| 2007575 | Teknik Industri    | Suryanto  | Penerbit Andi  | 50000 |

**Menggunakan NOT IN:**
```sql
SELECT * FROM buku WHERE pengarang NOT IN ('Srikanti', 'Suryanto', 'Tim Guru');
```

**Output:**
| isbn    | judul            | pengarang     | penerbit   | harga |
|---------|------------------|---------------|------------|-------|
| 2000698 | Akuntansi Lanjut | Tonikurnia    | Graha Ilmu | 40000 |
| 2000543 | Good English     | Michael R     | ElexMedia  | 45000 |
| 2054449 | Kimia Dasar      | Michael Purba | Erlangga   | 60000 |

---

## i) Menampilkan Data dengan GROUP BY dan HAVING

```sql
SELECT penerbit, COUNT(*) AS jumlah 
FROM buku 
GROUP BY penerbit 
HAVING COUNT(*) > 1;
```

**Output:**
| penerbit | jumlah |
|----------|--------|
| Erlangga | 2      |

---

## j) Menampilkan Data ISBN dengan ORDER BY ASC dan DESC

**Ascending:**
```sql
SELECT * FROM buku ORDER BY isbn ASC;
```

**Output:**
| isbn    | judul              | pengarang     | penerbit       | harga |
|---------|--------------------|---------------|----------------|-------|
| 2000543 | Good English       | Michael R     | ElexMedia      | 45000 |
| 2000698 | Akuntansi Lanjut   | Tonikurnia    | Graha Ilmu     | 40000 |
| 2001145 | IPS Terpadu        | Tim Guru      | Erlangga       | 54000 |
| 2005666 | Cerdas Berbahasa   | Srikanti      | KompasMedia    | 60000 |
| 2007575 | Teknik Industri    | Suryanto      | Penerbit Andi  | 50000 |
| 2054449 | Kimia Dasar        | Michael Purba | Erlangga       | 60000 |

**Descending:**
```sql
SELECT * FROM buku ORDER BY isbn DESC;
```

**Output:**
| isbn    | judul              | pengarang     | penerbit       | harga |
|---------|--------------------|---------------|----------------|-------|
| 2054449 | Kimia Dasar        | Michael Purba | Erlangga       | 60000 |
| 2007575 | Teknik Industri    | Suryanto      | Penerbit Andi  | 50000 |
| 2005666 | Cerdas Berbahasa   | Srikanti      | KompasMedia    | 60000 |
| 2001145 | IPS Terpadu        | Tim Guru      | Erlangga       | 54000 |
| 2000698 | Akuntansi Lanjut   | Tonikurnia    | Graha Ilmu     | 40000 |
| 2000543 | Good English       | Michael R     | ElexMedia      | 45000 |

---

# TUGAS PENDAHULUAN

1. **Apa yang dimaksud dengan BETWEEN dalam MySQL?**  
   BETWEEN digunakan untuk mencari dan menampilkan data dalam rentang nilai tertentu (antara nilai awal dan nilai akhir).

2. **Apa tujuan menggunakan HAVING dalam menampilkan data yang telah di-GROUP BY?**  
   HAVING digunakan untuk memfilter hasil dari GROUP BY berdasarkan kondisi tertentu, seperti menampilkan kelompok yang memenuhi kriteria agregat.

3. **Apa perbedaan dalam pengurutan data dengan ORDER BY?**  
   - **ASC (Ascending)**: Mengurutkan dari kecil ke besar (default)
   - **DESC (Descending)**: Mengurutkan dari besar ke kecil

4. **Apa perbedaan dari LIKE dan NOT LIKE?**  
   - **LIKE**: Menampilkan data yang mengandung karakter/pola tertentu
   - **NOT LIKE**: Menampilkan data yang tidak mengandung karakter/pola tertentu

5. **IN dan NOT IN digunakan sebagai apa dalam penampilan sebuah data?**  
   - **IN**: Menampilkan data yang sesuai dengan salah satu nilai dalam daftar
   - **NOT IN**: Menampilkan data yang tidak sesuai dengan nilai dalam daftar

---

# TUGAS AKHIR

**Kesimpulan:**  
Praktikum ini membahas Data Manipulation Language (DML) tingkat lanjut dengan fokus pada pencarian dan filtering data menggunakan berbagai operator seperti BETWEEN, LIKE, NOT LIKE, IN, NOT IN, GROUP BY, HAVING, dan ORDER BY. Operator-operator ini sangat berguna untuk menampilkan data sesuai kriteria tertentu, mengelompokkan data, dan mengurutkan hasil query agar lebih mudah dibaca dan dianalisis.