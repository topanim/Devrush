package io.eduonline.devrush.devrushlmsmultiplatform.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.StudentCourse

@Entity(tableName = "courses")
data class CourseDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("shortDescription") val shortDescription: String,
    @ColumnInfo("primaryImageCloudKey") val primaryImageCloudKey: String?,
    @ColumnInfo("freeAccess") val freeAccess: Boolean,
    @ColumnInfo("studentHasAccess") val studentHasAccess: Boolean,
    @ColumnInfo("isCompleteAnyCourseRequirement") val isCompleteAnyCourseRequirement: Boolean,
    @ColumnInfo("studentCourse") val studentCourse: StudentCourse,
    @ColumnInfo("studentAccessDate") val studentAccessDate: String?,
    @ColumnInfo("categories") val categories: List<String>,
    @ColumnInfo("softDeleted") val softDeleted: Boolean,
    @ColumnInfo("softDeletedDate") val softDeletedDate: String?,
)