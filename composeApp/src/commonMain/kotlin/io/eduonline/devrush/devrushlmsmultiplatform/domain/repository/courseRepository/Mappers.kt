package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.courseRepository

import io.eduonline.devrush.devrushlmsmultiplatform.database.models.CourseDBO
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course

internal fun CourseDBO.toCourse() = Course(
    title = title,
    shortDescription = shortDescription,
    primaryImageCloudKey = primaryImageCloudKey,
    freeAccess = freeAccess,
    studentHasAccess = studentHasAccess,
    isCompleteAnyCourseRequirement = isCompleteAnyCourseRequirement,
    studentCourse = studentCourse,
    studentAccessDate = studentAccessDate,
    categories = categories,
    id = id,
    softDeleted = softDeleted,
    softDeletedDate = softDeletedDate
)

internal fun Course.toCourseDbo() = CourseDBO(
    title = title?:"",
    shortDescription = shortDescription,
    primaryImageCloudKey = primaryImageCloudKey,
    freeAccess = freeAccess,
    studentHasAccess = studentHasAccess,
    isCompleteAnyCourseRequirement = isCompleteAnyCourseRequirement,
    studentCourse = studentCourse,
    studentAccessDate = studentAccessDate,
    categories = categories,
    id = id,
    softDeleted = softDeleted,
    softDeletedDate = softDeletedDate
)