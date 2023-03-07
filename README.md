# PokedexApp

Pokemon Purchase with PokedexApp

This project is a feature prototype which uses the PokeAPI as a data source and allows users to check if they have sufficient funds to purchase a Pokemon based on its base experience.

Solution:
This Android app uses Kotlin and leverages MVVM Architecture for inter-fragment communication with ViewModels, Retrofit and OkHttp for network calls and Kotlin coroutines for asynchronous operations. The user enters the name of a Pokemon and the app checks if the user has sufficient funds to purchase it. The purchase price for a Pokemon is calculated as 1% of the base experience multiplied by 6.
The user profile is included in the app, which contains the name, last name, account number, balance, and email address of the user.

Data Definition
The PokeAPI data contains information about a Pokemon's name, abilities, base experience, id, and more. A description of the target pokemon data is included in the main UI.

Technologies Used
The app was built using Kotlin, ViewModel, Kotlin coroutines, ViewBinding, OkHttp, Retrofit, LiveData, DaggerHilt.


Usage
Enter the name of a Pokemon in the search bar
If the Pokemon exists and the user has sufficient funds, the purchase summary and a "complete purchase" button will play the classic sound of purchasing an item at the store from Pokemon Red/Blue.
If the Pokemon does not exist or the user does not have sufficient funds, an error message will be displayed in the form of a Toast.

Note
The app was partially developed within 4 hours, the remainder of the UI and business logic was completed afterwards. There were some Gradle build issues surrounding DaggerHilt that had slowed development but the feature has been completed. The details of the solution were kept in mind while developing it. This app is not meant for external use and is developed solely for the purpose of assessment.
