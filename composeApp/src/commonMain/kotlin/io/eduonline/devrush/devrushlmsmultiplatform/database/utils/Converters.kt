package io.eduonline.devrush.devrushlmsmultiplatform.database.utils

import androidx.room.TypeConverter
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Avatar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.ChatChannelIntegration
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.ChatUserData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Country
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Gamification
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Group
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Partnership
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.School
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.SchoolOwner
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Session
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Tag
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Timezone
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.CurrentCourseItem
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.StudentCourse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Student
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class Converters {
    @TypeConverter
    fun fromStudent(value: Student?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStudent(value: String): Student? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromStudentCourse(value: StudentCourse?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStudentCourse(value: String): StudentCourse? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromListString(value: List<String>?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListString(value: String): List<String>? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromCurrentCourseItem(value: CurrentCourseItem?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toCurrentCourseItem(value: String): CurrentCourseItem? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromSchool(value: School?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toSchool(value: String): School? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromAvatar(value: Avatar?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toAvatar(value: String): Avatar? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromSession(value: Session?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toSession(value: String): Session? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromChatUserData(value: ChatUserData?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toChatUserData(value: String): ChatUserData? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromListTag(value: List<Tag>?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListTag(value: String): List<Tag>? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromListGroup(value: List<Group>?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListGroup(value: String): List<Group>? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromSchoolOwner(value: SchoolOwner?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toSchoolOwner(value: String): SchoolOwner? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromTimezone(value: Timezone?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toTimezone(value: String): Timezone? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromPartnership(value: Partnership?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toPartnership(value: String): Partnership? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromGamification(value: Gamification?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toGamification(value: String): Gamification? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromCountry(value: Country?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toCountry(value: String): Country? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromChatChannelIntegration(value: List<ChatChannelIntegration>?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toChatChannelIntegration(value: String): List<ChatChannelIntegration>? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromListAchievement(value: List<Achievement>?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListAchievement(value: String): List<Achievement>? {
        return Json.decodeFromString(value)
    }
}
