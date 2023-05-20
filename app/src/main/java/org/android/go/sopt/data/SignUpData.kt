package org.android.go.sopt.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpData(
    @SerialName("name")
    val name: String,
    @SerialName("skill")
    val skill: String,
)