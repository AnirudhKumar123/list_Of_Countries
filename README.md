# list_Of_Countries
# data.remote.dtos
### Country.kt
### Purpose: Represents the structure of the country data received from the remote API.
### Fields:
capital: Capital city of the country.
code: Country code (e.g., "US").
name: Name of the country.
region: Region to which the country belongs (e.g., "Asia").

### CountryApi.kt
### Purpose: Retrofit interface to define the endpoint for fetching the list of countries.
getAllCountries(): A suspend function that returns a Response<List<Country>> from the countries.json endpoint.

### CountryApiClientProvider.kt
### Purpose: Provides the Retrofit client instance configured with:
Base URL: Hosted countries.json file.
GsonConverterFactory: For JSON parsing.
CountryApi: Created using Retrofit builder.


## data.repositoryImpl
### CountryRepositoryImpl.kt
### Purpose: Implements CountryRepository and fetches data from the API using CountryApiClientProvider.
#### Flow Logic:
Emits LOADING state.
#### If API call is successful:
Emits SUCCESS with mapped country list.
#### If not:
Emits ERROR with the appropriate exception.

## domain.repository
### CountryRepository.kt
### Purpose: Abstracts the data source, allowing the ViewModel to remain unaware of whether the data is local or remote.

## presentation
### recyclerview
### ItemListAdapter.kt
### Purpose: Displays a list of countries in a RecyclerView.
ViewHolder binds each Country to a layout defined in CountryItemBinding.
updateData() updates the dataset and refreshes the view.

### UiState.kt
### Purpose: Represents UI states used for displaying:
Loading indicator
Success state with data
Error messages
This helps manage state cleanly in a ViewModel.

### ItemListViewModel.kt
### Purpose: Fetches country data using CountryRepository and exposes it as a StateFlow to the UI.
On init, triggers a coroutine that collects from the repository.
Updates _countryState based on result (SUCCESS, LOADING, ERROR).

### ItemListFragment.kt
### Purpose: Displays the country list using a RecyclerView.
Initializes ItemListAdapter.
Observes countryState from ItemListViewModel.
Updates the UI based on state (ProgressBar, ErrorMessage, or RecyclerView).
### Best Practices Used:
ViewModel scoped to fragment lifecycle.
Custom ViewModelProvider.Factory for injecting the repository.

### MainActivity.kt
### Purpose: Hosts the navigation graph using a FragmentContainerView.
Uses enableEdgeToEdge() and WindowInsetsCompat for modern full-screen layout.


## res/layout
### activity_main.xml
### Purpose: Hosts the navigation graph for fragment transactions.

### country_item.xml (via CountryItemBinding)
### Purpose: Layout for displaying a single Country item in the list.

## Summary of Architecture
### Architecture Pattern: MVVM with Clean Principles
#### Data Layer:
CountryRepositoryImpl, CountryApi, CountryApiClientProvider
#### Domain Layer:
CountryRepository
#### Presentation Layer:
ItemListViewModel, ItemListFragment, UiState, ItemListAdapter

## Libraries Used:
Retrofit (networking)
Coroutines + Flow (async data streams)
RecyclerView (UI)
StateFlow (state management)
ViewModel (lifecycle-safe state)
ViewBinding (type-safe view access)

<img width="381" alt="Screenshot 2025-05-16 at 3 34 03 PM" src="https://github.com/user-attachments/assets/1dfc7042-8b42-4f6f-b11b-b3a29c02c8ea" />

<img width="381" alt="Screenshot 2025-05-16 at 3 34 26 PM" src="https://github.com/user-attachments/assets/6c36494a-b123-4b3a-a56a-346998b62caf" />

https://github.com/user-attachments/assets/4de7b1f5-03e5-4228-8f13-e054fc048eeb
https://www.loom.com/share/8c3fca3b7ced4721a41eb54980d823c0?sid=5132bf8e-0e3b-4a8b-973c-fc398527aa93




