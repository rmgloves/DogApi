package com.rmgloves.dogapi.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.ui.breedlist.BreedList
import com.rmgloves.dogapi.ui.breedphotos.BreedPhotos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogApiNavigation() {
    val navController = rememberNavController()
    var title by rememberSaveable { mutableStateOf("") }
    var showBackButton by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                navigationIcon = {
                    AnimatedNavigationIcon(showIcon = showBackButton) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.BreedList.route
        ) {
            composable(Screen.BreedList.route) {
                title = stringResource(R.string.app_name)
                showBackButton = false
                BreedList { breed ->
                    navController.navigate(Screen.BreedPhotos.createRoute(breed))
                }
            }
            composable(
                route = Screen.BreedPhotos.route,
                arguments = listOf(navArgument("encodedBreed") { type = NavType.StringType })
            ) {
                val encodedBreed = requireNotNull(it.arguments?.getString("encodedBreed"))
                val decodedBreed = Breed.decodeClass(encodedBreed)
                title = decodedBreed.getDisplayString()
                showBackButton = true
                BreedPhotos(decodedBreed)
            }
        }
    }
}

@Composable
fun AnimatedNavigationIcon(
    imageVector: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    contentDescription: String = stringResource(R.string.back),
    showIcon: Boolean,
    onClick: () -> Unit
) {
    AnimatedContent(
        targetState = showIcon,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = fadeIn(),
                initialContentExit = fadeOut()
            )
        },
        label = "navigation transition"
    ) { show ->
        if (show) {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription
                )
            }
        }
    }
}
