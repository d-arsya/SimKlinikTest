Feature: Manajemen Detail dan Histori Pasien

  Scenario: TC-PATIENT-001 - Verifikasi detail dan histori pasien
    Given Pasien memiliki histori
    When Pengguna mengklik ikon "mata" pada pasien
    Then Sistem menampilkan detail "About" dan histori pasien dengan data yang akurat

  Scenario: TC-PATIENT-002 - Input Pemeriksaan Rawat Jalan
    Given Pengguna login sebagai "dokter"
    And Pengguna berada di halaman Form Pemeriksaan
    When Pengguna mengklik "Aksi" dan mengisi Anamnesa, Gejala, Diagnosis, Layanan, serta Obat
    And Pengguna mengklik tombol "Submit"
    Then Data pemeriksaan tersimpan dan sistem mengarahkan ke halaman Invoice

  Scenario: TC-PATIENT-003 - Input Pemeriksaan ke Rawat Inap
    Given Pengguna login sebagai "dokter"
    And Pengguna berada di halaman Form Pemeriksaan
    When Pengguna mengisi data pemeriksaan seperti TC-PATIENT-002
    And Pengguna mengklik tombol "Tambah ke Rawat Inap"
    Then Data pemeriksaan tersimpan dan sistem mengarahkan ke halaman Rawat Inap

  Scenario: TC-PATIENT-004 - Edit nama pasien
    Given Pengguna berada di halaman Detail Pasien
    When Pengguna mengklik "Edit Profile" pada halaman Detail Pasien
    And Mengubah nama pasien dan menekan tombol "Submit"
    Then Nama pasien ter-update dan ditampilkan pada seluruh halaman terkait

  Scenario: TC-PATIENT-005 - Melihat History Pemeriksaan
    Given Pengguna telah login dan pasien memiliki riwayat pemeriksaan
    And Pengguna berada di halaman Daftar Pasien
    When Pengguna mengklik tombol "Aksi" pada pasien yang dipilih
    And Pengguna berada di tabel Histori Pemeriksaan
    And Pengguna mengklik ikon "Detail" pada baris histori
    Then Sistem menampilkan halaman history pemeriksaan dan rekam medis pasien
