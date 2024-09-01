package io.eduonline.devrush.devrushlmsmultiplatform.utils


fun Long.format() = when {
    this == Long.MIN_VALUE || this < 0 -> "N/A"
    this < 1024L -> "$this B"
    this <= 0xfffccccccccccccL shr 40 -> "${this.div(0x1 shl 10)} KB"
    this <= 0xfffccccccccccccL shr 30 -> "${this.div(0x1 shl 20)} MB"
    this <= 0xfffccccccccccccL shr 20 -> "${this.div(0x1 shl 30)} GB"
    this <= 0xfffccccccccccccL shr 10 -> "${this.div(0x1 shl 40)} TB"
    this <= 0xfffccccccccccccL -> "${(this shr 10).div(0x1 shl 40)} PiB"
    else -> "${(this shr 20).div(0x1 shl 40)} EiB"
}