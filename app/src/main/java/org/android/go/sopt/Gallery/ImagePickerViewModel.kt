package org.android.go.sopt.Gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.android.go.sopt.ServicePool.musicService
import org.android.go.sopt.data.ResponseSignUpDto
import org.android.go.sopt.data.ResponseUploadMusicDto
import org.android.go.sopt.util.ContentUriRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagePickerViewModel : ViewModel() {
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image


    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun uploadMusicInfo(title:String, singer:String) {
        if (image.value == null) {
        } else {
            musicService
                .uploadMusic(
                    image.value!!.toFormData(),
                    title.toRequestBody("text/plain".toMediaTypeOrNull()),
                    singer.toRequestBody("text/plain".toMediaTypeOrNull())
                )
                .enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            Log.e("test", "success !!!")
                        }else{
                            Log.e("test", "fail !!!")
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.e("test", "서버통신 실패 !!!")
                    }

                }
            )
        }
    }
}
