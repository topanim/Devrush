package io.eduonline.devrush.devrushlmsmultiplatform.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.LibraryDBO

@Dao
interface LibraryDao {

    @Insert
    suspend fun insert(library: List<LibraryDBO>)

    @Delete
    suspend fun delete(library: List<LibraryDBO>)

    @Query("DELETE FROM library")
    suspend fun clean()

    @Query("SELECT * FROM library")
    suspend fun getAll(): List<LibraryDBO>
}