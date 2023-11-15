package com.example.spb_bestplacenavigationadaptivelayout

import androidx.lifecycle.ViewModel
import com.example.spb_bestplacenavigationadaptivelayout.data.Place
import com.example.spb_bestplacenavigationadaptivelayout.ui.SpbBestPlacesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SpbBestPlacesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SpbBestPlacesUiState())
    val uiState: StateFlow<SpbBestPlacesUiState> = _uiState


    /**
     * Получили Стринг Ресурс на выбранную категорию
     */
    fun pickCategory(category: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                category = category
            )
        }
    }

    /**
     * Получили конкретно выбранный Place
     */
    fun pickPlace(place: Place) {
        _uiState.update { currentState ->
            currentState.copy(
                image = place.image,
                details = place.placesDescription,
                address = place.placesAddress
            )
        }
    }

}