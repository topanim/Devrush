package io.eduonline.devrush.devrushlmsmultiplatform.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.CourseDBO

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courses: List<CourseDBO>)

    @Delete
    suspend fun delete(courses: List<CourseDBO>)

    @Query("DELETE FROM courses")
    suspend fun clean()

    @Query("SELECT * FROM courses")
    suspend fun getAll(): List<CourseDBO>
}