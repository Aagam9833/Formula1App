package com.aagamshah.slipstreampicks.data.remote

import com.aagamshah.slipstreampicks.domain.model.request.ForgotPasswordRequestModel
import com.aagamshah.slipstreampicks.domain.model.request.LoginRequestModel
import com.aagamshah.slipstreampicks.domain.model.request.ResetPasswordModel
import com.aagamshah.slipstreampicks.domain.model.request.SaveFantasyTeamRequestModel
import com.aagamshah.slipstreampicks.domain.model.request.SignUpRequestModel
import com.aagamshah.slipstreampicks.domain.model.response.AuthResponseModel
import com.aagamshah.slipstreampicks.domain.model.response.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.model.response.CurrentSeasonModel
import com.aagamshah.slipstreampicks.domain.model.response.DriverStandingModel
import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel
import com.aagamshah.slipstreampicks.domain.model.response.HomeModel
import com.aagamshah.slipstreampicks.domain.model.response.MessageResponseModel
import com.aagamshah.slipstreampicks.domain.model.response.NavigationModel
import com.aagamshah.slipstreampicks.domain.model.response.ProfileImageUploadResponse
import com.aagamshah.slipstreampicks.domain.model.response.RaceResultModel
import com.aagamshah.slipstreampicks.utils.Constants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.GET_HOME)
    suspend fun getHome(): HomeModel

    @GET(Constants.GET_DRIVER_STANDING)
    suspend fun getDriverStanding(): DriverStandingModel

    @GET(Constants.NAVIGATION)
    suspend fun getNavigation(): NavigationModel

    @GET(Constants.CURRENT_SEASON)
    suspend fun getCurrentSeason(): CurrentSeasonModel

    @GET(Constants.GET_CONSTRUCTOR_STANDINGS)
    suspend fun getConstructorStandings(): ConstructorStandingModel

    @GET(Constants.GET_ROUND_RESULT)
    suspend fun getRoundResults(@Query("round") round: String): RaceResultModel

    @POST(Constants.SIGN_UP)
    suspend fun postSignUp(@Body signUpRequestModel: SignUpRequestModel): AuthResponseModel

    @POST(Constants.LOGIN)
    suspend fun postLogin(@Body loginRequestModel: LoginRequestModel): AuthResponseModel

    @GET(Constants.GET_FANTASY_HOME)
    suspend fun getFantasyHome(@Query("user_id") userId: String): GetFantasyHomeResponseModel

    @POST(Constants.SAVE_FANTASY_TEAM)
    suspend fun setFantasyTeam(@Body data: SaveFantasyTeamRequestModel): MessageResponseModel

    @Multipart
    @POST(Constants.UPLOAD_PROFILE_IMAGE)
    suspend fun uploadProfileImage(
        @Part image: MultipartBody.Part,
        @Part("user_id") userId: RequestBody
    ): ProfileImageUploadResponse

    @POST(Constants.FORGOT_PASSWORD)
    suspend fun forgotPassword(@Body email: ForgotPasswordRequestModel): MessageResponseModel

    @POST(Constants.RESET_PASSWORD)
    suspend fun resetPassword(@Body data: ResetPasswordModel): MessageResponseModel

}