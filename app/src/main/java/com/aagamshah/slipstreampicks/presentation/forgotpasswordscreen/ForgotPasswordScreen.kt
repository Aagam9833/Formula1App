package com.aagamshah.slipstreampicks.presentation.forgotpasswordscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aagamshah.slipstreampicks.R
import com.aagamshah.slipstreampicks.presentation.components.ErrorPopUp
import com.aagamshah.slipstreampicks.presentation.components.FlipCard
import com.aagamshah.slipstreampicks.presentation.components.LoadingAnimation
import com.aagamshah.slipstreampicks.presentation.components.PrimaryButton
import com.aagamshah.slipstreampicks.ui.theme.AppTypography
import com.aagamshah.slipstreampicks.utils.CardFace

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {

    var cardFace by remember { mutableStateOf(CardFace.Front) }
    var showPopup by remember { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }
    var otp by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirm by rememberSaveable { mutableStateOf("") }
    val errorMessage = forgotPasswordViewModel.errorMessage
    val isLoading = forgotPasswordViewModel.isLoading

    LaunchedEffect(errorMessage) {
        showPopup = errorMessage != null
    }

    Scaffold(containerColor = Color.Black, modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                modifier = Modifier.padding(top = 60.dp),
                style = AppTypography.headlineLarge,
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
                    ForgotPasswordEmailCard(
                        text = email,
                        buttonText = stringResource(R.string.send_otp),
                        onTextChange = { email = it },
                        onButtonClick = {
                            forgotPasswordViewModel.forgotPassword(email) {
                                cardFace = CardFace.Back
                            }
                        }
                    )
                },
                back = {
                    ForgotPasswordOtpCard(
                        otp = otp,
                        password = password,
                        confirm = confirm,
                        buttonText = stringResource(R.string.reset_password),
                        onOtpChange = { otp = it },
                        onConfirmChange = { confirm = it },
                        onPasswordChange = { password = it },
                    ) {
                        forgotPasswordViewModel.resetPassword(email, otp, password, confirm) {
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingAnimation(modifier = Modifier)
            }
        }
    }

    if (!errorMessage.isNullOrEmpty()) {
        ErrorPopUp(errorMessage) { forgotPasswordViewModel.dismissError() }
    }

}

@Composable
fun ForgotPasswordEmailCard(
    text: String,
    buttonText: String,
    onTextChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = stringResource(R.string.email),
            style = AppTypography.displayMedium,
            color = Color.White
        )
        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
            value = text,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = onTextChange,
            label = {
                Text(
                    text = stringResource(R.string.enter_email),
                    style = AppTypography.headlineSmall
                )
            }
        )
        PrimaryButton(
            text = buttonText,
            {
                onButtonClick()
            },
            modifier = Modifier,
            isEnabled = true
        )
    }
}

@Composable
fun ForgotPasswordOtpCard(
    otp: String,
    password: String,
    confirm: String,
    buttonText: String,
    onOtpChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = stringResource(R.string.otp),
            style = AppTypography.displayMedium,
            color = Color.White
        )
        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
            value = otp,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),

            onValueChange = onOtpChange,
            label = {
                Text(
                    text = stringResource(R.string.enter_otp),
                    style = AppTypography.headlineSmall
                )
            }
        )
        Text(
            text = stringResource(R.string.new_password),
            style = AppTypography.displayMedium,
            color = Color.White
        )
        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
            value = password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = onPasswordChange,
            label = {
                Text(
                    text = stringResource(R.string.enter_new_password),
                    style = AppTypography.headlineSmall
                )
            }
        )
        Text(
            text = stringResource(R.string.confirm_password),
            style = AppTypography.displayMedium,
            color = Color.White
        )
        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
            value = confirm,
            visualTransformation = if (confirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = onConfirmChange,
            label = {
                Text(
                    text = stringResource(R.string.confirm_password),
                    style = AppTypography.headlineSmall
                )
            }
        )
        PrimaryButton(
            text = buttonText,
            {
                onButtonClick()
            },
            modifier = Modifier,
            isEnabled = true
        )
    }
}