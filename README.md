# Pokémon Compose App

A modern Android app that lists Pokémon and shows detailed info about each, built with Jetpack Compose and clean architecture.

---

## Features

- Paginated Pokémon list fetched from a remote API
- Detailed screen with Pokémon's name, types, abilities, height, and weight
- Smooth navigation with Jetpack Compose Navigation
- Material 3 UI components and theming
- Dependency Injection with Hilt
- Paging 3 integration for efficient data loading
- Unit and UI testing (Compose testing + ViewModel tests)
- Support for dark mode 

---

## Architecture

- **Clean Architecture**: Separation into `data`, `domain`, and `presentation` modules
- **MVVM Pattern**: ViewModels manage UI state with Kotlin Flows and PagingData
- **Use Cases**: Encapsulate business logic for better testability
- **Repository Pattern**: Abstracts data sources (network/local)
- **Jetpack Compose**: Declarative UI for fast, maintainable UI development
- **Hilt**: Simplified dependency injection
- **Paging 3**: Efficient data pagination and caching

---

## Libraries Used

- [Jetpack Compose]
- [Hilt]
- [Paging 3]
- [Coil] for image loading
- [Kotlin Coroutines & Flow]
- [Jetpack Navigation Compose]
- [Mockk] for unit testing mocks
- [Jetpack Compose Testing]

---

## Project Structure

- `data/`: Data sources, network API, repository implementations
- `domain/`: Business logic, use cases, domain models
- `presentation/`: UI code including Composables, ViewModels, navigation, and theming

---

## Testing

- Unit tests for use cases with Mockk and coroutine test utils
- UI tests using Jetpack Compose testing framework, integrated with Hilt and a Fake ViewModel

---
