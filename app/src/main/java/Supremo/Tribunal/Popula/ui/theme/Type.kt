package Supremo.Tribunal.Popula.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unitSize.sp
import Supremo.Tribunal.Popula.R

val Poppins = FontFamily(
    Font(R.font.poppins_regular)
)
val Roboto = FontFamily(
    Font(R.font.roboto_regular)
)

val Typography = TextStyle(
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = medium,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)