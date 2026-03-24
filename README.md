# SelTest

A lightweight test suite that validates GitHub repository existence using Selenium WebDriver across multiple browsers.

## Overview

SelTest automates testing to verify that a specific GitHub repository appears in a user's public repositories list. It runs tests against both Chrome and Firefox browsers using Selenium Manager for automatic driver management.

## Prerequisites

- **Java 25** or later
- **Maven 3.6.0** or later
- **Chrome** or **Firefox** (or both) installed on your system
- Internet connection (for GitHub access)

## Quick Start

### Run all tests
```bash
mvn test
```

### Run specific browser test
```bash
mvn test -Dtest=GithubRepoFinderTest#chromeTest
mvn test -Dtest=GithubRepoFinderTest#firefoxTest
```

### Build the project
```bash
mvn clean compile
```

## Project Structure

```
SelTest/
├── pom.xml                          # Maven configuration & dependencies
├── README.md                        # This file
├── src/
│   ├── main/                        # Application source code
│   │   ├── java/com/seltest/
│   │   │   └── SelTestApplication.java   # Spring Boot entry point
│   │   └── resources/
│   │       └── application.properties    # App configuration
│   └── test/                        # Test source code
│       └── java/com/seltest/
│           └── GithubRepoFinderTest.java # Selenium test suite
└── target/                          # Build output (generated)
```

## Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| **Java** | 25 | Programming language |
| **Spring Boot** | 3.2.4 | Application framework |
| **Selenium** | 4.23.0 | Browser automation |
| **JUnit 5** | (via Spring Boot) | Testing framework |
| **Maven** | 3.12.1+ | Build tool |

## Test Configuration

The test suite is configured with:
- **Timeout**: 3 seconds for element visibility
- **Target User**: `jaguar-ks`
- **Target Repository**: `SelTest`
- **Drivers**: Chrome & Firefox (auto-managed via Selenium Manager)

To change the target user or repository, modify the `GithubRepoFinderTest` constructor:
```java
this.user = "your-github-username";
this.repository = "your-repo-name";
```

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Tests timeout | Increase the wait timeout in `GithubRepoFinderTest` (line 68) |
| Chrome/Firefox not found | Install missing browser or check PATH environment variable |
| Connection timeout | Ensure internet is available and GitHub is accessible |
| permission denied on Firefox binary | Update Firefox path or ensure execute permissions |
