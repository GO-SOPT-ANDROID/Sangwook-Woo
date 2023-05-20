package org.android.go.sopt.service

import org.android.go.sopt.data.ResponseListUsersDto
import retrofit2.Call
import retrofit2.http.GET

interface GetListUsersService {
    @GET("users?page=1")
    fun getusers(
    ): Call<ResponseListUsersDto>
}