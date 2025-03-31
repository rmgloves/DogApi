package com.rmgloves.dogapi.data.model

import androidx.annotation.StringRes
import com.rmgloves.dogapi.R

sealed class ErrorMessage {
    data class Literal(val message: String) : ErrorMessage()
    data class Resource(@StringRes val resId: Int = R.string.error_generic) : ErrorMessage()
}
