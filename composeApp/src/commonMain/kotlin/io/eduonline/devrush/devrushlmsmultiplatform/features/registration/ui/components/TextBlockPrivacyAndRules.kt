package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterViewState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBlockPolicyAndRules(
    viewState: RegisterViewState,
    onEvent: (String) -> Unit,
) {
    val rulesText = DevRushTheme.strings.registrationEnterWe
    val policyText = DevRushTheme.strings.registrationPolicy
    var showBottomSheet by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        val text = buildAnnotatedString {
            append(DevRushTheme.strings.registrationEnterTitleRules)
            pushStringAnnotation(tag = "click", annotation = "click")
            withStyle(
                SpanStyle(
                    //textDecoration = TextDecoration.Underline,
                    color = DevRushTheme.colors.blue1
                )
            ) {
                append(DevRushTheme.strings.registrationConfidentiality)
            }
            pop()
            append(DevRushTheme.strings.registrationAnd)
            pushStringAnnotation(tag = "click_2", annotation = "click")
            withStyle(
                SpanStyle(
                    //textDecoration = TextDecoration.Underline,
                    color = DevRushTheme.colors.blue1
                )
            ) {
                append(DevRushTheme.strings.registrationServerRules)
            }
            pop()
        }
        Box(contentAlignment = Alignment.Center) {
            ClickableText(text = text,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = DevRushTheme.colors.c2,
                    fontWeight = FontWeight(700),
                    lineHeight = 14.4.sp
                ),

                //modifier = Modifier.,
                onClick = { offset ->
                    text.getStringAnnotations(tag = "click", start = offset, end = offset)
                        .firstOrNull()
                        ?.let {
                            onEvent(policyText)
                            showBottomSheet = true
                        }
                    text.getStringAnnotations(tag = "click_2", start = offset, end = offset)
                        .firstOrNull()
                        ?.let {
                            onEvent(rulesText)
                            showBottomSheet = true
                        }
                })
        }
    }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            shape = BottomSheetDefaults.HiddenShape,
            containerColor = DevRushTheme.colors.background,


            ) {
            // Sheet content
            Text(
                text = viewState.bottomSheetText,
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                color = DevRushTheme.colors.c1,
                style = DevRushTheme.typography.sfPro14
            )

        }
    }
}