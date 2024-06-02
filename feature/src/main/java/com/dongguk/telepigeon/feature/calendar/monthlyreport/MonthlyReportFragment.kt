package com.dongguk.telepigeon.feature.calendar.monthlyreport

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.feature.databinding.FragmentMonthlyReportBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class MonthlyReportFragment : BindingFragment<FragmentMonthlyReportBinding>({ FragmentMonthlyReportBinding.inflate(it) }) {
    private val monthlyReportViewModel by viewModels<MonthlyReportViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        initLayout()
        setBtnMonthlyReportCheckClickListener()
    }

    private fun initAppBar() {
        binding.appbarMonthlyReport.initLayout(appBarType = AppBarType.X)
        binding.appbarMonthlyReport.binding.ivAppBarTelepigeonX.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initLayout() {
        with(binding) {
            layoutMonthlyReportNegativeKeyword.visibility = View.INVISIBLE
            layoutMonthlyReportPositiveKeyword.visibility = View.INVISIBLE
            tvMonthlyReportPositiveKeyword.visibility = View.INVISIBLE
            tvMonthlyReportNegativeKeyword.visibility = View.INVISIBLE
        }
    }

    private fun setBtnMonthlyReportCheckClickListener() {
        binding.btnMonthlyReportCheck.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
