package io.eduonline.devrush.devrushlmsmultiplatform.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.eduonline.devrush.devrushlmsmultiplatform.database.dao.CourseDao
import io.eduonline.devrush.devrushlmsmultiplatform.database.dao.LibraryDao
import io.eduonline.devrush.devrushlmsmultiplatform.database.dao.NotificationDao
import io.eduonline.devrush.devrushlmsmultiplatform.database.dao.ProfileDao
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.AchievementDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.CourseDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.LeaderDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.LibraryDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.NotificationDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.ProfileDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.utils.Converters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        CourseDBO::class,
        LibraryDBO::class,
        NotificationDBO::class,
        ProfileDBO::class,
        LeaderDBO::class,
        AchievementDBO::class
    ],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCourseDao(): CourseDao
    abstract fun getLibraryDao(): LibraryDao
    abstract fun getNotificationDao(): NotificationDao
    abstract fun getProfileDao(): ProfileDao
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>,
): AppDatabase {
    return builder
        .fallbackToDestructiveMigration(true)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

public expect fun instantiateAppDatabase(): AppDatabase
