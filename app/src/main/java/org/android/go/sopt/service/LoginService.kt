package org.android.go.sopt.service

import org.android.go.sopt.data.RequestLoginDto
import org.android.go.sopt.data.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}