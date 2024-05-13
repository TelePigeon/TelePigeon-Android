package com.dongguk.telepigeon.design.system.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.core.design.system.databinding.EditTextQnaTelepigeonBinding
import com.dongguk.telepigeon.design.system.type.TelePigeonQnaEditTextType

@SuppressLint("CustomViewStyleable")
class TelePigeonQnaEditText
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : ConstraintLayout(context, attrs, defStyleAttr) {
        private val binding: EditTextQnaTelepigeonBinding
        val editText get() = binding.etEditTextQnaTelepigeon

        init {
            binding = EditTextQnaTelepigeonBinding.inflate(LayoutInflater.from(context), this, true)
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TelePigeonQnaEditText)
            try {
                initView(typedArray)
            } finally {
                typedArray.recycle()
            }
        }

        private fun initView(typedArray: TypedArray) {
            typedArray.apply {
                with(binding) {
                    etEditTextQnaTelepigeon.hint = getString(R.styleable.TelePigeonEditText_telePigeonEditTextHint)
                }
            }
        }

        fun initLayout(
            qnaEditTextType: TelePigeonQnaEditTextType,
        ) {
            binding.ivEditTextQnaTelepigeon.setImageResource(qnaEditTextType.icon)
        }
    }
