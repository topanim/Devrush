package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.common

sealed class FileType(val value: String) {
    data object Image : FileType("image")
    data object Audio : FileType("video")
    data object Video : FileType("audio")
}