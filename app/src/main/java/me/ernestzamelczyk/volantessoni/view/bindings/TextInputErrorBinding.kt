package me.ernestzamelczyk.volantessoni.view.bindings

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher

object TextInputErrorBinding {

    @JvmStatic
    @BindingAdapter("errorText")
    fun setErrorText(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
    }

    @JvmStatic
    @BindingAdapter("onInput")
    fun setOnInputListener(textInputEditText: TextInputEditText, onInputListener: OnInputListener) {
        textInputEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onInputListener.onInput()
            }
        })
    }

    interface OnInputListener {
        fun onInput()
    }

}