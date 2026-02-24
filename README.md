# 🌿 BorneoPOS

BorneoPOS is a robust, mobile-first Point of Sale solution specifically designed for retail, F&B, and hospitality environments. Built for the Android ecosystem, it provides a seamless checkout experience, smart inventory management, and real-time sales analytics tailored for modern businesses.

## 🚀 Key Features

- Smart Inventory: Real-time tracking of stock levels with automated low-stock alerts and category management.
- Flexible Transactions: Integrated support for cash, credit/debit cards, and popular digital wallets (QR-based payments).
- Offline Mode: Keep your business running even in areas with spotty connectivity; data syncs automatically once back online.
- Digital & Thermal Receipts: Send receipts via email/SMS or print directly via Bluetooth, Wi-Fi, or USB thermal printers (ESC/POS).
- Multi-Store Management: Manage multiple outlets and synchronized catalogs from a single administrative account.
- Localization: Built-in support for regional tax configurations (SST/GST) and multi-currency handling.
- Role-Based Access: Secure login for Admins, Managers, and Cashiers with granular permission sets.

## 🛠 Tech Stack

- Language: Kotlin
- Architecture: MVVM (Model-View-ViewModel) + Clean Architecture
- Database: Room (Local Persistence) & Firebase/Cloud Firestore (Real-time Sync)
- Networking: Retrofit & OkHttp for API communication
- UI Framework: Jetpack Compose for a modern, responsive interface
- Dependency Injection: Hilt
- Asynchronous Logic: Kotlin Coroutines & Flow

## 📂 BorneoPOS Project Structure

Following the Clean Architecture and MVVM principles, this is the recommended modular structure for BorneoPOS.

### 📦 1. Core Module (`:core`)

Contains utilities, theme definitions, and common components used across all features.

```text
core/
├─ common/util/DateFormatter.kt
├─ common/util/CurrencyExt.kt
├─ designsystem/theme/Color.kt
├─ designsystem/theme/Type.kt
├─ designsystem/components/BorneoButton.kt
├─ designsystem/components/BorneoTextField.kt
└─ network/NetworkResult.kt
```

### 📦 2. Domain Layer (`:domain`)

The brain of the app. Pure Kotlin with no Android dependencies. Contains business logic.

```text
domain/
├─ model/Product.kt
├─ model/Category.kt
├─ model/Transaction.kt
├─ model/CartItem.kt
├─ repository/InventoryRepository.kt
├─ repository/SalesRepository.kt
├─ usecase/inventory/GetLowStockProductsUseCase.kt
├─ usecase/checkout/ProcessPaymentUseCase.kt
└─ usecase/printer/GenerateReceiptUseCase.kt
```

### 📦 3. Data Layer (`:data`)

Implementation of repositories, API services, and local database.

```text
data/
├─ local/BorneoDatabase.kt
├─ local/dao/ProductDao.kt
├─ local/dao/TransactionDao.kt
├─ local/entity/ProductEntity.kt
├─ remote/BorneoApiService.kt
├─ remote/dto/ProductDto.kt
├─ repository/InventoryRepositoryImpl.kt
├─ repository/SalesRepositoryImpl.kt
├─ di/DatabaseModule.kt
└─ di/NetworkModule.kt
```

### 📦 4. Feature Modules (`:features`)

Each feature is modularized to ensure separation of concerns.

```text
features/
├─ checkout/
│  ├─ ui/CheckoutScreen.kt
│  ├─ ui/CheckoutViewModel.kt
│  ├─ ui/components/CartList.kt
│  └─ ui/components/PaymentMethodSelector.kt
├─ inventory/
│  ├─ ui/ProductListScreen.kt
│  ├─ ui/ProductDetailScreen.kt
│  ├─ ui/InventoryViewModel.kt
│  └─ ui/components/StockCounter.kt
├─ reports/
│  ├─ ui/DailySalesScreen.kt
│  ├─ ui/ReportsViewModel.kt
│  └─ ui/components/SalesChart.kt
└─ hardware/
   ├─ printer/EscPosPrinter.kt
   ├─ printer/BluetoothManager.kt
   └─ scanner/BarcodeAnalyzer.kt
```

### ⚙️ 5. Configuration Files

```text
build.gradle.kts
app/build.gradle.kts
gradle/libs.versions.toml
proguard-rules.pro
```

### 🚦 Implementation Priority

1. Core & Domain: Define data models and business rules first.
2. Data (Local): Set up Room persistence to ensure offline capability.
3. UI (Checkout): Build the main transaction screen as the core workflow.
4. Hardware: Implement Bluetooth, USB, and network printing once core logic is stable.

---

## 🗄️ Database

The app uses **Room** for local persistence. The `data` module provides:

- `ProductEntity` and `TransactionEntity` entities with `@Entity` annotations.
- `ProductDao` and `TransactionDao` with query methods.
- `BorneoDatabase` (`@Database`) exposing DAOs.
- `DatabaseModule` Hilt provider for injecting the database and DAOs.  The provider also performs an initial seed when the database is created (a small catalog of sample products).

Repository implementations in `data/repository` bridge the DAOs to the domain layer.

A helper use‑case (`SeedDatabaseUseCase`) is also available in the domain layer for programmatic seeding if you prefer to populate data via business logic instead of the provider callback.

To initialize or inspect the database:

```kotlin
@HiltAndroidApp
class BorneoApp : Application()
```

The database file (`borneo.db`) is created automatically in internal storage.

```bash
# Example of opening the DB on an Android device/emulator
adb exec-out "run-as com.borneopos.app cat databases/borneo.db" > borneo.db
```

---

## 📦 Getting Started

### Prerequisites

- Android Studio Ladybug (2024.2.1) or newer
- Android SDK API Level 26 (Android 8.0 Oreo) or higher
- JDK 17–21 installed (Gradle wrapper defaults to 8.10.2; avoid JDK 25 due to Kotlin compatibility)
- A physical Android device (recommended for testing printer/scanner hardware)

### Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/BorneoPOS.git
```

2. (Optional) ensure the Gradle wrapper is up to date:

```bash
cd BorneoPOS && ./gradlew wrapper --gradle-version 8.10.2
```

3. Import project:
   Open Android Studio and select Open, then navigate to the BorneoPOS folder.

3. Configure environment:
   Create a local.properties file, or rename sample.env.properties to env.properties, then add your API keys for payment gateways or cloud services.

4. Build and run:
   Sync Gradle and click the Run button to deploy to your device.

## 📱 Supported Hardware

BorneoPOS is designed to be hardware-agnostic but performs best with:

- Printers: Any ESC/POS compatible 58mm or 80mm thermal printer.
- Scanners: Bluetooth or USB HID-compatible barcode/QR scanners.
- Handheld Terminals: Optimized for specialized POS hardware like Sunmi, PAX, or Landi.

## 🤝 Contributing

Contributions are what make the community great!

1. Fork the project.
2. Create your feature branch:
   ```bash
   git checkout -b feature/NewFeature
   ```

3. Commit your changes:
   ```bash
   git commit -m "Add NewFeature"
   ```

4. Push to the branch:
   ```bash
   git push origin feature/NewFeature
   ```

5. Open a pull request.

## 📄 License

Distributed under the MIT License. See LICENSE for more information.

Developed for the vibrant businesses of Borneo and beyond.