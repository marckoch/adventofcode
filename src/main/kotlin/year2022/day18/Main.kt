package year2022.day18

import kotlin.math.abs

fun main() {
    part1(SAMPLE)
    part1(INPUT)
}

fun part1(input: String) {
    val cubes = input.lines()
        .map { s -> s.split(",")
            .let { Cube(it[0].toInt(), it[1].toInt(), it[2].toInt()) } }
    val n = cubes.count()
    println("cubes $n have surface of ${6 * n}")

    val sharedSides = cubes.sumOf { c -> neighbors(c, cubes).count() }
    println("sharedSides $sharedSides")

    val r = 6 * n - sharedSides

    println("part1: $r")
}

fun neighbors(cube: Cube, cubes: List<Cube>): List<Cube> {
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