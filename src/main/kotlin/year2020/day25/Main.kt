package year2020.day25

const val INPUT = """13233401
6552760"""

fun main() {
//    val pubKeyCard = transform(7, 8)
//    pubKeyCard.let { println(it) }
//    val pubKeyDoor = transform(7, 11)
//    pubKeyDoor.let { println(it) }

    val publicKeys = INPUT.lines().map { it.toLong() }
    val loopSizesFound = mutableListOf<Int>()

    var loopSize = 1
    var value = 1L
    while (loopSizesFound.size < 2) {
        value *= 7
        value %= 20201227
        if (value in publicKeys) {
            loopSizesFound.add(loopSize)
        }
        loopSize++
    }
    loopSizesFound.let { println(it) }
    transform(transform(7, loopSizesFound[0]), loopSizesFound[1]).let { println(it) }
}

fun transform(subject: Long, loopSize: Int): Long {
    var value = 1L
    repeat (loopSize) {
        value *= subject
        value %= 20201227
    }
    return value
}