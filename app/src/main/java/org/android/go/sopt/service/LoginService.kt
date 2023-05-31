package org.android.go.sopt.service

import okhttp3.MultipartBody
import org.android.go.sopt.data.RequestLoginDto
import org.android.go.sopt.data.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface LoginService {
    @POST("sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<Unit>
}
