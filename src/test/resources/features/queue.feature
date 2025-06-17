Feature: Manajemen Antrian Pasien

  Scenario: Tambah antrian pasien baru untuk owner baru
    Given Pengguna telah login dan berada di halaman Antrian
    And Pengguna memilih tab "Pasien Baru" dan sub-tab "Owner Baru"
    When Pengguna mengisi data Owner Baru dan klik "Submit"
    And Pengguna mengisi data Pasien Baru dan klik "Submit"
    And Pengguna mengklik "OK" pada tab Konfirmasi
    And Pengguna mengisi data Pemeriksaan dan klik "Submit"
    Then Data pasien dan owner baru tersimpan dan muncul di Antrian serta halaman Pasien

  Scenario: Tambah owner baru gagal karena No. HP duplikat
    Given Pengguna telah login dan berada di halaman Antrian
    And Nomor HP yang akan digunakan sudah terdaftar
    When Pengguna memilih tab "Pasien Baru" dan sub-tab "Owner Baru"
    And Pengguna mengisi form dengan nomor HP yang sama dan klik "Submit"
    Then Sistem menampilkan notifikasi error bahwa nomor HP sudah terdaftar

  Scenario: Tambah antrian pasien baru untuk owner lama
    Given Pengguna telah login dan berada di halaman Antrian
    And Owner lama sudah terdaftar
    When Pengguna memilih tab "Pasien Baru" dan sub-tab "Owner Lama"
    And Pengguna memilih Owner Lama dan mengisi data Pasien
    And Pengguna klik "Submit"
    And Pengguna mengisi Pemeriksaan Awal dan klik "Submit"
    Then Data pasien tersimpan dan muncul di Antrian serta halaman Pasien

  Scenario: Tambah antrian pasien lama ke dalam antrian
    Given Pengguna telah login dan berada di halaman Antrian
    And Pasien lama sudah terdaftar
    When Pengguna memilih tab "Pasien Lama"
    And Pengguna klik ikon "+"
    And Pengguna klik "OK"
    And Pengguna mengisi Pemeriksaan Awal
    Then Data pasien lama tersimpan dan muncul di tabel Antrian serta halaman Pasien

  Scenario: Cari data pasien dalam daftar antrian
    Given Pengguna telah login dan berada di halaman Antrian
    And Data pasien sudah tersedia
    When Pengguna mengklik tombol "Cari Pasien"
    And Pengguna memasukkan keyword pencarian
    Then Data Owner atau Pasien ditampilkan sesuai dengan keyword
