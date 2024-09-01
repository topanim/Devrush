package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.red_start_settings
import io.eduonline.devrush.devrushlmsmultiplatform.components.PhoneMaskVisualTransformation
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import io.michaelrocks.libphonenumber.kotlin.PhoneNumberUtil
import io.michaelrocks.libphonenumber.kotlin.Phonenumber
import io.michaelrocks.libphonenumber.kotlin.metadata.defaultMetadataLoader
import network.chaintech.cmpcountrycodepicker.model.CountryDetails
import network.chaintech.cmpcountrycodepicker.ui.CountryPickerBasicTextField
import org.jetbrains.compose.resources.vectorResource

@Composable
fun PhoneTextField(
    title: String,
    state: String,
    necessarily: Boolean = false,
    onChange: (String) -> Unit,
) {

    Column(Modifier.fillMaxWidth()) {
        if (necessarily) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.red_start_settings),
                    tint = DevRushTheme.colors.baseRed,
                    contentDescription = null,
                    modifier = Modifier.size(11.dp)
                )
                Text(
                    title,
                    style = DevRushTheme.typography.sfPro14,
                    color = DevRushTheme.colors.c2
                )
            }
        } else {
            Text(
                title,
                style = DevRushTheme.typography.sfPro14,
                color = DevRushTheme.colors.c2
            )
        }

        Gap(8)

        var isError by remember { mutableStateOf(false) }
        var validationState: ValidationState by remember { mutableStateOf(ValidationState.Ok) }
        val validationError = DevRushTheme.strings.profileSettingsPhoneTextFieldValidationError

        val themeIsDark = LocalThemeIsDark.current
        val containerColor = if (themeIsDark) DevRushTheme.colors.c4
            else DevRushTheme.colors.c6

        val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

        val visualTransformation = PhoneMaskVisualTransformation("### ### ## ## ## ## ## ##")

        var fullMobileNumber by remember {
            mutableStateOf(
                "+" + state.filter { it.isDigit() }   //добавить "+" и оставить только цифры
            )
        }

        // find CountryCode by fullMobileNumber
        val phoneNumberUtil = PhoneNumberUtil.createInstance(defaultMetadataLoader())
        val defaultLetterCountryCode = "ru"
        val defaultNumberCountryCode = 7
        var letterCountryCode = defaultLetterCountryCode
        var numberCountryCode = defaultNumberCountryCode
        try {
            val numberProto: Phonenumber.PhoneNumber = phoneNumberUtil.parse(fullMobileNumber, "")
            letterCountryCode = phoneNumberUtil.getRegionCodeForNumber(numberProto).toString()
            numberCountryCode = phoneNumberUtil.getCountryCodeForRegion(letterCountryCode)
            Logger.d("MyLog") { "Номер телефона $fullMobileNumber зарегистрирован в стране: $letterCountryCode" }
            Logger.d("MyLog") { "code $numberCountryCode" }
        } catch (e: Exception) {
            Logger.d("MyLog") { "Не удалось определить страну для номера: $fullMobileNumber" }
        }

        if (numberCountryCode == 0) {       //страна не определена
            letterCountryCode = defaultLetterCountryCode
            numberCountryCode = defaultNumberCountryCode
        }

        val numberCountryCodeLength = numberCountryCode.toString().length
        var cutMobileNumber by remember {mutableStateOf(
            if (fullMobileNumber.length > numberCountryCodeLength)
                fullMobileNumber.substring(numberCountryCodeLength + 1)
            else
                ""
        )}

        val selectedCountryState: MutableState<CountryDetails?> = remember {
            mutableStateOf(null)
        }

        Logger.d("MyLog") { "cutMobileNumber: $cutMobileNumber" }
        Logger.d("MyLog") { "страна: " + selectedCountryState.value.toString() }

        CountryPickerBasicTextField(
            mobileNumber = cutMobileNumber,
            defaultCountryCode = letterCountryCode,
            onMobileNumberChange = { it ->
                cutMobileNumber = it.filter { it.isDigit() }
                fullMobileNumber =
                    (selectedCountryState.value?.countryPhoneNumberCode ?: "") + cutMobileNumber
                validationState =
                    if (validatePhoneNumber(fullMobileNumber)) ValidationState.Ok
                    else ValidationState.Error(validationError)
                if (validationState == ValidationState.Ok) {
                    Logger.d("MyLog") { "валидный номер" }
                    isError = false
                    onChange(fullMobileNumber)
                } else {
                    isError = true
                }
                Logger.d("MyLog") { "isError: $isError" }
            },
            onCountrySelected = {
                selectedCountryState.value = it
                fullMobileNumber =
                    (selectedCountryState.value?.countryPhoneNumberCode ?: "") + cutMobileNumber
                validationState =
                    if (validatePhoneNumber(fullMobileNumber)) ValidationState.Ok
                    else ValidationState.Error(validationError)
                if (validationState == ValidationState.Ok) {
                    Logger.d("MyLog") { "валидный номер" }
                    isError = false
                    onChange(fullMobileNumber)
                } else {
                    isError = true
                }
                Logger.d("MyLog") { "isError: $isError" }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            textStyle = DevRushTheme.typography.sfPro14.copy(color = DevRushTheme.colors.c1),
            countryPhoneCodeTextStyle = DevRushTheme.typography.sfPro14.copy(color = DevRushTheme.colors.c1),
            defaultPaddingValues = PaddingValues(0.dp),
            showCountryFlag = true,
            showCountryPhoneCode = true,
            showCountryName = false,
            showCountryCode = false,
            showArrowDropDown = true,
            arrowDropDownColor = DevRushTheme.colors.c2,
            spaceAfterCountryFlag = 0.dp,
            spaceAfterCountryPhoneCode = 0.dp,
            spaceAfterCountryName = 0.dp,
            spaceAfterCountryCode = 0.dp,
            spaceAfterVerticalDivider = 0.dp,
            countryFlagSize = 20.dp,
            showVerticalDivider = false,
            verticalDividerColor = DevRushTheme.colors.c5,
            verticalDividerHeight = 10.dp,
            shape = RoundedCornerShape(12.dp),
            focusedBorderThickness = 1.dp,
            unfocusedBorderThickness = 1.dp,
            enabled = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = DevRushTheme.colors.c1,
                unfocusedTextColor = DevRushTheme.colors.c1,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedBorderColor = DevRushTheme.colors.basePink1,
                unfocusedBorderColor = DevRushTheme.colors.c5,
                errorBorderColor = DevRushTheme.colors.baseRed,
                errorContainerColor = DevRushTheme.colors.c6,
            ),
            interactionSource = interactionSource,
            isError = isError,
            visualTransformation =  visualTransformation,
            placeholder = {
                if (cutMobileNumber.isBlank())
                    Text(
                        "999 999 99 99",
                        color = DevRushTheme.colors.c2,
                        style = DevRushTheme.typography.sfPro14
                    )
            },
        )

        Gap(4)
        if (validationState != ValidationState.Ok) {
            Text(
                (validationState as ValidationState.Error).massage ?: "",
                color = DevRushTheme.colors.baseRed,
                style = DevRushTheme.typography.sfPro12
            )
        }
    }
}

fun validatePhoneNumber(phoneNumber: String): Boolean {
    val phoneNumberUtil = PhoneNumberUtil.createInstance(defaultMetadataLoader())
    try {
        val numberProto: Phonenumber.PhoneNumber = phoneNumberUtil.parse(phoneNumber, "")
        return phoneNumberUtil.isValidNumber(numberProto)
    } catch (e: Exception) {
        Logger.d("MyLog") { "Не удалась валидация" }
    }
    return false
}