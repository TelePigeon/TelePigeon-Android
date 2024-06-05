package com.dongguk.telepigeon.design.system.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.core.design.system.databinding.EditTextTelepigeonBinding

@SuppressLint("CustomViewStyleable")
class TelePigeonEditText
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
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
                getInt(R.styleable.TelePigeonEditText_telePigeonTextMaxLength, -1).let { maxLength ->
                    if (maxLength > INITIAL_LENGTH) {
                        binding.etEditTextTelepigeonText.filters = arrayOf(InputFilter.LengthFilter(maxLength))
                    }
                }
            }
        }
    }

    fun setOnTextChangedListener(listener: (String) -> Unit) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listener(s.toString())
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    companion object {
        const val INITIAL_LENGTH = 0
    }
}
