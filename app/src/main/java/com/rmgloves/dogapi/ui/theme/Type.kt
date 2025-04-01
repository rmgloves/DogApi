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
import androidx.compose.ui.unit.dp
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.ui.common.ThemedPreview

val OpenSans = FontFamily(
    Font(R.font.open_sans_variable_font, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography().copy(
    displayLarge = Typography().displayLarge.copy(fontFamily = OpenSans),
    displayMedium = Typography().displayMedium.copy(fontFamily = OpenSans),
    displaySmall = Typography().displaySmall.copy(fontFamily = OpenSans),
    headlineLarge = Typography().headlineLarge.copy(fontFamily = OpenSans),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = OpenSans),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = OpenSans),
    titleLarge = Typography().titleLarge.copy(fontFamily = OpenSans),
    titleMedium = Typography().titleMedium.copy(fontFamily = OpenSans),
    titleSmall = Typography().titleSmall.copy(fontFamily = OpenSans),
    bodyLarge = Typography().bodyLarge.copy(fontFamily = OpenSans),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = OpenSans),
    bodySmall = Typography().bodySmall.copy(fontFamily = OpenSans),
    labelLarge = Typography().labelLarge.copy(fontFamily = OpenSans),
    labelMedium = Typography().labelMedium.copy(fontFamily = OpenSans),
    labelSmall = Typography().labelSmall.copy(fontFamily = OpenSans),
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TypographyPreviewLight(typography: Typography = MaterialTheme.typography) {
    ThemedPreview {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Display Large", style = MaterialTheme.typography.displayLarge)
            Text("Display Medium", style = MaterialTheme.typography.displayMedium)
            Text("Display Small", style = MaterialTheme.typography.displaySmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
            Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
            Text("Headline Small", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Title Large", style = MaterialTheme.typography.titleLarge)
            Text("Title Medium", style = MaterialTheme.typography.titleMedium)
            Text("Title Small", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Body Large", style = MaterialTheme.typography.bodyLarge)
            Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
            Text("Body Small", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Label Large", style = MaterialTheme.typography.labelLarge)
            Text("Label Medium", style = MaterialTheme.typography.labelMedium)
            Text("Label Small", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TypographyPreviewDark(typography: Typography = MaterialTheme.typography) {
    ThemedPreview {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Display Large", style = MaterialTheme.typography.displayLarge)
            Text("Display Medium", style = MaterialTheme.typography.displayMedium)
            Text("Display Small", style = MaterialTheme.typography.displaySmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
            Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
            Text("Headline Small", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Title Large", style = MaterialTheme.typography.titleLarge)
            Text("Title Medium", style = MaterialTheme.typography.titleMedium)
            Text("Title Small", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Body Large", style = MaterialTheme.typography.bodyLarge)
            Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
            Text("Body Small", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Label Large", style = MaterialTheme.typography.labelLarge)
            Text("Label Medium", style = MaterialTheme.typography.labelMedium)
            Text("Label Small", style = MaterialTheme.typography.labelSmall)
        }
    }
}
