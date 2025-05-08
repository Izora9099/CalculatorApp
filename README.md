# Calculator App

A modern Android calculator application built with Java that provides essential arithmetic operations with a clean, user-friendly interface.

## Features

- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Percentage calculations
- Decimal point support
- Clear functionality
- Input length validation
- Precise decimal calculations with rounding
- Modern Material Design UI

## Technical Specifications

- **Minimum SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 34 (Android 14)
- **Language**: Java
- **Build System**: Gradle (Kotlin DSL)

## Dependencies

- AndroidX AppCompat
- Google Material Design Components
- AndroidX Activity
- AndroidX ConstraintLayout

## Building the Project

1. Clone the repository
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Build and run the application

## Development Setup

### Requirements
- Android Studio
- JDK 8 or higher
- Android SDK with API level 34

### Build Configuration
```gradle
android {
    compileSdk = 34
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
}
```

## Usage

The calculator provides a straightforward interface with:
- Numeric keypad (0-9)
- Operation buttons (+, -, ×, ÷, %)
- Decimal point button
- Clear button
- Equal button

## License

This project is open-source and available under the MIT License.
