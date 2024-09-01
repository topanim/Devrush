package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule

class DeleteAccountUseCase(
    private val api: AuthModule,
    private val logoutUseCase: LogoutUseCase,
) {
    suspend operator fun invoke() {

    }
}