package org.android.go.sopt.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestOrResponseDto(
    @SerialName("userName")
    val user_name: String
)
