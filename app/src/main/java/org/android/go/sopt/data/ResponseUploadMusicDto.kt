package org.android.go.sopt.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUploadMusicDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<MusicData>,
) {
    @Serializable
    data class MusicData(
        @SerialName("title")
        val title: String,
        @SerialName("singer")
        val singer: String,
        @SerialName("url")
        val url: String
    )
}
