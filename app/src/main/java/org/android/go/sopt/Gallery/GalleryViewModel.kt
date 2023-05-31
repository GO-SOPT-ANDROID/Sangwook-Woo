package org.android.go.sopt.Gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.ServicePool
import org.android.go.sopt.body.ContentUriRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel: ViewModel() {
    private val service = ServicePool.imageService
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun uploadProfileImage() {
        if (image.value == null) { /* 아직 사진이 등록되지 않았다는 로직 처리 */
        } else {
            service.uploadImage(image.value!!.toFormData()).enqueue(
                object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            Log.e("sangwook", "success !!!")
                        }else{
                            Log.e("sangwook", "fail !!!")
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        TODO("Not yet implemented")
                        Log.e("sangwook", "onFailure")
                    }

                }
            )
        }
    }
}