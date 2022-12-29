package year2020.day17

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

// 3d and 4d game of life, conway
@OptIn(ExperimentalTime::class)
fun main() {
    measureTime { Part1().solve(SAMPLE) }.let { println(it) }
    measureTime { Part1().solve(INPUT) }.let { println(it) }

    measureTime { Part2().solve(SAMPLE) }.let { println(it) }
    measureTime { Part2().solve(INPUT) }.let { println(it) }
}

data class Cube(val x: Int, val y: Int, val z: Int, val w: Int = 0, val active: Boolean = false)

abstract class Solver {
    fun solve(input: String) {
        // we maintain only a list of active cubes
        var activeCubes = parse(input)
        // print(activeCubes, 0)

        repeat(6) {
            activeCubes = tick(activeCubes)
            // print(activeCubes, it + 1)
        }
        println("active cubes: ${activeCubes.size}")
    }

    private fun tick(activeCubes: Set<Cube>): Set<Cube> {
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

    abstract fun countActiveCubesAround(cube: Cube, activeCubes: Set<Cube>): Int

    fun parse(input: String): Set<Cube> {
        return input.lines().withIndex().flatMap { (y, s) ->
            s.withIndex().mapNotNull { (x, c) ->
                if (c == '#')
                    Cube(x, y, 0, active = true)
                else
                    null
            }
        }.toSet()
    }

    abstract fun neighbors(cube: Cube): Set<Cube>

    abstract fun print(activeCubes: Set<Cube>, cycles: Int)
}

class Part1 : Solver() {
    override fun neighbors(cube: Cube): Set<Cube> {
        return (-1..1).flatMap { dX ->
            (-1..1).flatMap { dY ->
                (-1..1).mapNotNull { dZ ->
                    if (dX == 0 && dY == 0 && dZ == 0) // exclude cube itself
                        null
                    else
                        Cube(cube.x + dX, cube.y + dY, cube.z + dZ, active = false)
                }
            }
        }.toSet()
    }

    override fun countActiveCubesAround(cube: Cube, activeCubes: Set<Cube>): Int {
        return neighbors(cube).count { n -> activeCubes.any { cube -> cube.x == n.x && cube.y == n.y && cube.z == n.z } }
    }

    override fun print(activeCubes: Set<Cube>, cycles: Int) {
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
}

class Part2 : Solver() {
    override fun neighbors(cube: Cube): Set<Cube> {
        return (-1..1).flatMap { dX ->
            (-1..1).flatMap { dY ->
                (-1..1).flatMap { dZ ->
                    (-1..1).mapNotNull { dW ->
                        if (dX == 0 && dY == 0 && dZ == 0 && dW == 0) // exclude cube itself
                            null
                        else
                            Cube(cube.x + dX, cube.y + dY, cube.z + dZ, cube.w + dW, false)
                    }
                }
            }
        }.toSet()
    }

    override fun countActiveCubesAround(cube: Cube, activeCubes: Set<Cube>): Int {
        return neighbors(cube).count { n -> activeCubes.any { cube -> cube.x == n.x && cube.y == n.y && cube.z == n.z && cube.w == n.w } }
    }

    override fun print(activeCubes: Set<Cube>, cycles: Int) {
        val minX = activeCubes.minOf { it.x }
        val maxX = activeCubes.maxOf { it.x }
        val minY = activeCubes.minOf { it.y }
        val maxY = activeCubes.maxOf { it.y }
        val minZ = activeCubes.minOf { it.z }
        val maxZ = activeCubes.maxOf { it.z }
        val minW = activeCubes.minOf { it.w }
        val maxW = activeCubes.maxOf { it.w }
        // println("x=$minX..$maxX, y=$minY..$maxY, z=$minZ..$maxZ")

        println()
        println("after $cycles cycles:")
        for (w in minW..maxW) {
            for (z in minZ..maxZ) {
                println()
                println("z=$z, w=$w")
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
    }
}