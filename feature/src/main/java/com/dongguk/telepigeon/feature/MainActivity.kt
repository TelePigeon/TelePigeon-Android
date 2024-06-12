package com.dongguk.telepigeon.feature

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dongguk.telepigeon.feature.databinding.ActivityMainBinding
import com.dongguk.telpigeon.core.ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.fcv_main_all_navi) as NavHostFragment).navController
        binding.bnvMainAllNavi.setupWithNavController(navController)

        setBottomNavigationVisibility(navController = navController)
        handleFcmNavigation(navController = navController)
    }

    private fun setBottomNavigationVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnvMainAllNavi.visibility =
                if (destination.id in
                    listOf(
                        R.id.menu_all_navi_main,
                        R.id.menu_all_navi_calender,
                        R.id.menu_all_navi_setting,
                    )
                ) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }
    }

    private fun handleFcmNavigation(navController: NavController) {
        intent.getStringExtra(RELATED_CONTENT_ID)?.let {
            navController.navigate(
                R.id.action_home_to_main,
                bundleOf(ID to it.toInt())
            )
        }
    }

    companion object {
        const val ID = "id"
        const val RELATED_CONTENT_ID = "relateContentId"
    }
}
