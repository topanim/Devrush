package io.eduonline.devrush.devrushlmsmultiplatform.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.poppins_regular
import devrush_multiplatform.composeapp.generated.resources.poppins_semibold
import devrush_multiplatform.composeapp.generated.resources.sf_pro_text_bold
import org.jetbrains.compose.resources.Font


val poppins: FontFamily
    @Composable get() = FontFamily(
        Font(resource = Res.font.poppins_regular, FontWeight.Normal),
        Font(resource = Res.font.poppins_semibold, FontWeight.SemiBold)
    )

val sfPro: FontFamily
    @Composable get() = FontFamily(
        Font(resource = Res.font.sf_pro_text_bold, FontWeight.Bold)
    )
