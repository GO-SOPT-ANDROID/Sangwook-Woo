package org.android.go.sopt.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
)
