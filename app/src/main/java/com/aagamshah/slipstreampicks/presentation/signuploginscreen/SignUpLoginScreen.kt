package com.aagamshah.slipstreampicks.presentation.signuploginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.navigation.Route
import com.aagamshah.slipstreampicks.presentation.components.ErrorPopUp
import com.aagamshah.slipstreampicks.presentation.components.FlipCard
import com.aagamshah.slipstreampicks.presentation.components.LoadingAnimation
import com.aagamshah.slipstreampicks.presentation.components.LoginUI
import com.aagamshah.slipstreampicks.presentation.components.PrimaryButton
import com.aagamshah.slipstreampicks.presentation.components.SignupUI
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.utils.CardFace

@Composable
fun SignUpLoginScreen(
    navController: NavHostController,
    signUpLoginViewModel: SignUpLoginViewModel = hiltViewModel()
) {
    var cardFace by remember { mutableStateOf(CardFace.Front) }
    var signUpUsername by rememberSaveable { mutableStateOf("") }
    var signUpEmail by rememberSaveable { mutableStateOf("") }
    var signUpPassword by rememberSaveable { mutableStateOf("") }
    var loginIdentifier by rememberSaveable { mutableStateOf("") }
    var loginPassword by rememberSaveable { mutableStateOf("") }
    var showPopup by remember { mutableStateOf(false) }
    val errorMessage = signUpLoginViewModel.errorMessage
    val isLoading = signUpLoginViewModel.isLoading

    LaunchedEffect(errorMessage) {
        showPopup = errorMessage != null
    }

    Scaffold(containerColor = Color.Black, modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 100.dp),
                    text = stringResource(R.string.app_name),
                    style = AppTypography.displayLarge,
                    color = Color.White
                )
                FlipCard(
                    cardFace = cardFace,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 32.dp,
                        bottom = 16.dp
                    ),
                    front = {
                        LoginUI(
                            username = loginIdentifier,
                            password = loginPassword,
                            onUsernameChange = { loginIdentifier = it },
                            onPasswordChange = { loginPassword = it },
                            flip = { cardFace = cardFace.next })
                    },
                    back = {
                        SignupUI(
                            username = signUpUsername,
                            email = signUpEmail,
                            password = signUpPassword,
                            onUsernameChange = { signUpUsername = it },
                            onEmailChange = { signUpEmail = it },
                            onPasswordChange = { signUpPassword = it },
                            flip = { cardFace = cardFace.next }
                        )
                    }
                )
            }
            PrimaryButton(
                stringResource(R.string.proceed),
                {
                    if (cardFace == CardFace.Front) {
                        signUpLoginViewModel.loginValidations(loginIdentifier, loginPassword) {
                            navController.navigate(Route.MainScreen.route) {
                                popUpTo(Route.SignUpLoginScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    } else {
                        signUpLoginViewModel.signUpValidations(
                            signUpUsername,
                            signUpEmail,
                            signUpPassword,
                        ) {
                            navController.navigate(Route.ImageUploadScreen.route) {
                                popUpTo(Route.SignUpLoginScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    }
                },
                Modifier,
                !isLoading
            )
        }
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingAnimation(modifier = Modifier)
            }
        }
    }

    if (!errorMessage.isNullOrEmpty()) {
        ErrorPopUp(errorMessage) { signUpLoginViewModel.dismissError() }
    }
}
