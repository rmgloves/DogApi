package com.rmgloves.dogapi.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmgloves.dogapi.ui.common.ThemedPreview

val primaryLight = Color(0xFF3F6837)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFBFF0B1)
val onPrimaryContainerLight = Color(0xFF275021)
val secondaryLight = Color(0xFF53634E)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFD7E8CD)
val onSecondaryContainerLight = Color(0xFF3C4B37)
val tertiaryLight = Color(0xFF386569)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFBCEBEF)
val onTertiaryContainerLight = Color(0xFF1E4D51)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF93000A)
val backgroundLight = Color(0xFFF8FBF1)
val onBackgroundLight = Color(0xFF191D17)
val surfaceLight = Color(0xFFF8FBF1)
val onSurfaceLight = Color(0xFF191D17)
val surfaceVariantLight = Color(0xFFDFE4D8)
val onSurfaceVariantLight = Color(0xFF43493F)
val outlineLight = Color(0xFF73796E)
val outlineVariantLight = Color(0xFFC2C8BC)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF2E322B)
val inverseOnSurfaceLight = Color(0xFFEFF2E8)
val inversePrimaryLight = Color(0xFFA4D397)
val surfaceDimLight = Color(0xFFD8DBD2)
val surfaceBrightLight = Color(0xFFF8FBF1)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFF2F5EB)
val surfaceContainerLight = Color(0xFFECEFE5)
val surfaceContainerHighLight = Color(0xFFE6E9E0)
val surfaceContainerHighestLight = Color(0xFFE1E4DA)

val primaryDark = Color(0xFFA4D397)
val onPrimaryDark = Color(0xFF10380C)
val primaryContainerDark = Color(0xFF275021)
val onPrimaryContainerDark = Color(0xFFBFF0B1)
val secondaryDark = Color(0xFFBBCBB2)
val onSecondaryDark = Color(0xFF263422)
val secondaryContainerDark = Color(0xFF3C4B37)
val onSecondaryContainerDark = Color(0xFFD7E8CD)
val tertiaryDark = Color(0xFFA0CFD2)
val onTertiaryDark = Color(0xFF00373A)
val tertiaryContainerDark = Color(0xFF1E4D51)
val onTertiaryContainerDark = Color(0xFFBCEBEF)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF11140F)
val onBackgroundDark = Color(0xFFE1E4DA)
val surfaceDark = Color(0xFF11140F)
val onSurfaceDark = Color(0xFFE1E4DA)
val surfaceVariantDark = Color(0xFF43493F)
val onSurfaceVariantDark = Color(0xFFC2C8BC)
val outlineDark = Color(0xFF8D9387)
val outlineVariantDark = Color(0xFF43493F)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFE1E4DA)
val inverseOnSurfaceDark = Color(0xFF2E322B)
val inversePrimaryDark = Color(0xFF3F6837)
val surfaceDimDark = Color(0xFF11140F)
val surfaceBrightDark = Color(0xFF363A34)
val surfaceContainerLowestDark = Color(0xFF0B0F0A)
val surfaceContainerLowDark = Color(0xFF191D17)
val surfaceContainerDark = Color(0xFF1D211B)
val surfaceContainerHighDark = Color(0xFF272B25)
val surfaceContainerHighestDark = Color(0xFF32362F)

@Composable
fun ColorSchemePreview() {
    val colors = listOf(
        "primary" to MaterialTheme.colorScheme.primary,
        "onPrimary" to MaterialTheme.colorScheme.onPrimary,
        "primaryContainer" to MaterialTheme.colorScheme.primaryContainer,
        "onPrimaryContainer" to MaterialTheme.colorScheme.onPrimaryContainer,

        "secondary" to MaterialTheme.colorScheme.secondary,
        "onSecondary" to MaterialTheme.colorScheme.onSecondary,
        "secondaryContainer" to MaterialTheme.colorScheme.secondaryContainer,
        "onSecondaryContainer" to MaterialTheme.colorScheme.onSecondaryContainer,

        "tertiary" to MaterialTheme.colorScheme.tertiary,
        "onTertiary" to MaterialTheme.colorScheme.onTertiary,
        "tertiaryContainer" to MaterialTheme.colorScheme.tertiaryContainer,
        "onTertiaryContainer" to MaterialTheme.colorScheme.onTertiaryContainer,

        "error" to MaterialTheme.colorScheme.error,
        "onError" to MaterialTheme.colorScheme.onError,
        "errorContainer" to MaterialTheme.colorScheme.errorContainer,
        "onErrorContainer" to MaterialTheme.colorScheme.onErrorContainer,

        "background" to MaterialTheme.colorScheme.background,
        "onBackground" to MaterialTheme.colorScheme.onBackground,
        "surface" to MaterialTheme.colorScheme.surface,
        "onSurface" to MaterialTheme.colorScheme.onSurface,

        "surfaceVariant" to MaterialTheme.colorScheme.surfaceVariant,
        "onSurfaceVariant" to MaterialTheme.colorScheme.onSurfaceVariant,
        "outline" to MaterialTheme.colorScheme.outline
    )

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        colors.forEach { (label, color) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .padding(vertical = 2.dp)
                    .background(color, shape = RoundedCornerShape(2.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (color.luminance() < 0.5f) Color.White else Color.Black
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ColorSchemePreviewLight() {
    ThemedPreview {
        ColorSchemePreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ColorSchemePreviewDark() {
    ThemedPreview {
        ColorSchemePreview()
    }
}
