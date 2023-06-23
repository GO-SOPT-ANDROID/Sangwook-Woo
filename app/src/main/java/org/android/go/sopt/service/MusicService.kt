package org.android.go.sopt.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.android.go.sopt.data.ResponseMusicListDto
import org.android.go.sopt.data.ResponseUploadMusicDto
import retrofit2.Call
import retrofit2.http.*

interface MusicService {
    @GET("/asd133/music")
    fun getmusics(
    ): Call<ResponseMusicListDto>
    @Multipart
    @POST("/music")
    fun uploadMusic(
        @Part image: MultipartBody.Part,
        @Part("title") title: RequestBody,
        @Part("singer") singer: RequestBody,
        @Header("id") id: String = "asd133",
    ): Call<Unit>
}