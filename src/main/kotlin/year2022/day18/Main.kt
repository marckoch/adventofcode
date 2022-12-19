package year2022.day18

import kotlin.math.abs

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    val cubes = parse(input)
    val n = cubes.count()
    println("cubes $n have surface of ${6 * n}")

    val sharedSides = cubes.sumOf { c -> sharedNeighbors(c, cubes).count() }
    println("sharedSides $sharedSides")

    val r = 6 * n - sharedSides

    println("part1: $r")
}

fun part2(input: String) {
    val cubes = parse(input)

    // find point outside
    val minX = cubes.minOf { it.x }
    val maxX = cubes.maxOf { it.x }
    val minY = cubes.minOf { it.y }
    val maxY = cubes.maxOf { it.y }
    val minZ = cubes.minOf { it.z }
    val maxZ = cubes.maxOf { it.z }
    // println("x $minX..$maxX, y $minY..$maxY, z $minZ..$maxZ")

    // find all neighbors from this starting point, that are
    // outside of the given cubes
    val start = Cube(minX - 1, minY - 1, minZ - 1)

    val outsideCubes = mutableSetOf<Cube>()
    val queue = ArrayDeque<Cube>()
    queue.addLast(start)

    while (queue.isNotEmpty()) {
        val cube = queue.removeFirst()
        neighbors(cube)
            .filter {
                it.x in minX-1..maxX+1 &&
                it.y in minY-1..maxY+1 &&
                it.z in minZ-1..maxZ+1 }
            .filter { it !in outsideCubes }
            .filter { it !in cubes }
            .forEach {
                outsideCubes.add(it)
                queue.add(it)
            }
    }

    // now every free side is a side between one of the cubes and one of the outside cubes

    val r = cubes.sumOf { c -> neighbors(c).count { n -> n in outsideCubes } }
    println("part2: $r")
}

fun neighbors(cube: Cube): List<Cube> {
    return listOf(
        cube.copy(x = cube.x + 1),
        cube.copy(x = cube.x - 1),
        cube.copy(y = cube.y + 1),
        cube.copy(y = cube.y - 1),
        cube.copy(z = cube.z + 1),
        cube.copy(z = cube.z - 1)
    )
}

fun parse(input: String): List<Cube> {
    return input.lines()
        .map { s ->
            s.split(",")
                .let { Cube(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        }
}

fun sharedNeighbors(cube: Cube, cubes: List<Cube>): List<Cube> {
    return cubes.filter { c -> shareSide(c, cube) }
}

fun shareSide(cube1: Cube, cube2: Cube): Boolean {
    return shareSideX(cube1, cube2) || shareSideY(cube1, cube2) || shareSideZ(cube1, cube2)
}

fun shareSideX(cube1: Cube, cube2: Cube): Boolean {
    return (abs(cube1.x - cube2.x) == 1 &&
            abs(cube1.y - cube2.y) == 0 &&
            abs(cube1.z - cube2.z) == 0)
}

fun shareSideY(cube1: Cube, cube2: Cube): Boolean {
    return (abs(cube1.x - cube2.x) == 0 &&
            abs(cube1.y - cube2.y) == 1 &&
            abs(cube1.z - cube2.z) == 0)
}

fun shareSideZ(cube1: Cube, cube2: Cube): Boolean {
    return (abs(cube1.x - cube2.x) == 0 &&
            abs(cube1.y - cube2.y) == 0 &&
            abs(cube1.z - cube2.z) == 1)
}

data class Cube(val x: Int, val y: Int, val z: Int)