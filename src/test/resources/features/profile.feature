Feature: Update Profil Pengguna

  # TC-PROFILE-001
  Scenario: Mengubah data profil dengan input valid
    Given Pengguna telah login dan berada di halaman Profil
    When Pengguna mengklik tombol "Edit", mengubah nama dan spesialis, lalu klik "Submit"
    Then Profil pengguna berhasil diperbarui dan muncul notifikasi sukses

  # TC-PROFILE-002
  Scenario: Gagal mengubah data profil karena input kosong
    Given Pengguna berada di halaman Edit Profil
    When Pengguna mengosongkan kolom nama dan menekan tombol "Submit"
    Then Sistem menampilkan pesan kesalahan "please fill out this field" dan data tidak tersimpan
