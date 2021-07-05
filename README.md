# kotlinx-gtk

GTK bindings for Kotlin via DSL

Version: `0.1.0-alpha`

## Usage

See `example/src/nativeMain/kotlin/Main.kt` for a simple example on
how to create an application using this library

## Progress

Check out the [status](nativex/README.md#Progress) of the wrapping

## Installation

Due to my own limitations, I am unsure of how to properly go around publishing this library onto maven central. Instead,
we will publish to mavenLocal

### Linux

Within the working directory
`./gradlew publishToMavenLocal`

### Windows

Figure it out

### MacOS

Heh

### Configure your project

After you published to your local maven repository, we will have to configure your projects

1. Add `mavenLocal()` to your own projects repositories
2. Add the following required dependency, replacing {KOTLIN} with your version of kotlin
   ```kotlin
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:{KOTLIN}-native-mt")
   ```
3. Add the dependencies, replacing {VERSION} with the current version
   ```kotlin 
   // Core library
   implementation("org.gnome.kotlinx-gtk:nativex:{VERSION}")
	   
   // DSL library
   implementation("org.gnome.kotlinx-gtk:dsl:{VERSION}")
   ```

## Contribution

Just help or ask me to implement something

## Future plans

- Separate DSL out of this project, allowing it to update separately from the project
- Finish more complex parts of this library
