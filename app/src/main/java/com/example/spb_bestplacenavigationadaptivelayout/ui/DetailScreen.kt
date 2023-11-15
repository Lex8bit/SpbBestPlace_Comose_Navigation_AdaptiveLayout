package com.example.spb_bestplacenavigationadaptivelayout.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spb_bestplacenavigationadaptivelayout.R
import com.example.spb_bestplacenavigationadaptivelayout.ui.theme.SPb_BestPlaceNavigationAdaptiveLayoutTheme


@Composable
fun DetailScreen(
    uiState: SpbBestPlacesUiState,
    onClick: () -> Unit,
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(uiState.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .height(256.dp)
        )

        Column (
        ){
            Text(
                text = stringResource(uiState.details),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
            Text(
                text = stringResource(R.string.address_with_value, stringResource(uiState.address)),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        BackButton(
            labelResourceId = R.string.Back_Button,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            onClick = onClick
        )
    }
}

@Composable
private fun BackButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(stringResource(labelResourceId))
    }
}

@Preview
@Composable
fun DetailScreenPreview(){
    SPb_BestPlaceNavigationAdaptiveLayoutTheme {
        DetailScreen(
            uiState = SpbBestPlacesUiState(
                R.string.CategoryParks,
                R.string.CategoryParks_PlaceBody1,
                R.drawable.picture1,
                R.string.CategoryParks_Address1,),
            onClick = {},
        )
    }
}