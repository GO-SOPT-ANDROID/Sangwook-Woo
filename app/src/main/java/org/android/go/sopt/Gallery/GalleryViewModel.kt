package org.android.go.sopt.Gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.ResponseMusicListDto
import org.android.go.sopt.service.MusicService
import retrofit2.Call
import retrofit2.Response

class GalleryViewModel : ViewModel() {
    private val _musicListResult: MutableLiveData<ResponseMusicListDto> = MutableLiveData()
    val musicListResult: LiveData<ResponseMusicListDto> = _musicListResult

    private val musicService = ServicePool.musicService

    fun completeGetMusic() {
        musicService.getmusics().enqueue(object : retrofit2.Callback<ResponseMusicListDto> {
            override fun onResponse(
                call: Call<ResponseMusicListDto>,
                response: Response<ResponseMusicListDto>,
            ) {
                if (response.isSuccessful) {
                    _musicListResult.value = response.body()
                    Log.e("test", "서버통신 성공")

                } else {
                    Log.e("test", "서버통신 실패 404")
                }
            }

            override fun onFailure(call: Call<ResponseMusicListDto>, t: Throwable) {
                Log.e("test", "서버통신 실패 응답값X")
            }
        })
    }
}