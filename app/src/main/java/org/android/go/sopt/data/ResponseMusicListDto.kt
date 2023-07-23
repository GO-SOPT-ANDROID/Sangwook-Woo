package org.android.go.sopt.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMusicListDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Data,
) {
    @Serializable
    data class Data(
        @SerialName("musicList")
        val musicList: List<Music>
    ) {
        @Serializable
        data class Music(
            @SerialName("singer")
            val singer: String,
            @SerialName("title")
            val title: String,
            @SerialName("url")
            val url: String
        )
    }
}
