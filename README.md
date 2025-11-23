# Mobile Automation Framework

## 📖 Overview

This is a **Mobile Automation Framework** built for testing Android and iOS applications. The framework supports *
*Appium**, **Selenium**, and **Cucumber BDD** integration, with capabilities to run tests on real devices or emulators.
It also integrates with **Allure** for reporting.

The framework provides:

* Page Object Model (POM) structure for maintainable tests.
* Hooks for pre/post test actions.
* Logging and reporting.
* Flexible driver setup for Android, iOS, and Web.

---

## ⚙️ Prerequisites

Before running the framework, ensure the following tools and dependencies are installed:

1. **Java JDK 8 or higher**
2. **Maven** (for dependency management and build)
3. **Node.js & NPM** (required for Appium)
4. **Appium Server**
5. **Android Studio** (for Android SDK and Emulator)
6. **Xcode** (for iOS Simulator, macOS only)
7. **Allure Commandline** (for generating test reports)

**Maven Dependencies** (example):

---

## 🚀 How to Run Tests

### 1. **Android**

* Start the **Appium server**.
* Connect a device or start an emulator.
* Run tests via Maven:

```bash
mvn clean test -Dcucumber.options="--tags @android"
```

### 2. **iOS**

* Start the **Appium server**.
* Connect a real device or start the iOS Simulator.
* Run tests via Maven:

```bash
mvn clean test -Dcucumber.options="--tags @ios"
```

### 3. **Web (Optional)**

* Ensure Chrome/Firefox is installed.
* Run tests via Maven:

```bash
mvn clean test -Dcucumber.options="--tags @web"
```

### 4. **Generate Allure Report**

After execution:

```bash
allure serve allure-results
```

---

## 🛠 Frameworks and Tools Used

* **Java** – programming language
* **Appium** – mobile automation for Android & iOS
* **Selenium WebDriver** – for cross-platform web interactions
* **Cucumber (BDD)** – feature-driven test execution
* **JUnit** – test runner
* **Maven** – build and dependency management
* **Allure** – reporting
* **Page Object Model** – design pattern for maintainability
* **Logging Utility** – integrated logs for debugging
---

## ⚠️ Assumptions & Limitations

* The framework assumes that **Appium server is running** before executing tests.
* Currently supports **Android & iOS only**; Web testing is optional.
* All tests are executed using **Java 8+**.
* Reports are generated using **Allure**, old reports must be cleared before re-generation.
* Framework is designed for **real devices and emulators**, but some device-specific issues may occur.
---

## 🔧 Notes

* Make sure the **Android SDK path** and **iOS certificates/profiles** are configured in the system environment.
* To delete old Allure reports programmatically, a utility method can be added in the hooks to clear
  `target/allure-results`.
---
