# DemoQA Automation Framework

## Overview

A robust Selenium-based test automation framework for **demoqa.com**, built with Java, Selenium WebDriver, and TestNG. The framework implements the **Page Object Model (POM)** design pattern and supports cross-browser execution, parallel testing, headless mode, detailed reporting, and automatic screenshot capture on failures.

---

## Tech Stack

| Component | Technology |
|-----------|------------|
| **Language** | Java (JDK 8+) |
| **Automation Tool** | Selenium WebDriver |
| **Test Framework** | TestNG |
| **Build Tool** | Maven |
| **Design Pattern** | Page Object Model (POM) |
| **Logging** | Log4j2 |
| **Reporting** | Extent Reports |
| **Browsers** | Chrome, Firefox, Edge |

---

## Key Features

- ✅ **Page Object Model (POM)** architecture for maintainable test code
- ✅ **Cross-browser testing** (Chrome, Firefox, Edge)
- ✅ **Parallel execution** using TestNG
- ✅ **Headless browser mode** for CI/CD pipelines
- ✅ **Auto screenshot capture** on test failures
- ✅ **Comprehensive HTML reports** with Extent Reports
- ✅ **Centralized WebDriver management**
- ✅ **Reusable utility classes** for waits, window handling, and logging
- ✅ **Maven-based dependency management**

---

## Project Structure

```
demoqa-automation-framework/
│
├── src/main/java/
│   ├── base/
│   │   └── BaseTest.java              # Test initialization & teardown
│   │
│   ├── pages/                          # Page Object classes
│   │   ├── alerts_frame_windows/
│   │   ├── elements/
│   │   ├── forms/
│   │   ├── homepage/
│   │   └── widgets/
│   │
│   └── utils/                          # Reusable utilities
│       ├── DriverManager.java
│       ├── ExtentReportManager.java
│       ├── Log.java
│       ├── WaitManager.java
│       ├── WaitUtils.java
│       └── WindowUtils.java
│
├── src/main/resources/
│   └── log4j2.xml                      # Logging configuration
│
├── src/test/java/
│   └── tests/                          # TestNG test classes
│       ├── alerts_frame_windows/
│       ├── elements/
│       ├── forms/
│       └── widgets/
│
├── src/test/resources/
│
├── logs/                               # Log files
├── reports/                            # Extent HTML reports
├── screenshots/                        # Failure screenshots
├── test-data/                          # Test data files
├── test-output/                        # TestNG output
│
├── pom.xml                             # Maven dependencies
├── testng.xml                          # TestNG suite configuration
└── README.md
```

---

## Architecture

### 🔹 Base Layer
**`BaseTest.java`**
- Handles test initialization and teardown
- Manages browser setup and cleanup
- Provides common configuration for all test classes

### 🔹 Page Layer (`pages/`)
- Contains page classes for each DemoQA module
- Encapsulates web elements and page-specific actions
- Promotes code reusability and maintainability

### 🔹 Test Layer (`tests/`)
- Contains TestNG test classes
- Validates UI behavior and functional flows
- One-to-one mapping with page classes

### 🔹 Utilities Layer (`utils/`)
Reusable helper classes:

| Utility | Purpose |
|---------|---------|
| `DriverManager` | WebDriver lifecycle management |
| `WaitUtils` / `WaitManager` | Explicit wait implementations |
| `WindowUtils` | Browser window and tab handling |
| `ExtentReportManager` | Test report generation |
| `Log` | Logging support via Log4j2 |

---

## Test Coverage

The framework automates the following **DemoQA** modules:

### Elements
- Text Box
- Check Box
- Radio Button
- Web Tables
- Buttons

### Forms
- Practice Form

### Alerts, Frames & Windows
- Alerts
- Browser Windows
- Frames
- Nested Frames

### Widgets
- Accordian
- Auto Complete
- Date Picker
- Slider
- Progress Bar
- Select Menu
- Tabs
- Tool Tips

---

## How to Run Tests

### Prerequisites
- Java JDK 8 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

### Clone the Repository
```bash
git clone https://github.com/Dhruv3110/Selenium-Automation-Framework-DemoQA.git
cd demoqa-automation-framework
```

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run with Specific Browser
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

---

## Parallel Execution

Configure parallel execution in `testng.xml`:

```xml
<suite name="DemoQA Suite" parallel="tests" thread-count="3">
    <!-- Test configurations -->
</suite>
```

---

## Cross-Browser Testing

The framework supports multiple browsers:
- **Chrome** (default)
- **Firefox**
- **Edge**

Browser selection is managed via configuration parameters or system properties.

---

## Headless Execution

Enable headless mode for CI/CD environments by configuring browser options in `DriverManager`.

---

## Reporting & Screenshots

- **Extent HTML Reports**: Generated after each test run in `/reports`
- **Screenshots**: Automatically captured on test failures in `/screenshots`
- **Logs**: Detailed execution logs stored in `/logs`

---

## Dependencies

All dependencies are managed via Maven. Key dependencies include:

- Selenium WebDriver
- TestNG
- Log4j2
- Extent Reports
- WebDriverManager

See `pom.xml` for complete dependency list.

---

## Future Enhancements

- [ ] API integration testing
- [ ] CI/CD pipeline integration (GitHub Actions / Jenkins)
- [ ] Data-driven testing with Excel/JSON
- [ ] Docker containerization
- [ ] Allure reporting integration
- [ ] BDD framework with Cucumber

---

## Author

**Dhruv Gupta**  
B.Tech CSE | Automation & QA Enthusiast

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue)](https://www.linkedin.com/in/dhruv-gupta-794968244/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black)](https://github.com/Dhruv3110)
[![Portfolio](https://img.shields.io/badge/Portfolio-Visit-brightgreen)](https://dhruvgupta-dev.web.app/)




---

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📧 Contact

For questions or suggestions, please open an issue or reach out via email.

---

**⭐ If you find this project helpful, please consider giving it a star!**
