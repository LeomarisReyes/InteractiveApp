 ![version](https://img.shields.io/badge/version-1.0.0-pink)
 
## 💄 Interactive Android App

### Table of Contents:

- [General information](#general-information)
	 - [Description](#description)
	 - [Requeriments](#requeriments)
  - [Extras requeriments](#extra-requeriments)
- [Feature Demo](#feature-demo)
	 - [API Documentation](#api-documentation)
	 - [Built With](#built-with) 

___

### General information

### Description

Make-up App was created with Kotlin and Compose for Android. This app gives you access to a complete list of cosmetic products loaded from an API. You can also see the details of each product by tapping on them. This includes the name, brand, description, available colors, and tags.

The application also supports light and dark appearance modes. You can easily identify it on your device by looking for the lipstick icon.

⚠ *This application was created specifically for technical testing purposes. The requested requirements and all the features of this app are detailed below.* 

### Requeriments

Create an application with the following points:


|No.| Description |
|--|--|
| 1. | **Api List:** Create a view that consumes a REST API (you can choose a free API). The view should display a paginated list of items. Additionally, it should have a search functionality that searches a specific parameter in the list of objects provided by the API. Upon selecting an item from the list, the application should open a screen to display the details of the selected item. |
| 2. | **Capitalizer generator:** Create a view with an input field. The user can enter a phrase in this field. The operation to perform on the phrase is as follows: convert the first letter of each word to uppercase and display it on the screen. Language-specific functions such as split, capitalize, filters, etc., cannot be used. | 
| 3. | **Password generator:** Create a view capable of generating random passwords. Passwords should be configurable with the following parameters: Length: Between 5 and 20 characters. - With or without uppercase letters , numbers and symbols - These parameters can be combined. Password generation should be done without using special language functions or external libraries. |

### Feature Demo 

In this session, you will explore visual resources designed to showcase each functionality of the app in a clear and engaging way.

The test requires three functionalities, so an intermediate menu was created within the app to provide a more user-friendly experience, allowing users to easily select each functionality as an option.


<p align="left"><img src="https://github.com/user-attachments/assets/2da68544-affd-4a43-8aa2-3ac3b0c2c082" width=420 height=800/></p> 


#### ➖ 1. Colombian Presidents: List, Search, and Details

To fulfill this requirement, a view was implemented that consumes the public Colombian data API, API-Colombia (https://api-colombia.com), specifically the presidents endpoint, which allowed me to display the list of presidents interactively. The features include:

* **Paginated List:**
The list of presidents is loaded in a paginated manner. Initially, only the first few presidents are shown, and as the user scrolls down, the next presidents are automatically loaded. This pagination optimizes data loading and visualization, preventing excessive memory consumption and improving the user experience.

* **Selecting a President:**
By selecting any president from the list, the user is taken to a screen that shows the full details of the selected president, such as their term, political party, and other relevant data.

* **Search Bar:**
The application includes a search bar that allows filtering the list of presidents by name. As the user types in the search field, the list automatically updates to show only the presidents whose names match the entered term.

**Public API Used:**
The data source used is API-Colombia, a public API with which I have collaborated on open-source contributions, providing information about Colombian presidents, among other Colombian-related data.
<p align="left"><img src="https://github.com/LeomarisReyes/FashionMakeUp/blob/RemoteData/Images/LigthMode.png" width=860 height=420/></p> 

<!-- Agregar Demo aqui -->	

#### ➖ 2. Capitalizer generator

A screen was developed where you can enter a phrase, and upon clicking the button, it converts the first letter of each word to uppercase while changing the remaining letters to lowercase, if necessary.

<!-- Agregar Demo aqui -->	

#### ➖ 3. Password generator


### Built With

- [Compose](https://developer.android.com/jetpack/compose/) 
- [Compose navigation](https://developer.android.com/jetpack/compose/navigation?hl=es-419) ![version](https://img.shields.io/badge/version-1.3.9-pink)
- [Material](https://m3.material.io) ![version](https://img.shields.io/badge/version-3-pink)
- [Coroutines](https://developer.android.com/kotlin/coroutines) ![version](https://img.shields.io/badge/version-3-pink)
- [Coil](https://github.com/coil-kt/coil) ![version](https://img.shields.io/badge/version-1.4.01-pink)
- [Retrofit](https://square.github.io/retrofit/) ![version](https://img.shields.io/badge/version-2.9.0-pink)
- [Dagger Hilt](https://developer.android.com/jetpack/androidx/releases/hilt?hl=es-419) ![version](https://img.shields.io/badge/version-2.44-pink)
- [Gson](https://github.com/google/gson) ![version](https://img.shields.io/badge/version-2.8.8-pink)

___

Thanks for reading! 💚💕 <br />

*@Composable<br />
fun Thanks(){<br />
    Text(text = "Leomaris Reyes")<br />
}<br />*

