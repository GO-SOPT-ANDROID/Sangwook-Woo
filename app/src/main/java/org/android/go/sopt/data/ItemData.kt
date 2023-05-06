package org.android.go.sopt.data

import androidx.annotation.DrawableRes

data class ItemData(
    @DrawableRes val image : Int,
    val name: String,
    val team: String
)
