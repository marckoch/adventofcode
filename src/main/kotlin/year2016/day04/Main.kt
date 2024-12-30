package year2016.day04

import adventofcode.Problem

fun main() {
    AOC2016D04(SAMPLE).run()
    AOC2016D04(INPUT).run()
}

data class Room(val encryptedName: String, val sectorID: Int, val checksum: String) {
    companion object {
        fun from(s: String): Room {
            val encryptedName = s.substringBeforeLast("-")
            val rest = s.substringAfterLast("-")
            val sectorID = rest.substring(0, rest.indexOf("[")).toInt()
            val checksum = rest.substring(rest.indexOf("[") + 1, rest.indexOf("]"))
            return Room(encryptedName, sectorID, checksum)
        }
    }

    fun isRealRoom(): Boolean {
        val frequencies = encryptedName
            .filter { it != '-' } // remove dash
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedWith(compareByDescending<Pair<Char, Int>>
                { it.second } // Sort by frequency descending
                .thenBy { it.first } // Sort alphabetically in case of a tie
            )

        return checksum == frequencies
            .take(5)
            .map { it.first }
            .joinToString("")
    }

    fun decryptName(): String {
        val shift = sectorID % 26
        return encryptedName.map { c ->
            var x = if (c == '-') ' ' else c + shift
            if (x > 'z') x -= 26
            x
        }.joinToString("")
    }
}

class AOC2016D04(input: String) : Problem(input) {
    private val rooms = inputLines().map { Room.from(it)}

    override fun solve1(): Int {
        return rooms.filter { it.isRealRoom() }.sumOf { it.sectorID }
    }

    override fun solve2(): String {
        return rooms.filter { it.isRealRoom() }
            .firstOrNull { it.decryptName().contains("northpole object storage") }?.sectorID.toString()
    }

}
