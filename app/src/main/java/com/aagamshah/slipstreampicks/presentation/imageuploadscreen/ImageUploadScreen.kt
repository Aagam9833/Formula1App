package com.aagamshah.slipstreampicks.presentation.imageuploadscreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.presentation.components.PrimaryButton
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.ui.theme.Formula1Red

@Composable
fun ImageUploadScreen(
    navController: NavController,
    imageUploadViewModel: ImageUploadViewModel = hiltViewModel()
) {

    val isLoading = imageUploadViewModel.isLoading
    val imageUrl = imageUploadViewModel.imageUrl
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current
    val uploadSuccess = imageUploadViewModel.uploadSuccess

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { imageUri = it }
        }

    Scaffold(containerColor = Color.Black) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            Text(
                text = "Skip",
                color = Color.White,
                style = AppTypography.titleLarge,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .clickable {
                        navController.navigate(Route.MainScreen.route) {
                            popUpTo(Route.ImageUploadScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 100.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Upload profile image",
                    style = AppTypography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    AsyncImage(
                        model = imageUri ?: imageUrl,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(screenWidth * 0.7f)
                            .clip(CircleShape)
                            .border(width = 1.dp, color = Formula1Red, shape = CircleShape)
                            .clickable {
                                imagePickerLauncher.launch("image/*")
                            }
                    )
                    if (imageUri == null) {
                        Text(
                            text = "Tap to select an image",
                            style = AppTypography.titleSmall,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            PrimaryButton(
                text = "Upload",
                onClick = {
                    imageUri?.let {
                        imageUploadViewModel.uploadImage(
                            it,
                            context
                        )
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 48.dp),
                isEnabled = true
            )
        }
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        if (uploadSuccess) {
            navController.navigate(Route.MainScreen.route) {
                popUpTo(Route.ImageUploadScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }

    }
}
