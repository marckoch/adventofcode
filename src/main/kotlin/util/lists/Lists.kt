package util.lists

import java.util.*
import kotlin.NoSuchElementException

inline fun <T> List<T>.singleOrException(predicate: (T) -> Boolean): T {
    val filtered = this.filter(predicate)
    return when {
        filtered.isEmpty() -> throw NoSuchElementException("No elements match the predicate.")
        filtered.size > 1 -> throw IllegalArgumentException("More than one element matches the predicate.")
        else -> filtered.first()
    }
}

fun <T, U> List<T>.cartesianProduct(other: List<U>): List<Pair<T, U>> {
    return this.flatMap { t -> other.map { u -> t to u } }
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

// https://rosettacode.org/wiki/Combinations#Kotlin
// very general solution: build all combinations of size m out of elements of arr
// NO REPETITION!
inline fun <reified T> combinations(arr: List<T>, m: Int) = sequence {
    val n = arr.size
    val result = Array(m) { arr[0] }
    val stack = LinkedList<Int>()
    stack.push(0)
    while (stack.isNotEmpty()) {
        var resIndex = stack.size - 1;
        var arrIndex = stack.pop()

        while (arrIndex < n) {
            result[resIndex++] = arr[arrIndex++]
            stack.push(arrIndex)

            if (resIndex == m) {
                yield(result.toList())
                break
            }
        }
    }
}


fun <T> Iterable<T>.combinations(k: Int): Sequence<List<T>> =
    sequence {
        val pool = this@combinations as? List<T> ?: toList()
        val n = pool.size
        if (k > n) return@sequence
        val indices = IntArray(k) { it }
        while (true) {
            yield(indices.map { pool[it] })
            var i = k
            do {
                if (i-- == 0) return@sequence
            } while (indices[i] == i + n - k)
            indices[i]++
            for (j in i + 1 until k) indices[j] = indices[j - 1] + 1
        }
    }

// https://rosettacode.org/wiki/Power_set#Kotlin
fun <T> Set<T>.subsets(): Sequence<Set<T>> = sequence {
    when (size) {
        0 -> yield(emptySet<T>())
        else -> {
            val head = first()
            val tail = this@subsets - head
            yieldAll(tail.subsets())
            for (subset in tail.subsets()) {
                yield(setOf(head) + subset)
            }
        }
    }
}

// rotate all members left by 'shift', wrap around
// rotate(listOf(1,2,3,4), 1) -> 2,3,4,1
fun <T> List<T>.rotateLeft(shift: Int): List<T> {
    if (isEmpty()) return this
    val effectiveShift = shift % size
    val normalizedShift = if (effectiveShift < 0) effectiveShift + size else effectiveShift
    return drop(normalizedShift) + take(normalizedShift)
}

// rotate all members left by 'shift', wrap around
// rotate(listOf(1,2,3,4), 1) -> 2,3,4,1
fun <T> List<T>.rotateRight(shift: Int): List<T> = rotateLeft(-shift)

fun <T> List<T>.areAllEqual() = this.distinct().size == 1
