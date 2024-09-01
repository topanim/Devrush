package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models

sealed interface AccessesPaymentsAction {
    data class OpenCourse(val id: String, val imageCloudKey: String?) : AccessesPaymentsAction
    data class OpenLibrary(val id: String, val imageCloudKey: String?) : AccessesPaymentsAction
}