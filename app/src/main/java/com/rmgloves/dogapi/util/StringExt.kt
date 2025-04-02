package com.rmgloves.dogapi.util

import java.util.Locale

fun String.capitalize(locale: Locale = Locale.getDefault()) =
    replaceFirstChar { it.titlecase(locale) }