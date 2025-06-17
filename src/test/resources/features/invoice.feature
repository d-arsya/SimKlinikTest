Feature: TS-05 : Invoice & Pembayaran

  Scenario: TC-INVOICE-001 - Melihat detail invoice
    Given Invoice tersedia
    And Pengguna berada di menu Invoice
    When Pengguna mengklik ikon "Invoice"
    Then Sistem menampilkan detail invoice dengan informasi yang lengkap

  Scenario: TC-INVOICE-002 - Menambahkan Diskon
    Given Pengguna berada di halaman edit invoice
    When Pengguna mengklik checkbox "Potongan Harga" dan mengisi jumlah diskon
    Then Total harga pada invoice diperbarui sesuai diskon yang diberikan

  Scenario: TC-INVOICE-003 - Menghilangkan Diskon
    Given Diskon sedang diterapkan pada invoice
    And Pengguna berada di halaman edit invoice
    When Pengguna menghapus centang "Potongan Harga"
    Then Total harga kembali ke nilai sebelum diskon

  Scenario: TC-INVOICE-004 - Menambahkan Gratis Biaya Pemeriksaan
    Given Pengguna berada di halaman edit invoice
    When Pengguna mencentang "Gratis Biaya Pemeriksaan"
    Then Biaya layanan pemeriksaan menjadi Rp 0 dan total diperbarui

  Scenario: TC-INVOICE-005 - Cetak dan Download Invoice
    Given Invoice telah dibuat dan dapat diakses
    When Pengguna mengklik "Cetak" lalu mengklik "Unduh"
    Then Invoice terbuka di tab baru untuk dicetak atau berhasil diunduh ke perangkat
