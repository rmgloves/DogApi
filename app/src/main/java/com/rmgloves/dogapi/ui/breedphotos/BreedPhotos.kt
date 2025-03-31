package com.rmgloves.dogapi.ui.breedphotos

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rmgloves.dogapi.data.model.Breed

@Composable
fun BreedPhotos(breed: Breed) {
    Text(text = breed.getDisplayString())
}