# cucumber-syafiq

Framework otomasi pengujian end-to-end berbasis BDD (Behavior-Driven Development) menggunakan Cucumber, Selenium WebDriver, dan TestNG untuk aplikasi web [SauceDemo](https://www.saucedemo.com/).

## Tech Stack

| Komponen | Teknologi |
|---|---|
| Bahasa | Java 17 |
| Build | Maven |
| BDD Framework | Cucumber 7.34.3 |
| Test Runner | TestNG 7.12.0 |
| Browser Automation | Selenium WebDriver 4.41.0 |
| Reporting | ExtentReports 5.1.2 |
| Logging | SLF4J + Logback |

## Struktur Proyek

```
cucumber-syafiq/
├── src/
│   ├── main/
│   │   ├── java/com/syafiq/cucumber/
│   │   │   ├── drivers/                    # WebDriver management
│   │   │   │   ├── DriverSingleton.java    # Singleton WebDriver instance
│   │   │   │   └── strategies/             # Strategy pattern browser selection
│   │   │   │       ├── Chrome.java
│   │   │   │       └── Safari.java
│   │   │   ├── pages/                      # Page Object Model
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── InventoryPage.java
│   │   │   │   ├── SortPage.java
│   │   │   │   ├── CartPage.java
│   │   │   │   └── CheckoutPage.java
│   │   │   └── utils/
│   │   │       ├── Constants.java          # URL, timeout, browser constants
│   │   │       └── Utils.java
│   │   └── resources/
│   │       └── features/                   # Cucumber feature files (Gherkin)
│   │           ├── Login.feature
│   │           └── SortAndCheckout.feature
│   └── test/
│       └── java/com/syafiq/cucumber/
│           ├── TestRunner.java             # Cucumber test runner
│           ├── LoginTest.java              # Step definitions login
│           └── SortAndCheckoutTest.java    # Step definitions checkout
└── pom.xml
```

## Skenario Pengujian

### Login.feature
- Login berhasil dengan kredensial valid
- Login gagal dengan pesan error untuk kredensial tidak valid

### SortAndCheckout.feature
- Sorting produk berdasarkan harga (rendah ke tinggi)
- Alur checkout lengkap dengan data valid
- Validasi form checkout dengan Scenario Outline (firstName, lastName, postalCode kosong)

## Prerequisites

- Java 17+
- Maven (atau gunakan Maven Wrapper `./mvnw`)
- Google Chrome / Safari terinstall

## Cara Menjalankan

### Jalankan semua test

```bash
mvn clean test
```

### Jalankan dengan browser tertentu

```bash
# Chrome (default)
mvn clean test -Dbrowser=chrome

# Chrome headless (tanpa tampilan browser)
mvn clean test -Dbrowser=chrome-headless

# Safari
mvn clean test -Dbrowser=safari
```

### Menggunakan Maven Wrapper

```bash
./mvnw clean test
```

## Laporan Pengujian

Setelah test selesai, buka laporan HTML di:

```
target/cucumber-reports.html
```

## Design Patterns

- **Singleton** — WebDriver dikelola sebagai satu instance via `DriverSingleton`
- **Strategy** — Pemilihan browser via implementasi `DriverStrategy`
- **Page Object Model (POM)** — Elemen UI dipisahkan dari logika pengujian
- **BDD** — Feature file ditulis dalam bahasa Gherkin yang mudah dibaca

## Kredensial Test (SauceDemo)

| Username | Password |
|---|---|
| `standard_user` | `secret_sauce` |
