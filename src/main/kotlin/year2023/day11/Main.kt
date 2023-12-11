package year2023.day11

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    part1(SAMPLE_1_1).let { println(it) }  // 374
    part1(INPUT).let { println(it) }  // 9274989

    part2(SAMPLE_1_1, 2).let { println(it) }  // 374
    part2(INPUT, 2).let { println(it) }  // 9274989
    part2(SAMPLE_1_1, 10).let { println(it) } // 1030
    part2(SAMPLE_1_1, 100).let { println(it) } // 8410
    part2(INPUT, 1_000_000).let { println(it) } // 357134560737
}

fun part1(input: String): Int {
    // old school: add empty rows and cols to input
    val rows = input.lines().drop(1)
    val rows1 = duplicateEmptyRows(rows)
    val rows2 = duplicateEmptyCols(rows1)

    val galaxies = extractGalaxies(rows2)
    return pairUp(galaxies).sumOf { (g1, g2) -> manhattanDistance(g1, g2) }
}

fun part2(input: String, expansionFactor: Int): Long {
    // a bit smarter: check which rows and cols are empty and pass those to our distance function
    val rows = input.lines().drop(1)
    val rowsWithNoGalaxy = rowsWithNoGalaxy(rows)
    val colsWithNoGalaxy = colsWithNoGalaxy(rows)

    val galaxies = extractGalaxies(rows)
    return pairUp(galaxies).sumOf { (g1, g2) -> manhattanDistance_new(g1, g2, rowsWithNoGalaxy, colsWithNoGalaxy, expansionFactor) }
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

fun rowsWithNoGalaxy(rows: List<String>): List<Int> {
    return rows.withIndex().filter { !it.value.contains('#') }.map { it.index }
}

fun colsWithNoGalaxy(rows: List<String>): List<Int> {
    return (0..rows.first().lastIndex).filter { c ->
        !rows.any { it[c] == '#' }
    }
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

fun manhattanDistance_new(galaxy1: Galaxy, galaxy2: Galaxy, rowsWithNoGalaxy: List<Int>, colsWithNoGalaxy: List<Int>, expansionFactor: Int): Long {
    val (r1, c1) = galaxy1
    val (r2, c2) = galaxy2
    return abs(r1 - r2).toLong() +
            rowsWithNoGalaxy.count { it in min(r1, r2) + 1 until max(r1, r2) } * (expansionFactor - 1) +
            abs(c1 - c2) +
            colsWithNoGalaxy.count { it in min(c1, c2) + 1 until max(c1, c2) } * (expansionFactor - 1)
}

typealias Galaxy = Pair<Int, Int>