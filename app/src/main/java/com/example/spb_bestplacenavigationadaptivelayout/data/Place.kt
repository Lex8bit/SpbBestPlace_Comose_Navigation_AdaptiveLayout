package com.example.spb_bestplacenavigationadaptivelayout.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    /** Картинка для места*/
    @DrawableRes val image: Int,
    /** Категория*/
    @StringRes val placesCategory: Int,
    /** Название места*/
    @StringRes val placesName: Int,
    /** Описание места*/
    @StringRes val placesDescription: Int,
    /** Адрес места*/
    @StringRes val placesAddress: Int,
)
