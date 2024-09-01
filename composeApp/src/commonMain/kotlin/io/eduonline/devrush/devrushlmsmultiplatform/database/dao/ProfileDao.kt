package io.eduonline.devrush.devrushlmsmultiplatform.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.AchievementDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.LeaderDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.ProfileDBO

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: ProfileDBO)

    @Query("DELETE FROM profile")
    suspend fun clean()

    @Query("SELECT * FROM profile")
    suspend fun getProfiles(): ProfileDBO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeader(leader: List<LeaderDBO>)

    @Query("DELETE FROM leaderboard")
    suspend fun cleanLeader()

    @Query("SELECT * FROM leaderboard")
    suspend fun getLeader(): List<LeaderDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: List<AchievementDBO>)

    @Query("DELETE FROM achievements")
    suspend fun cleanAchievement()

    @Query("SELECT * FROM achievements")
    suspend fun getAchievement(): List<AchievementDBO>
}