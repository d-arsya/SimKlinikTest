Feature: Dashboard

  Scenario: Verifikasi data pada kartu ringkasan dashboard
    Given Pengguna telah login dan data tersedia
    And Pengguna berada di halaman Dashboard
    When Pengguna mengamati kartu summary
    And Pengguna mencocokkan jumlah antrian dan rawat inap
    Then Data pada kartu summary sesuai dengan jumlah aktual

  Scenario: Navigasi ke halaman profil dari dashboard
    Given Pengguna telah login
    When Pengguna mengklik "View Profile"
    Then Pengguna diarahkan ke halaman profil

  Scenario: Pengguna berhasil logout dari dashboard
    Given Pengguna sedang dalam sesi login aktif
    When Pengguna mengklik foto profil
    And Pengguna mengklik "Logout"
    Then Pengguna diarahkan ke halaman login
