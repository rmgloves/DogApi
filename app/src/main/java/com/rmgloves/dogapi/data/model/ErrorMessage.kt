package com.rmgloves.dogapi.data.model

import androidx.annotation.StringRes
import com.rmgloves.dogapi.R
import java.util.UUID

sealed class ErrorMessage(open val key: UUID) {
    data class Literal(val message: String, override val key: UUID = UUID.randomUUID()) :
        ErrorMessage(key)

    data class Resource(
        @StringRes val resId: Int = R.string.error_generic,
        override val key: UUID = UUID.randomUUID()
    ) : ErrorMessage(key)
}
