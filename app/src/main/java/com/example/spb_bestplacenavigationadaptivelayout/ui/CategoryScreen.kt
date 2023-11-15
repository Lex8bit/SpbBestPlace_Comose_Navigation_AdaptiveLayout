package com.example.spb_bestplacenavigationadaptivelayout.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spb_bestplacenavigationadaptivelayout.R
import com.example.spb_bestplacenavigationadaptivelayout.data.PlacesDataProvider.allPlaces
import com.example.spb_bestplacenavigationadaptivelayout.ui.theme.SPb_BestPlaceNavigationAdaptiveLayoutTheme

@Composable
fun CategoryScreen(
    places: List<Int>,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(
            top = dimensionResource(R.dimen.padding_medium),
            bottom = dimensionResource(R.dimen.padding_medium)
        ),
    ) {
        items(places) { place ->
            CategoryListItem(
                place = place,
                onClick = {onCategoryClick(place)}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryListItem(
    place: Int,
    onClick: () -> Unit,
     ) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .size(dimensionResource(R.dimen.card_category_height)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.card_text_vertical_space)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = stringResource(place),
                fontSize = 36.sp,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview
@Composable
fun CategoryListItemPreview(){
    SPb_BestPlaceNavigationAdaptiveLayoutTheme {
        CategoryListItem(
            place = allPlaces.map{ it.placesCategory }.toSet().toList().first(),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun CategoryScreenPreview(){
    SPb_BestPlaceNavigationAdaptiveLayoutTheme {
        CategoryScreen(
            places = allPlaces.map{ it.placesCategory }.toSet().toList(),
            onCategoryClick = {}
        )
    }
}