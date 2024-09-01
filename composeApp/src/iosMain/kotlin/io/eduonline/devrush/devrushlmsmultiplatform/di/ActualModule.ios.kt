package io.eduonline.devrush.devrushlmsmultiplatform.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.eduonline.devrush.devrushlmsmultiplatform.database.AppDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.database.instantiateAppDatabase
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    factory<RoomDatabase.Builder<AppDatabase>> {
        Room.databaseBuilder<AppDatabase>(
            name = "${NSHomeDirectory()}/devrush.db",
            factory = { instantiateAppDatabase() }
        ).setDriver(BundledSQLiteDriver())
    }
}

actual suspend fun HttpClient.downloadAndSave(url: String, fileName: String) {

}