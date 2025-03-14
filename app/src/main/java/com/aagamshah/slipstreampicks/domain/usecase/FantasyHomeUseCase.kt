package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel
import com.aagamshah.slipstreampicks.domain.repository.GetFantasyHomeRepository
import com.aagamshah.slipstreampicks.domain.repository.UserRepository
import com.aagamshah.slipstreampicks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class FantasyHomeUseCase @Inject constructor(
    private val getFantasyHomeRepository: GetFantasyHomeRepository,
    private val userRepository: UserRepository,
) {

    operator fun invoke(): Flow<Resource<GetFantasyHomeResponseModel>> = flow {
        try {
            emit(Resource.Loading())
            val userId = userRepository.getUser()?.id
            if (userId.isNullOrEmpty()) {
                emit(Resource.Error("User not logged in"))
            } else {
                val data = getFantasyHomeRepository.getFantasyHome(userId)
                emit(Resource.Success(data))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        }
    }

}