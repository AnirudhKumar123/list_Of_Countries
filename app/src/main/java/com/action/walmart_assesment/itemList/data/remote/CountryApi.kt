package com.action.walmart_assesment.itemList.data.remote

import com.action.walmart_assesment.itemList.data.remote.dtos.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("countries.json")
    suspend fun getAllCountries(): Response<List<Country>>
}