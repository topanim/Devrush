package io.eduonline.devrush.devrushlmsmultiplatform.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library")
data class LibraryDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("title") val title: String?,
    @ColumnInfo("shortDescription") val shortDescription: String,
    @ColumnInfo("freeAccess") val freeAccess: Boolean,
    @ColumnInfo("primaryImageCloudKey") val primaryImageCloudKey: String?,
    @ColumnInfo("studentHasAccess") val studentHasAccess: Boolean,
    @ColumnInfo("studentAccessDate") val studentAccessDate: String?,
    @ColumnInfo("flowBeginDate") val flowBeginDate: String?,
    @ColumnInfo("categories") val categories: List<String>,
    @ColumnInfo("softDeleted") val softDeleted: Boolean,
    @ColumnInfo("softDeletedDate") val softDeletedDate: String?,
)