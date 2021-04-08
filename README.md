# Square test app

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
* [Author](#author)


<!-- ABOUT THE PROJECT -->
## About The Project

* I'm an adet of "Uncle Bob" clean code and clean architecture: using intention-revealing names and make the code explain itself
* This app is using Android architecture component: Kotlin, MVVM, LiveData, Coroutines, Retrofit, Daggaer 2, coil and repository and useCase patterns
* Unit tests and UI tests are available
* The asset folder contains all the payloads
* I kept the original error message but in real prod apps it should be user friendly with less unnecessary details
* The app is dark theme ready
* The choice for Daggaer 2 and coil is based of what I know better
* The app is ready for phones but can also on tablets since there's no restriction on the orientation
* I used one of my template project that I use to keep me updated with Android changes and new features but no code has been copied from external resources
* I had to look sometimes to stackoverflow.com
* In my regular learning day, I use Android codelabs and raywenderlich.com
* It took me 5 hours to develop the app but I took axtra 30 minutes to cleanup and another 30 minutes to write the readme file
* To test the empty list or/and the malformed payload please update getEmployeesList method from EmployeesRepository class and one of these methods:
```kotlin
    suspend fun getEmployees(): Response<Employees> {
        return employeesApi.getEmployees()
    }

    suspend fun getMalformedEmployees(): Response<Employees> {
        return employeesApi.getMalformedEmployees()
    }

    suspend fun getEmptyEmployees(): Response<Employees> {
        return employeesApi.getEmptyEmployees()
    }
```

<!-- CONTACT -->
## Author

Skander Jabouzi (jabouzi@gmail.com)


