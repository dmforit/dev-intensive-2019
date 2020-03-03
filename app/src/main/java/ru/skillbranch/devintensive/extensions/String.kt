package ru.skillbranch.devintensive.extensions

fun String.stripHtml(): String {
    return this.replace(Regex("<.*?>"), "").replace(Regex(" +"), " ")
}

fun String.truncate(value: Int = 16): String {
    return if (this.trim().length <= value || value < 0)
        this.trim()
    else
        this.trim().substring(0, value).trim().plus("...")
}