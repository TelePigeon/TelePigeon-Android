package com.dongguk.telepigeon.feature.notification

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentNotificationBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class NotificationFragment : BindingFragment<FragmentNotificationBinding>({ FragmentNotificationBinding.inflate(it) }) {
    private val postNotificationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        navigateToHome()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setBtnNotificationNoApplyClickListener()
        setBtnNotificationApplyClickListener()
    }

    private fun initLayout() {
        if (ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            navigateToHome()
        }
    }

    private fun setBtnNotificationNoApplyClickListener() {
        binding.btnNotificationNoApply.setOnClickListener {
            navigateToHome()
        }
    }

    private fun setBtnNotificationApplyClickListener() {
        binding.btnNotificationApply.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                postNotificationPermissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_notification_to_home)
    }
}