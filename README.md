# Project Title

Android app using Jetpack Compose with Kotlin and Java (Gradle build).

## Overview

This project is an Android application built with Kotlin (primary) and Java, using Gradle. The UI is implemented with Jetpack Compose and follows recommended state management patterns (mutable state, remember, state hoisting) and separation of UI behavior logic from business/data layers.

## Tech Stack

- Kotlin, Java
- Jetpack Compose
- Android Gradle Plugin / Gradle
- Android Studio Otter 2 Feature Drop \| 2025.2.2 Patch 1 (development IDE)

## Key Concepts

- Layout primitives: `Column`, `Row`, and `Box` are the primary Compose layout containers.
- State: Use `mutableStateOf` together with `remember` or `rememberSaveable` to add local state inside composables.
- State hoisting: Lift state to a single owner (e.g., a `ViewModel`) and pass state + event callbacks to stateless composables to avoid duplicate sources of truth.
- UI behavior logic: Keep presentation and UI reaction logic in the UI layer; place business rules and side-effects in business/data layers (ViewModel, repository).

## Project Structure (example)

- `app/` \- Android app module
    - `src/main/java/` \- Java/Kotlin source
    - `src/main/res/` \- resources
    - `src/androidTest/`, `src/test/` \- tests

## Build & Run (Windows)

- Open the project in Android Studio Otter and use the Play button to run on an emulator or device.
- From command line (project root):
    - To assemble debug APK: `gradlew.bat assembleDebug`
    - To install on a connected device: `gradlew.bat installDebug`
    - To run unit tests: `gradlew.bat test`
    - To run instrumented tests: `gradlew.bat connectedAndroidTest`

## Notes

- Follow Compose best practices: prefer stateless composables and hoist state to `ViewModel` when needed.
- Use `rememberSaveable` for state that should survive process death (where appropriate).
- Use `Arrangement` and `Alignment` for positioning inside `Row`/`Column`, and `Spacer(Modifier.weight(1f))` to push items.

## Contributing

1. Create a branch for your feature or fix.
2. Open a pull request with a clear description.

## License

Add a license file if required.
