package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.utils.ApiExceptionHandler
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.NavigationModel
import com.aagamshah.slipstreampicks.domain.repository.NavigationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NavigationUseCase @Inject constructor(
    private val navigationRepository: NavigationRepository,
) {

    operator fun invoke(): Flow<Resource<NavigationModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = navigationRepository.getNavigation()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(ApiExceptionHandler.handleException(e)))
        }
    }

}