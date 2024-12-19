package year2024.day18

import util.graph.Labyrinth
import util.point.Point

// dfs and bfs are good for example, but fail with real input
// dijkstra gets the job done
fun main() {
    AOC2024D18(SAMPLE).solve(7, 7, 12).also(::println) // 22
    AOC2024D18(INPUT).solve(71, 71, 1024).also(::println) // 336

    AOC2024D18(SAMPLE).solve2(7, 7, 12).also(::println) // 6,1
    AOC2024D18(INPUT).solve2(71, 71, 1024).also(::println) // 24,30
}

fun String.toPoint(): Point {
    val (x, y) = this.split(",").map { it.toInt() }
    return Point(y, x)
}

class AOC2024D18(val input: String) {
    fun solve(width: Int, height: Int, limit: Int): Int {
        val obstacles = input.lines().map { it.toPoint() }

        val shortestPath = Labyrinth.fromObstacleList(width, height, obstacles.take(limit)).dijkstraPath()

        return shortestPath.size - 1
    }

    // just brute force it, from known starting point (part 1)
    // add one more obstacle and check when cost to reach
    // lower right corner remains Int.MAX
    // one improvement would be: iterate over obstacles
    // (keep adding one more obstacle and update costs)
    //
    // note: we do not need to know/calculate the path here
    fun solve2(width: Int, height: Int, offset: Int): String {
        val obstacles = input.lines().map { it.toPoint() }

        for (i in offset..obstacles.lastIndex) {
            val costs = Labyrinth.fromObstacleList(width, height, obstacles.take(i)).dijkstraCosts()
            if (costs[width - 1][height - 1] == Int.MAX_VALUE) {
                val o = obstacles[i - 1]
                return "${o.second},${o.first}"
            }
        }
        return "wtf?"
    }
}

