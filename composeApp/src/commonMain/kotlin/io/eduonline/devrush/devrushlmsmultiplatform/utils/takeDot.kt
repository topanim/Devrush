package io.eduonline.devrush.devrushlmsmultiplatform.utils

fun String.takeDot(limit:Int) = if(limit < 3 || this.length <= limit) this else this.take(limit-3) + "..."