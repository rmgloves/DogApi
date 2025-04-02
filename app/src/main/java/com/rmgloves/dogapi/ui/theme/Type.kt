package com.rmgloves.dogapi.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.ui.common.ThemedPreview

val OpenSans = FontFamily(
    Font(R.font.open_sans_variable_font, FontWeight.Normal),
)

val baseTypography = Typography()
val Typography = Typography().copy(
    displayLarge = baseTypography.displayLarge.copy(fontFamily = OpenSans),
    displayMedium = baseTypography.displayMedium.copy(fontFamily = OpenSans),
    displaySmall = baseTypography.displaySmall.copy(fontFamily = OpenSans),
    headlineLarge = baseTypography.headlineLarge.copy(fontFamily = OpenSans),
    headlineMedium = baseTypography.headlineMedium.copy(fontFamily = OpenSans),
    headlineSmall = baseTypography.headlineSmall.copy(fontFamily = OpenSans),
    titleLarge = baseTypography.titleLarge.copy(fontFamily = OpenSans),
    titleMedium = baseTypography.titleMedium.copy(fontFamily = OpenSans),
    titleSmall = baseTypography.titleSmall.copy(fontFamily = OpenSans),
    bodyLarge = baseTypography.bodyLarge.copy(fontFamily = OpenSans),
    bodyMedium = baseTypography.bodyMedium.copy(fontFamily = OpenSans),
    bodySmall = baseTypography.bodySmall.copy(fontFamily = OpenSans),
    labelLarge = baseTypography.labelLarge.copy(fontFamily = OpenSans),
    labelMedium = baseTypography.labelMedium.copy(fontFamily = OpenSans),
    labelSmall = baseTypography.labelSmall.copy(fontFamily = OpenSans),
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TypographyPreviewLight() {
    ThemedPreview {
        Column(modifier = Modifier.padding(Dimens.SpaceM)) {
            Text("Display Large", style = MaterialTheme.typography.displayLarge)
            Text("Display Medium", style = MaterialTheme.typography.displayMedium)
            Text("Display Small", style = MaterialTheme.typography.displaySmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
            Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
            Text("Headline Small", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Title Large", style = MaterialTheme.typography.titleLarge)
            Text("Title Medium", style = MaterialTheme.typography.titleMedium)
            Text("Title Small", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Body Large", style = MaterialTheme.typography.bodyLarge)
            Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
            Text("Body Small", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Label Large", style = MaterialTheme.typography.labelLarge)
            Text("Label Medium", style = MaterialTheme.typography.labelMedium)
            Text("Label Small", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TypographyPreviewDark() {
    ThemedPreview {
        Column(modifier = Modifier.padding(Dimens.SpaceM)) {
            Text("Display Large", style = MaterialTheme.typography.displayLarge)
            Text("Display Medium", style = MaterialTheme.typography.displayMedium)
            Text("Display Small", style = MaterialTheme.typography.displaySmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
            Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
            Text("Headline Small", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Title Large", style = MaterialTheme.typography.titleLarge)
            Text("Title Medium", style = MaterialTheme.typography.titleMedium)
            Text("Title Small", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Body Large", style = MaterialTheme.typography.bodyLarge)
            Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
            Text("Body Small", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(Dimens.SpaceS))
            Text("Label Large", style = MaterialTheme.typography.labelLarge)
            Text("Label Medium", style = MaterialTheme.typography.labelMedium)
            Text("Label Small", style = MaterialTheme.typography.labelSmall)
        }
    }
}
