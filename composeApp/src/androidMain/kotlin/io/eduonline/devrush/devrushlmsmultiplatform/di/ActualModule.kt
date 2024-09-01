package io.eduonline.devrush.devrushlmsmultiplatform.di

import android.os.Environment
import io.eduonline.devrush.devrushlmsmultiplatform.database.getDatabaseBuilder
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository.Auth
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.AppSettingsRepository.AppSettings
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.copyAndClose
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

actual fun platformModule(): Module = module {
    single<HttpClientEngine> { OkHttp.create() }

    single<KStore<Auth>>(named("auth_storage")) {
        val filesDir: String = androidContext().filesDir.path
        return@single storeOf(
            file = "$filesDir/auth_storage.json".toPath(),
            enableCache = true,
            json = get()
        )
    }

    single<KStore<AppSettings>>(named("app_settings")) {
        val filesDir: String = androidContext().filesDir.path
        return@single storeOf(
            file = "$filesDir/app_settings.json".toPath(),
            enableCache = true,
            default = AppSettings(),
            json = get()
        )
    }

    single {
        getDatabaseBuilder(
            androidContext()
        )
    }
}

@OptIn(InternalAPI::class)
actual suspend fun HttpClient.downloadAndSave(url: String, fileName: String) {
    val downloadsDir = Environment
        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file = File(downloadsDir, fileName)

    val response: HttpResponse = get(url)
    response.content.copyAndClose(file.writeChannel())
}