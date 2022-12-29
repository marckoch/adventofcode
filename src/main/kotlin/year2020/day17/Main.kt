package year2020.day17

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    measureTime { part1(SAMPLE) }.let { println(it) }
    measureTime { part1(INPUT) }.let { println(it) }
}

fun part1(input: String) {
    // we maintain only a list of active cubes
    var activeCubes = parse(input)
    print(activeCubes, 0)

    repeat(6) {
        activeCubes = tick(activeCubes)
        print(activeCubes, it + 1)
    }
    println("part1: ${activeCubes.size}")
}

fun tick(activeCubes: Set<Cube>): Set<Cube> {
    // our active cubes and all their neighbors
    val allCubes = activeCubes + activeCubes.flatMap { neighbors(it) }.toSet()

    return allCubes.map { cube ->
        val activeNeighbors = countActiveCubesAround(cube, activeCubes)
        if (cube.active && activeNeighbors !in 2..3) {
            cube.copy(active = false)
        } else if (activeNeighbors == 3)
            cube.copy(active = true)
        else
            cube
    }.filter { it.active }.toSet()
}

fun countActiveCubesAround(cube: Cube, activeCubes: Set<Cube>): Int {
    return neighbors(cube).count { n -> activeCubes.any { cube -> cube.x == n.x && cube.y == n.y && cube.z == n.z } }
}

fun parse(input: String): Set<Cube> {
    return input.lines().withIndex().flatMap { (y, s) ->
        s.withIndex().mapNotNull { (x, c) ->
            if (c == '#')
                Cube(x, y, 0, true)
            else
                null
        }
    }.toSet()
}

data class Cube(val x: Int, val y: Int, val z: Int, var active: Boolean)

fun neighbors(cube: Cube): Set<Cube> {
    return (-1..1).flatMap { dX ->
        (-1..1).flatMap { dY ->
            (-1..1).mapNotNull { dZ ->
                if (dX == 0 && dY == 0 && dZ == 0) // exclude cube itself
                    null
                else
                    Cube(cube.x + dX, cube.y + dY, cube.z + dZ, false)
            }
        }
    }.toSet()
}

fun print(activeCubes: Set<Cube>, cycles: Int) {
    val minX = activeCubes.minOf { it.x }
    val maxX = activeCubes.maxOf { it.x }
    val minY = activeCubes.minOf { it.y }
    val maxY = activeCubes.maxOf { it.y }
    val minZ = activeCubes.minOf { it.z }
    val maxZ = activeCubes.maxOf { it.z }
    // println("x=$minX..$maxX, y=$minY..$maxY, z=$minZ..$maxZ")

    println()
    println("after $cycles cycles:")
    for (z in minZ..maxZ) {
        println()
        println("z=$z")
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                val cube = activeCubes.find { it.x == x && it.y == y && it.z == z }
                if (cube != null)
                    print('#')
                else
                    print('.')
            }
            println()
        }
    }
}