package com.dongguk.telepigeon.feature.calendar.monthlyreport

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.feature.calendar.calendar.CalendarFragment.Companion.DATE
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
            tvMonthlyReportTitle.text = getString(com.dongguk.telepigeon.core.design.system.R.string.monthly_report_title, requireArguments().getString(DATE)?.split("-")?.getOrNull(1)?.toIntOrNull())

            ivMonthlyReportEmpty.visibility = if (monthlyReportViewModel.dummyMonthlyReportModel == null) View.VISIBLE else View.INVISIBLE
            tvMonthlyReportEmpty.visibility = if (monthlyReportViewModel.dummyMonthlyReportModel == null) View.VISIBLE else View.INVISIBLE

            monthlyReportViewModel.dummyMonthlyReportModel?.let { monthlyReportModel ->
                tvMonthlyReportPositiveKeywordRank1.text = monthlyReportModel.positiveKeywords[0]
                tvMonthlyReportPositiveKeywordRank2.text = monthlyReportModel.positiveKeywords[1]
                tvMonthlyReportPositiveKeywordRank3.text = monthlyReportModel.positiveKeywords[2]
                tvMonthlyReportNegativeKeywordRank1.text = monthlyReportModel.negativeKeywords[0]
                tvMonthlyReportNegativeKeywordRank2.text = monthlyReportModel.positiveKeywords[1]
                tvMonthlyReportNegativeKeywordRank3.text = monthlyReportModel.positiveKeywords[2]
            }
        }
    }

    private fun setBtnMonthlyReportCheckClickListener() {
        binding.btnMonthlyReportCheck.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
