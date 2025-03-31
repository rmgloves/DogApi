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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmgloves.dogapi.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_variable_font_wght, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography().copy(
    displayLarge = Typography().displayLarge.copy(fontFamily = Montserrat, fontWeight = FontWeight.Medium),
    displayMedium = Typography().displayMedium.copy(fontFamily = Montserrat),
    displaySmall = Typography().displaySmall.copy(fontFamily = Montserrat),
    headlineLarge = Typography().headlineLarge.copy(fontFamily = Montserrat),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = Montserrat),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = Montserrat),
    titleLarge = Typography().titleLarge.copy(fontFamily = Montserrat),
    titleMedium = Typography().titleMedium.copy(fontFamily = Montserrat),
    titleSmall = Typography().titleSmall.copy(fontFamily = Montserrat),
    bodyLarge = Typography().bodyLarge.copy(fontFamily = Montserrat),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = Montserrat),
    bodySmall = Typography().bodySmall.copy(fontFamily = Montserrat),
    labelLarge = Typography().labelLarge.copy(fontFamily = Montserrat),
    labelMedium = Typography().labelMedium.copy(fontFamily = Montserrat),
    labelSmall = Typography().labelSmall.copy(fontFamily = Montserrat),
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
