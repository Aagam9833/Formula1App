package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.request.SignUpRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.AuthResponseModel

interface SignUpRepository {

    suspend fun signUp(signUpRequestModel: SignUpRequestModel): AuthResponseModel

}