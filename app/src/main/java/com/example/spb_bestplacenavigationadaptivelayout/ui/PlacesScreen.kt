package com.example.spb_bestplacenavigationadaptivelayout.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spb_bestplacenavigationadaptivelayout.PlacesContentType
import com.example.spb_bestplacenavigationadaptivelayout.R
import com.example.spb_bestplacenavigationadaptivelayout.data.Place
import com.example.spb_bestplacenavigationadaptivelayout.data.PlacesDataProvider.allPlaces
import com.example.spb_bestplacenavigationadaptivelayout.ui.theme.SPb_BestPlaceNavigationAdaptiveLayoutTheme

//@Composable
//fun PlacesScreen(
//    contentType: PlacesContentType,
//    places: List<Place>,
//    onNameClick: (Place) -> Unit,
//    modifier: Modifier = Modifier,
//    contentPadding: PaddingValues = PaddingValues(0.dp),
//    onClick: () -> Unit,
//    uiState: SpbBestPlacesUiState,
//){
//    if (contentType == PlacesContentType.ListAndDetail)
//        PlacesScreenAndDetail(
//            places = places,
//            onNameClick = onNameClick,
//            onClick = onClick,
//            uiState = uiState,
//            modifier = modifier
//        )
//    else
//        PlacesScreenListOnly(
//            places = places,
//            onNameClick = onNameClick,
//            modifier = modifier,
//            contentPadding = contentPadding
//        )
//}

@Composable
fun PlacesScreenListOnly(
    places: List<Place>,
    onNameClick: (Place) -> Unit,
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
            PlacesListItem(
                place = place,
                onClick = {onNameClick(place)}
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlacesListItem(
    place: Place,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            Text(
                text = stringResource(place.placesName),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
            Image(
                painter = painterResource(place.image),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun PlacesScreenAndDetail(
    onClick: () -> Unit,
    uiState: SpbBestPlacesUiState,
    places: List<Place>,
    onNameClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        PlacesScreenListOnly(
            places = places,
            onNameClick = {onNameClick(it)},
            modifier = Modifier.weight(1f)
        )
        DetailScreen(
            uiState = uiState,
            onClick = onClick,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
    }
}


@Preview
@Composable
fun PlacesListItemPreview(){
    SPb_BestPlaceNavigationAdaptiveLayoutTheme {
        PlacesListItem(
            place = allPlaces[20],
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PlacesListOnlyPreview(){
    SPb_BestPlaceNavigationAdaptiveLayoutTheme {
        PlacesScreenListOnly(
            places = allPlaces.filter { it.placesCategory == R.string.CategoryParks },
            onNameClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun SportsListAndDetailPreview() {
    SPb_BestPlaceNavigationAdaptiveLayoutTheme {
        Surface {
            PlacesScreenAndDetail(
                places = allPlaces.filter { it.placesCategory == R.string.CategoryParks },
                onNameClick = {},
                onClick = {},
                uiState = SpbBestPlacesUiState(
                    R.string.CategoryParks,
//                    R.string.CategoryParks_PlaceName1,
                    R.string.CategoryParks_PlaceBody1,
                    R.drawable.picture1,
                    R.string.CategoryParks_Address1,
                ),
            )
        }
    }
}