package year2023.day11

import kotlin.math.abs

fun main() {
    part1(SAMPLE_1_1).let { println(it) }  // 374
    part1(INPUT).let { println(it) }  // 9274989
}

fun part1(input: String): Int {
    val rows = input.lines().drop(1)
    val rows1 = duplicateEmptyRows(rows)
    val rows2 = duplicateEmptyCols(rows1)

    val galaxies = extractGalaxies(rows2)
    return pairUp(galaxies).sumOf { (g1, g2) -> manhattanDistance(g1, g2) }
}

fun duplicateEmptyRows(rows: List<String>): List<String> {
    return rows.fold(mutableListOf()) { acc, line ->
        if (!line.contains('#')) {
            acc.add(line)
        }
        acc.add(line)
        acc
    }
}

fun duplicateEmptyCols(rows: List<String>): List<String> {
    val acc = MutableList(rows.size) { mutableListOf<Char>() }
    for (c in 0 .. rows.first().lastIndex) {
        if (!rows.any { it[c] == '#' }) {
            acc.forEach { it.add('.') }
        }
        for (r in 0 .. rows.lastIndex) {
            acc[r].add(rows[r][c])
        }
    }
    return acc.map { it.joinToString("") }
}

fun extractGalaxies(rows: List<String>): List<Galaxy> {
    return (0..rows.lastIndex).flatMap { r ->
        (0..rows.first().lastIndex).mapNotNull { c ->
            if (rows[r][c] == '#') {
                Pair(r, c)
            } else {
                null
            }
        }
    }
}

fun pairUp(galaxies: List<Galaxy>): List<Pair<Galaxy, Galaxy>> {
    val pairs = mutableListOf<Pair<Galaxy, Galaxy>>()
    for (a in 0..galaxies.lastIndex) {
        for (b in a+1..galaxies.lastIndex) {
            pairs.add(Pair(galaxies[a], galaxies[b]))
        }
    }
    return pairs
}

fun manhattanDistance(galaxy1: Galaxy, galaxy2: Galaxy): Int {
    val (r1, c1) = galaxy1
    val (r2, c2) = galaxy2
    return abs(r1 - r2) + abs(c1 - c2)
}

typealias Galaxy = Pair<Int, Int>