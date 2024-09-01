package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule

class GetAccessesPageCountUseCase(
    val api: CourseModule,
    val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(expired: Boolean) {
//        do {
//
//        } while ()
    }
}