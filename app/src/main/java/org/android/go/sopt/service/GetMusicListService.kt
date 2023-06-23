package org.android.go.sopt.service

import org.android.go.sopt.data.ResponseMusicListDto
import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("daehwan2yo/music")
    fun getmusics(
    ): Call<ResponseMusicListDto>
}