                                                    
                                            TUGAS  PRAKTIKUM 
 
    a) Buatlah Struktur Database, dengan  mengetik perintah di  bawah  ini 

mysql> create database universitas;
Query OK, 1 row affected

mysql> use universitas;
Database changed

mysql> create table mahasiswa (
-> nim int(10) primary key,
-> nama char(20),
-> alamat varchar(30),
-> email char(20),
-> no_tlp varchar(13)
-> );
Query OK, 0 rows affected

    b) Dari point a) di atas, tambahkan data sebagai berikut:Menambah data pada tabel mahasiswa dengan insert into
    Ketik perintah di bawah ini

mysql> insert into mahasiswa (nim, nama, alamat, email, no_tlp) values
-> (080911002, 'Sari', 'Pamulang', 'sari@gmail.com', '08561009999'),
-> (080911001, 'Lala', 'Jakarta', 'lala@gmail.com', '08561009124'),
-> (080911003, 'Tono', 'Parung', 'tono@gmail.com', '08561009888'),
-> (080911006, 'Toti', 'Ciputat', 'toti@gmail.com', '08561009555');
Query OK, 4 rows affected

mysql> insert into mahasiswa set nim=080911014, nama='Ida', alamat='Serpong', email='ida@gmail.com', no_tlp='08561009777';
Query OK, 1 row affected

mysql> insert into mahasiswa set nim=080911004, nama='Iya', alamat='Semarang', email='iya@gmail.com', no_tlp='08561009999';
Query OK, 1 row affected

    Hasil Penambahan, di lihat menggunakan perintah berikut:
mysql> select * from mahasiswa;
    +-----------+--------+----------+------------------+--------------+
    | nim | nama | alamat | email | no_tlp |
    +-----------+--------+----------+------------------+--------------+
    | 80911001 | Lala | Jakarta  | lala@gmail.com  | 08561009124 |
    | 80911002 | Sari | Pamulang | sari@gmail.com  | 08561009999 |
    | 80911003 | Tono | Parung   | tono@gmail.com  | 08561009888 |
    | 80911004 | Iya  | Semarang | iya@gmail.com   | 08561009999 |
    | 80911006 | Toti | Ciputat  | totti@gmail.com | 08561009555 |
    | 80911014 | Ida  | Serpong  | ida@gmail.com   | 08561009777 |
    +-----------+--------+----------+------------------+--------------+
    6 rows in set

    c) Dari point a) dan b) di atas, ubah data sebagai berikut:
        Mengubah data pada tabel mahasiswa dengan update
        Ketik perintah di bawah ini

mysql> update mahasiswa set alamat='Ciputat' where nim=080911002;
Query OK, 1 row affected

mysql> update mahasiswa set email='jaya@yahoo.com' where nim=080911002;
Query OK, 1 row affected

mysql> update mahasiswa set nama='Bambang' where nim=080911003;
Query OK, 1 row affected

mysql> update mahasiswa set alamat='Ciputat' where nim=080911003;
Query OK, 1 row affected

mysql> update mahasiswa set email='yaho@yahoo.com' where nim=080911004;
Query OK, 1 row affected

mysql> update mahasiswa set email='Ida@yahoo.com' where nim=080911004;
Query OK, 1 row affected

    d) Dari point a), b) dan c) di atas, hapus data sebagai berikut:Menghapus data pada tabel mahasiswa dengan delete
       Ketik perintah di bawah ini

mysql> delete from mahasiswa where nim=080911004;
Query OK, 1 row affected

mysql> delete from mahasiswa where nim=080911014;
Query OK, 1 row affected

    e) Buat Database dengan nama Perusahaan, kemudian table dengan nama karyawan,
       kemudian isi table tersebut dengan data seperti di bawah ini

        | NIK       | Nama    | Alamat    | Email               | No_Telepon  |
        |-----------|---------|-----------|---------------------|-------------|
        | 080911001 | Susi    | Serpong   | susi@yahoo.com      | 0856777777  |
        | 080911002 | Nuri    | Jakarta   | nuri@gmail.com      | 0856888888  |
        | 080911003 | Santi   | Ciputat   | santi@gmail.com     | 0812455555  |
        | 080911006 | Nunu    | Kebayoran | nunu@yahoo.com      | 0817677776  |

mysql> create database perusahaan;
Query OK, 1 row affected

mysql> use perusahaan;
Database changed

