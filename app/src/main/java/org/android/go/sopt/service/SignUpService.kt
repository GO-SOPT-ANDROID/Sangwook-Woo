package org.android.go.sopt.service

import org.android.go.sopt.data.RequestSignUpDto
import org.android.go.sopt.data.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up")
    fun signup(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}