package com.rmgloves.dogapi.ui.theme

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

@Preview(showBackground = true)
@Composable
fun TypographyPreview(typography: Typography = MaterialTheme.typography) {
    DogApiTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Display Large", style = typography.displayLarge)
            Text("Display Medium", style = typography.displayMedium)
            Text("Display Small", style = typography.displaySmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Headline Large", style = typography.headlineLarge)
            Text("Headline Medium", style = typography.headlineMedium)
            Text("Headline Small", style = typography.headlineSmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Title Large", style = typography.titleLarge)
            Text("Title Medium", style = typography.titleMedium)
            Text("Title Small", style = typography.titleSmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Body Large", style = typography.bodyLarge)
            Text("Body Medium", style = typography.bodyMedium)
            Text("Body Small", style = typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Label Large", style = typography.labelLarge)
            Text("Label Medium", style = typography.labelMedium)
            Text("Label Small", style = typography.labelSmall)
        }
    }
}
