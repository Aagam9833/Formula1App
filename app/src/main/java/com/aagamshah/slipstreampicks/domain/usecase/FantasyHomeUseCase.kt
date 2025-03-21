package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.domain.model.request.SaveFantasyTeamRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel
import com.aagamshah.slipstreampicks.domain.model.response.MessageResponseModel
import com.aagamshah.slipstreampicks.domain.repository.GetFantasyHomeRepository
import com.aagamshah.slipstreampicks.domain.repository.SaveFantasyTeamRepository
import com.aagamshah.slipstreampicks.domain.repository.UserRepository
import com.aagamshah.slipstreampicks.presentation.components.FantasyCardModel
import com.aagamshah.slipstreampicks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class FantasyHomeUseCase @Inject constructor(
    private val getFantasyHomeRepository: GetFantasyHomeRepository,
    private val saveFantasyTeamRepository: SaveFantasyTeamRepository,
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

    operator fun invoke(data: List<FantasyCardModel>): Flow<Resource<MessageResponseModel>> = flow {
        try {
            emit(Resource.Loading())
            val userId = userRepository.getUser()?.id

            if (userId.isNullOrEmpty()) {
                emit(Resource.Error("User not logged in"))
            } else {
                val saveFantasyTeamRequestModel = SaveFantasyTeamRequestModel(
                    user_id = userId,
                    driver_1_id = data[0].id,
                    driver_2_id = data[1].id,
                    driver_3_id = data[2].id,
                    driver_4_id = data[3].id,
                    driver_5_id = data[4].id,
                    constructor_id = data[5].id
                )
                val response = saveFantasyTeamRepository.saveTeam(saveFantasyTeamRequestModel)
                emit(Resource.Success(response))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        }
    }

}