mysql> create table karyawan (
-> nik int(10) primary key,
-> nama varchar(30),
-> alamat varchar(30),
-> email varchar(30),
-> no_telepon varchar(15)
-> );
Query OK, 0 rows affected

mysql> insert into karyawan values
-> (080911001, 'Susi', 'Serpong', 'susi@yahoo.com', '0856777777'),
-> (080911002, 'Nuri', 'Jakarta', 'nuri@gmail.com', '0856888888'),
-> (080911003, 'Santi', 'Ciputat', 'santi@gmail.com', '0812455555'),
-> (080911006, 'Nunu', 'Kebayoran', 'nunu@yahoo.com', '0817677776');
Query OK, 4 rows affected


    f) Dari point e), kemudian ubah data pada table tersebut sehingga menjadi seperti
       tabel di bawah ini

        | NIK       | Nama    | Alamat    | Email               | No_Telepon  |
        |-----------|---------|-----------|---------------------|-------------|
        | 080911001 | Susi    | Muncul    | susi@yahoo.com      | 0851236789  |
        | 080911002 | Sari    | Jakarta   | sari@ymail.com      | 0856888888  |
        | 080911003 | Sindy   | Cinere    | sindy@yahoo.com     | 0812454564  |
        | 080911006 | Sandra  | Kebayoran | sandra@yahoo.com    | 0817677776  |

mysql> update karyawan set alamat='Muncul', no_telepon='0851236789' where nik=080911001;
Query OK, 1 row affected

mysql> update karyawan set nama='Sari', email='sari@ymail.com' where nik=080911002;
Query OK, 1 row affected

mysql> update karyawan set nama='Sindy', alamat='Cinere', email='sindy@yahoo.com', no_telepon='0812454564' where nik=080911003;
Query OK, 1 row affected

mysql> update karyawan set nama='Sandra', email='sandra@yahoo.com' where nik=080911006;
Query OK, 1 row affected

mysql> select * from karyawan;
    +-----------+--------+-----------+-------------------+-------------+
    | nik | nama | alamat | email | no_telepon |
    +-----------+--------+-----------+-------------------+-------------+
    | 80911001 | Susi   | Muncul    | susi@yahoo.com    | 0851236789 |
    | 80911002 | Sari   | Jakarta   | sari@ymail.com    | 0856888888 |
    | 80911003 | Sindy  | Cinere    | sindy@yahoo.com   | 0812454564 |
    | 80911006 | Sandra | Kebayoran | sandra@yahoo.com  | 0817677776 |
    +-----------+--------+-----------+-------------------+-------------+
    4 rows in set


# TUGAS PENDAHULUAN

**1. Apa yang dimaksud dengan DML?**  
DML (Data Manipulation Language) adalah kumpulan perintah SQL untuk memanipulasi data pada basis data, meliputi operasi penyisipan, pengambilan, pengubahan, dan penghapusan data.

**2. Apa yang dimaksud create into values pada sebuah table?**  
`CREATE INTO ... VALUES` (lebih tepatnya: `INSERT INTO ... VALUES`) adalah perintah SQL untuk menambahkan data baru ke tabel.

**3. Sebutkan dan jelaskan perintah yang terdapat dalam query DML!**  
- **INSERT**: Menambahkan data baru ke tabel  
- **SELECT**: Mengambil/menampilkan data dari tabel  
- **UPDATE**: Mengubah data yang sudah ada di tabel  
- **DELETE**: Menghapus data dari tabel  

**4. Apa perbedaan drop dan delete dalam sebuah tabel, jelaskan!**  
- **DROP**: Menghapus tabel (atau database) beserta seluruh isinya dan struktur tabel.  
- **DELETE**: Menghapus data (record/baris) dari tabel, struktur tabel tetap ada.

**5. Buatlah database dan tabel sederhana menggunakan perintah DML!**  
Contoh:

    CREATE DATABASE contoh;
    USE contoh;
    CREATE TABLE siswa (
    id INT PRIMARY KEY,
    nama VARCHAR(15)
    );
    INSERT INTO siswa VALUES (1, 'Budi');
    INSERT INTO siswa VALUES (2, 'Ayu');

# TUGAS AKHIR

**Kesimpulan Praktikum:**  
Praktikum ini memberikan pemahaman dan latihan langsung mengenai penggunaan perintah DML (Data Manipulation Language) dalam SQL. Mahasiswa diajak untuk membuat, memasukkan, mengubah, dan menghapus data pada tabel basis data, sehingga dapat memahami proses manipulasi data secara terstruktur dan benar dalam aplikasi basis data relasional.