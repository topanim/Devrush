package io.eduonline.devrush.devrushlmsmultiplatform.domain.validation

//interface ValidationState {
//    object OK : ValidationState
//    data class Error(val message: String) : ValidationState
//}
//
//interface Validator<T : Any?> {
//    fun validate(value: T): ValidationState
//}
//
//object EmailValidator : Validator<String> {
//    override fun validate(value: String): ValidationState {
//        return ValidationState.OK
//    }
//}
//
//val registerForm = listOf(EmailField("gorogannisan@gmail.com"))
//
//class EmailField(
//    intiValue: String,
//) : Field<String>(
//    value = intiValue,
//    validator = EmailValidator,
//    required = true
//) {
//    @Composable
//    override fun title(): String {
//        return "Email"
//    }
//}
//
//abstract class Field<T : Any?>(
//    value: T,
//    val validator: Validator<T>?,
//    val required: Boolean,
//) {
//    var value: T by published(value)
//    var valid: ValidationState by published(validator?.validate(value) ?: ValidationState.OK)
//
//    fun push(value: T) {
//        valid = validator?.validate(value) ?: ValidationState.OK
//        this@Field.value = value
//    }
//
//    @Composable
//    abstract fun title(): String
//}
//fun <T : Any?> published(initValue: T) = Published(initValue)
//
//class Published<T : Any?>(initValue: T) : ReadWriteProperty<Any?, T> {
//    private val state: MutableState<T> = mutableStateOf(initValue)
//
//    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
//        return state.value
//    }
//
//    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
//        state.value = value
//    }
//}