package util.strings

fun String.numberOfVowels() = this.count { c -> c in "aeiou" }
