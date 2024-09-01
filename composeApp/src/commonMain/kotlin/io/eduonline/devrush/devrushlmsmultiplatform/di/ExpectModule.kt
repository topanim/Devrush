package io.eduonline.devrush.devrushlmsmultiplatform.di

import io.ktor.client.HttpClient
import org.koin.core.module.Module

expect fun platformModule(): Module

expect suspend fun HttpClient.downloadAndSave(url: String, fileName: String)