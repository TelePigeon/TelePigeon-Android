package com.dongguk.telepigeon.feature.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentLoginBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class LoginFragment : BindingFragment<FragmentLoginBinding>({ FragmentLoginBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setLayoutLoginKakaoClickListener()
    }

    private fun setLayoutLoginKakaoClickListener() {
        binding.layoutLoginKakao.setOnClickListener {
            navigateToNotification()
        }
    }

    private fun navigateToNotification() {
        findNavController().navigate(R.id.action_login_to_notification)
    }
}
