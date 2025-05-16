package com.action.walmart_assesment.itemList.data.repositoryImpl

import com.action.walmart_assesment.itemList.data.remote.CountryApiClientProvider
import com.action.walmart_assesment.itemList.data.remote.dtos.Country
import com.action.walmart_assesment.itemList.domain.repository.CountryRepository
import com.action.walmart_assesment.itemList.presentation.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl: CountryRepository {
    override fun getAllCountries(): Flow<UiState<List<Country>>> = flow {
        emit(UiState.LOADING)
        try {
            val countryApiClient = CountryApiClientProvider.apiClient
            val response = countryApiClient.getAllCountries()
            if(response.isSuccessful) {
                val body = response.body()
                if(body == null) {
                    emit(UiState.ERROR(Exception("Body is null or empty")))
                } else {

                    emit(UiState.SUCCESS(data = body.map {
                        return@map Country(
                            name = it.name,
                            region = it.region,
                            code = it.code,
                            capital = it.capital
                        )
                    }))
                }
            } else {
                emit(UiState.ERROR(Exception("Response was not successful")))
            }
        } catch(e: Exception) {
            emit(UiState.ERROR(e))
        }
    }
}