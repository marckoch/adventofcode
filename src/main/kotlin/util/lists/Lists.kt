package util.lists

inline fun <T> List<T>.singleOrException(predicate: (T) -> Boolean): T {
    val filtered = this.filter(predicate)
    return when {
        filtered.isEmpty() -> throw NoSuchElementException("No elements match the predicate.")
        filtered.size > 1 -> throw IllegalArgumentException("More than one element matches the predicate.")
        else -> filtered.first()
    }
}
