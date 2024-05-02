package com.dongguk.telepigeon.design.system.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.dongguk.telepigeon.core.design.system.databinding.AppBarTelepigeonBinding
import com.dongguk.telepigeon.design.system.type.AppBarType

@SuppressLint("CustomViewStyleable")
class TelePigeonAppBar
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : ConstraintLayout(context, attrs, defStyleAttr) {
        var binding: AppBarTelepigeonBinding

        init {
            binding = AppBarTelepigeonBinding.inflate(LayoutInflater.from(context), this, true)
        }

        fun initLayout(
            appBarType: AppBarType,
            title: String = "",
        ) {
            with(binding) {
                when (appBarType) {
                    AppBarType.TITLE -> {
                        ivAppBarTelepigeonX.visibility = View.INVISIBLE
                        tvAppBarTelepigeonTitle.text = title
                    }

                    AppBarType.X -> {
                        ivAppBarTelepigeonArrowLeft.visibility = View.INVISIBLE
                        tvAppBarTelepigeonTitle.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
