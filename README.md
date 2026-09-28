# Tourism Mobile App

A native Android tourism application built with Kotlin and Jetpack Compose, covering discovery, booking, local persistence, maps, notifications, authentication, payments, and administrative workflows.

## Overview

The app is structured as a multi-feature Android project rather than a single-screen demo. It includes dedicated data, repository, ViewModel, and UI layers for common tourism workflows.

## Implemented Areas

The committed source tree includes:

- onboarding, registration, and login
- home/discovery flows
- hotels, flights, and car services
- booking creation and booking history
- payment selection
- landmarks and landmark details
- map screens
- notifications
- user profile
- administrative dashboard
- AI/chat UI
- local Room persistence

## Architecture

The project separates responsibilities across:

- data models
- Room DAOs
- repositories
- ViewModels
- Jetpack Compose screens

Representative source areas:

```text
app/src/main/java/com/example/tourism/
├── data/
│   ├── local/
│   ├── model/
│   └── repository/
└── ui/
    ├── admin/
    ├── ai/
    ├── auth/
    ├── booking/
    ├── home/
    ├── landmarks/
    ├── map/
    ├── notifications/
    ├── profile/
    └── services/
```

## Android Stack

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Room
- Lifecycle/ViewModel
- Coil
- Android SDK 35
- Java 11 compatibility

## Local Data Layer

The Room-backed local layer includes DAOs and models for areas such as:

- bookings
- cars
- flights
- hotels
- landmarks
- notifications
- users

Repository classes sit above the data layer and are consumed by feature ViewModels.

## Feature Structure

Examples of implemented screens and ViewModels include:

- `LoginScreen` / `AuthViewModel`
- `BookingScreen` / `BookingViewModel`
- `MyBookingsScreen` / `MyBookingsViewModel`
- `PaymentSelectionScreen` / `PaymentViewModel`
- `LandmarkDetailScreen` / `LandmarkViewModel`
- `MapScreen` / `MapViewModel`
- `NotificationScreen` / `NotificationViewModel`
- `ProfileScreen` / `ProfileViewModel`
- hotel, flight, and car listing flows
- administrative dashboard
- AI/chat interface

## Build

Open the project in Android Studio or use the Gradle wrapper.

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

Run the test suite with:

```powershell
.\gradlew.bat test
```

## Current Status

The repository contains a substantial native Android source tree with the core tourism workflows and local data architecture implemented.

The next portfolio-quality improvements should focus on:

- adding product screenshots
- documenting end-to-end user flows
- removing generated build/IDE artifacts from version control
- expanding automated test coverage
- documenting any external service/API integrations used by the app

## Author

**Joshua Wabulo**

- GitHub: https://github.com/Pedurabo
- LinkedIn: https://www.linkedin.com/in/joshua-wabulo-025894275/
