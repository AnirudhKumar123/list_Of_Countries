package com.action.walmart_assesment.itemList.domain.repository

import com.action.walmart_assesment.itemList.data.remote.dtos.Country
import com.action.walmart_assesment.itemList.presentation.UiState
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getAllCountries(): Flow<UiState<List<Country>>>
}