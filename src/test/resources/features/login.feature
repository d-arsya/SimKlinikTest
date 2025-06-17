Feature: Autentikasi Pengguna

  Scenario: Login berhasil dengan kredensial yang valid
    Given Pengguna berada di halaman login
    When Pengguna mengisi email valid dan password valid
    And Pengguna menekan tombol login
    Then Pengguna diarahkan ke halaman dashboard

  Scenario: Login gagal dengan password salah
    Given Pengguna berada di halaman login
    When Pengguna mengisi email valid dan password tidak valid
    And Pengguna menekan tombol login
    Then Sistem menampilkan error login

  Scenario: Login dengan field kosong
    Given Pengguna berada di halaman login
    When Pengguna menekan tombol login tanpa mengisi field
    Then Sistem menampilkan alert bahwa field harus diisi

  Scenario: Akses langsung halaman dashboard tanpa login
    Given Pengguna tidak login
    When Pengguna akses halaman dashboard langsung
    Then Sistem mengarahkan ke halaman login

  Scenario: Lupa password
    Given Pengguna berada di halaman login
    When Pengguna menekan link “Forgot Password”
    And Pengguna mengisi email dan menekan submit
    Then Sistem mengirimkan reset password ke email
