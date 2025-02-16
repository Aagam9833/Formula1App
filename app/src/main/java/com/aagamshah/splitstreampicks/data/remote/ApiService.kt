package com.aagamshah.splitstreampicks.data.remote

import com.aagamshah.splitstreampicks.common.Constants
import com.aagamshah.splitstreampicks.domain.model.HomeModel
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.GET_HOME)
    suspend fun getHome(): HomeModel

}