package com.dongguk.telepigeon.feature.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentLoginBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>({ FragmentLoginBinding.inflate(it) }) {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setLayoutLoginKakaoClickListener()
        collectPostLoginState()
    }

    private fun initLayout() {
        if (loginViewModel.getIsLogin()) {
            navigateToNotification()
        }
    }

    private fun setLayoutLoginKakaoClickListener() {
        binding.layoutLoginKakao.setOnClickListener {
            loginViewModel.startKakaoLogin(postLogin = loginViewModel::postLogin)
        }
    }

    private fun collectPostLoginState() {
        loginViewModel.postLoginState.flowWithLifecycle(lifecycle).onEach { postLoginState ->
            when (postLoginState) {
                is UiState.Success -> {
                    navigateToNotification()
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToNotification() {
        findNavController().navigate(R.id.action_login_to_notification)
    }
}
