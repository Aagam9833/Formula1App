package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.common.Resource
import com.aagamshah.slipstreampicks.domain.model.NavigationModel
import com.aagamshah.slipstreampicks.domain.repository.NavigationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NavigationUseCase @Inject constructor(
    private val navigationRepository: NavigationRepository,
) {

    operator fun invoke(): Flow<Resource<NavigationModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = navigationRepository.getNavigation()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        }
    }

}