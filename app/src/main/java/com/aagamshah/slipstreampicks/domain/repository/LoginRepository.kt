package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.request.LoginRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.AuthResponseModel

interface LoginRepository {

    suspend fun login(loginRequestModel: LoginRequestModel): AuthResponseModel

}