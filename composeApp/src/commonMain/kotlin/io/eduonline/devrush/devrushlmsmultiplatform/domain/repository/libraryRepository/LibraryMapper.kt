package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.libraryRepository

import io.eduonline.devrush.devrushlmsmultiplatform.database.models.LibraryDBO
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library

internal fun LibraryDBO.toLibrary() = Library(
    title = title,
    shortDescription = shortDescription,
    freeAccess = freeAccess,
    primaryImageCloudKey = primaryImageCloudKey,
    studentHasAccess = studentHasAccess,
    studentAccessDate = studentAccessDate,
    flowBeginDate = flowBeginDate,
    categories = categories,
    id = id,
    softDeleted = softDeleted,
    softDeletedDate = softDeletedDate
)

internal fun Library.toLibraryDBO() = LibraryDBO(
    title = title,
    shortDescription = shortDescription,
    freeAccess = freeAccess,
    primaryImageCloudKey = primaryImageCloudKey,
    studentHasAccess = studentHasAccess,
    studentAccessDate = studentAccessDate,
    flowBeginDate = flowBeginDate,
    categories = categories,
    id = id,
    softDeleted = softDeleted,
    softDeletedDate = softDeletedDate
)