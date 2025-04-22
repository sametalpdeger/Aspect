package com.example.aspectchat.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aspectchat.screens.signup.presentation.view.FormSection
import com.example.aspectchat.screens.signup.presentation.view.HaveAccountBtn
import com.example.aspectchat.screens.signup.presentation.view.MainLayout
import com.example.aspectchat.screens.signup.presentation.viewModels.FormSectionViewModel


@Composable
fun SignUpScreen(
) {
    MainLayout {
        val formSectionViewModel = hiltViewModel<FormSectionViewModel>()
        val nickname = formSectionViewModel._nickname.collectAsStateWithLifecycle()
        val encryptionKey = formSectionViewModel._encryptionKey.collectAsStateWithLifecycle()
        val isLoading = formSectionViewModel._isLoading.collectAsStateWithLifecycle()
        val error = formSectionViewModel._isError.collectAsStateWithLifecycle()

        FormSection(
            onContinue = { formSectionViewModel.onContinue() },
            onChangeNickname = { formSectionViewModel.setNickname(it) },
            onChangeEncryptionKey = { formSectionViewModel.setEncryptionKey(it) },
            nickname = nickname.value,
            encryptionKey = encryptionKey.value,
            isLoading = isLoading.value,
            error = error.value,
            setSuccessful = { }
        )

        Spacer(modifier = Modifier.height(20.dp))

        HaveAccountBtn(
            onClick = { }
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Preview
@Composable
fun SignUpScreenPreview() {
    MainLayout {
        Spacer(modifier = Modifier.height(20.dp))

        FormSection(
            onContinue = { },
            onChangeNickname = {},
            onChangeEncryptionKey = { },
            nickname = "adsadsa",
            encryptionKey = "",
            isLoading = false,
            error = false,
            setSuccessful = { }
        )

        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier
                .height(2.dp)
                .background(Color(0x59ffffff))
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        HaveAccountBtn(
            onClick = { }
        )
        Spacer(modifier = Modifier.height(20.dp))

    }
}

