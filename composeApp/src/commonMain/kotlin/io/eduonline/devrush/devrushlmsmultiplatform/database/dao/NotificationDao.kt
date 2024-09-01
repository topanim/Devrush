package io.eduonline.devrush.devrushlmsmultiplatform.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.NotificationDBO

@Dao
interface NotificationDao {

    @Insert
    suspend fun insert(notifications: List<NotificationDBO>)

    @Query("DELETE FROM notification")
    suspend fun clean()

    @Query("SELECT * FROM notification")
    suspend fun getAll(): List<NotificationDBO>
}