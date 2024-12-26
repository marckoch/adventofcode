package util.lists

inline fun <T> List<T>.singleOrException(predicate: (T) -> Boolean): T {
    val filtered = this.filter(predicate)
    return when {
        filtered.isEmpty() -> throw NoSuchElementException("No elements match the predicate.")
        filtered.size > 1 -> throw IllegalArgumentException("More than one element matches the predicate.")
        else -> filtered.first()
    }
}

// https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> List<T>.permute(): List<List<T>> {
    if (this.size == 1) return listOf(this)

    val perms = mutableListOf<List<T>>()
    val head = this.first()
    val tail = this.drop(1)
    for (perm in tail.permute()) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, head)
            perms.add(newPerm)
        }
    }
    return perms
}

// get index of 'e' in 'list'.
// if 'e' is not in 'list', insert 'e' first at end of 'list'
fun <T> MutableList<T>.indexOfOrPut(e: T): Int {
    if (!this.contains(e)) {
        this.add(e)
    }
    return this.indexOf(e)
}