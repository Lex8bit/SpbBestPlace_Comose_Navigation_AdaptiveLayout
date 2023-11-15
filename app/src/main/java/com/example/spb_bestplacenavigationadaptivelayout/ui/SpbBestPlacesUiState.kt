package com.example.spb_bestplacenavigationadaptivelayout.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SpbBestPlacesUiState(
    @StringRes val category: Int = 0,
//    @StringRes val place: Int = 0,
    @StringRes val details: Int = 0,
    @DrawableRes val image : Int = 0,
    @StringRes val address : Int =0
)

