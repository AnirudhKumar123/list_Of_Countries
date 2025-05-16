package com.action.walmart_assesment.itemList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.action.walmart_assesment.itemList.data.remote.dtos.Country
import com.action.walmart_assesment.itemList.domain.repository.CountryRepository
import com.action.walmart_assesment.itemList.presentation.UiState.SUCCESS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemListViewModel(
    private val countryRepository: CountryRepository
): ViewModel() {
    private var countryHolder: List<Country> = emptyList()
    private var _countryState: MutableStateFlow<UiState<List<Country>>> = MutableStateFlow(
        UiState.LOADING)
    val countryState: StateFlow<UiState<List<Country>>> = _countryState.asStateFlow()

    init {
        viewModelScope.launch {
            countryRepository.getAllCountries().collect { country ->
                if(country is SUCCESS) {
                    countryHolder = country.data
                }
                _countryState.value = country
            }
        }
    }
}