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

// https://en.wikipedia.org/wiki/Power_set
fun <T> List<T>.powerSetRecursive(): List<List<T>> {
    if (this.isEmpty()) return listOf(emptyList())
    val first = this.first()
    val restPowerSet = this.drop(1).powerSetRecursive()
    return restPowerSet + restPowerSet.map { it + first }
}

// https://en.wikipedia.org/wiki/Power_set
fun <T> List<T>.powerSetBitwise(): List<List<T>> {
    val powerSet = mutableListOf<List<T>>()
    val size = this.size
    val totalSubsets = 1 shl size // 2^size

    for (i in 0 until totalSubsets) {
        val subset = mutableListOf<T>()
        for (j in this.indices) {
            if (i and (1 shl j) != 0) {
                subset.add(this[j])
            }
        }
        powerSet.add(subset)
    }
    return powerSet
}
