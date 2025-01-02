package util.strings

fun String.numberOfVowels() = this.count { c -> c in "aeiou" }

fun String.isNumber(): Boolean = this.all { it in '0'..'9' }
