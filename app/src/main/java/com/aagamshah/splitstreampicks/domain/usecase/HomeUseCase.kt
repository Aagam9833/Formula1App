package com.aagamshah.splitstreampicks.domain.usecase

import com.aagamshah.splitstreampicks.common.Resource
import com.aagamshah.splitstreampicks.domain.model.HomeModel
import com.aagamshah.splitstreampicks.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {

    operator fun invoke(): Flow<Resource<HomeModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = homeRepository.getHome()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        }
    }

}