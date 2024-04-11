package com.dongguk.telepigeon.design.system.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.core.design.system.databinding.EditTextTelepigeonBinding

@SuppressLint("CustomViewStyleable")
class TelePigeonEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: EditTextTelepigeonBinding
    val editText get() = binding.etEditTextTelepigeonText

    init {
        binding = EditTextTelepigeonBinding.inflate(LayoutInflater.from(context), this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TelePigeonEditText)
        try {
            initView(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initView(typedArray: TypedArray) {
        typedArray.apply {
            with(binding) {
                tvEditTextTelepigeonTitle.text = getString(R.styleable.TelePigeonEditText_telePigeonEditTextTitle)
                etEditTextTelepigeonText.hint = getString(R.styleable.TelePigeonEditText_telePigeonEditTextHint)
            }
        }
    }
}
