
# Project Title

Develop a simplified version of a video sharing platform that allows users to view videos
hosted on Bunny.net. (You can use the 14 day free trial) The application should offer a
basic but intuitive interface for users to browse, watch, and interact with videos.

## Tech Stack

**App:** Kotlin, Jetpack Compose

**Architecture:** MVVM

**Dependeny Injection:** Koin




## Installation
Run the project with android studio and install on android device or emulator running from 24 to 34 sdk



## Design Decision
The project makes use of common android patterns in modern android codebases.

Project Structure

The folders are split into 4 boundaries:



    Data:

    Contains data sources ,  remote, this is where the implementation for such is kept. All data related actions and formatting happens in this layer as well.. One common pattern used in this area is the repository pattern, which mediates data sources and acts as a source of truth to the consumer.

    DI:

    This acts as the glue between the core ,data and UI.The UI relies on the core models and interfaces which are implemented in data.

    UI:

    Contains the presentation layer of the app, the screen components and viewmodels. Framework specific dependencies are best suited for this layer. In this layer MVVM is used.

    The screen state e.g UIState is also modelled as a class with immutable properties and makes state management way easier by reducing the state whenever their is a new update received. Some design patterns that can be seen here are the Observer pattern when consuming the flow -> state flows in the composables and provides a reactive app.
       
    Utils:

    This is where common methods and classes are kept for reusability

