package year2019.day03

fun main() {
    findMinimalSteps(SAMPLE1) // 6
    findMinimalSteps(SAMPLE2) // 159
    findMinimalSteps(SAMPLE3) // 135
    findMinimalSteps(INPUT)   // 855
}

fun findMinimalSteps(input: String) {
    val instructionsWire1 = input.lines().first().split(",")
    val instructionsWire2 = input.lines().last().split(",")

    val weightedPointsWire1 = getWeightedPoint(instructionsWire1)
    val weightedPointsWire2 = getWeightedPoint(instructionsWire2)

    // find all points where wire1 and wire2 intersect (ignoring the weight for now)
    val intersectingPoints = weightedPointsWire1.map { it.point }.toSet().intersect(weightedPointsWire2.map { it.point }.toSet())

    intersectingPoints.minOfOrNull { intersection ->
        // find the point in both lists of weighted points and sum both steps
        stepsToIntersection(weightedPointsWire1, intersection) +
        stepsToIntersection(weightedPointsWire2, intersection)
    }.let { println(it) }

}

fun stepsToIntersection(weightedPoints: Set<WeightedPoint>, intersection: Point): Int {
    return weightedPoints.find { it.point == intersection }!!.steps
}

// take the list of instructions (e.g. "R13") and generate a Set of all WeightedPoint covered by this wire,
// we are starting from the origin 0/0
fun getWeightedPoint(instructions: List<String>): Set<WeightedPoint> {
    var x = 0
    var y = 0
    var steps = 0 // count the steps

    val points = mutableSetOf<WeightedPoint>()

    instructions.forEach {
        val direction = it.first()
        val distance = it.substring(1).toInt()
        when (direction) {
            'R' -> {
                for (i in 1..distance) {
                    x++
                    steps++
                    points.add(WeightedPoint(Point(x, y), steps))
                }
            }
            'L' -> {
                for (i in 1..distance) {
                    x--
                    steps++
                    points.add(WeightedPoint(Point(x, y), steps))
                }
            }
            'U' -> {
                for (i in 1..distance) {
                    y++
                    steps++
                    points.add(WeightedPoint(Point(x, y), steps))
                }
            }
            'D' -> {
                for (i in 1..distance) {
                    y--
                    steps++
                    points.add(WeightedPoint(Point(x, y), steps))
                }
            }
        }
    }
    return points
}

// a Point and the weight (a.k.a number of steps to reach this point)
data class WeightedPoint(val point: Pair<Int, Int>, val steps: Int)