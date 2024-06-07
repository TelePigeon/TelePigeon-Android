package com.dongguk.telepigeon.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.usecase.GetIsLoginUseCase
import com.dongguk.telepigeon.domain.usecase.PostLoginUseCase
import com.dongguk.telepigeon.domain.usecase.SetAccessTokenUseCase
import com.dongguk.telepigeon.domain.usecase.SetIsLoginUseCase
import com.dongguk.telepigeon.domain.usecase.SetRefreshTokenUseCase
import com.dongguk.telepigeon.domain.usecase.StartKakaoLoginUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getIsLoginUseCase: GetIsLoginUseCase,
    private val setIsLoginUseCase: SetIsLoginUseCase,
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
    private val setRefreshTokenUseCase: SetRefreshTokenUseCase,
    private val postLoginUseCase: PostLoginUseCase,
    private val startKakaoLoginUseCase: StartKakaoLoginUseCase
) : ViewModel() {
    private val _postLoginState = MutableSharedFlow<UiState<Unit>>()
    val postLoginState get() = _postLoginState.asSharedFlow()

    fun getIsLogin() = getIsLoginUseCase()

    fun postLogin(authorization: String) {
        viewModelScope.launch {
            _postLoginState.emit(UiState.Loading)
            postLoginUseCase(authorization = authorization).onSuccess { authTokenModel ->
                setIsLoginUseCase(isLogin = true)
                setAccessTokenUseCase(accessToken = HEADER_BEARER + authTokenModel.accessToken)
                setRefreshTokenUseCase(refreshToken = HEADER_BEARER + authTokenModel.refreshToken)
                _postLoginState.emit(UiState.Success(Unit))
            }.onFailure { exception: Throwable ->
                _postLoginState.emit(UiState.Error(exception.message))
            }
        }
    }

    fun startKakaoLogin(postLogin: (String) -> Unit) {
        startKakaoLoginUseCase(postLogin = postLogin)
    }

    companion object {
        const val HEADER_BEARER = "Bearer "
    }
}