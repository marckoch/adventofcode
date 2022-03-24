package year2015.day03

fun main() {
    val houses = HashMap<Pair<Int, Int>, Int>()
    var currentHouseSanta = Pair(0, 0)
    var currentHouseRoboSanta = Pair(0, 0)
    houses[currentHouseSanta] = 1

    for ((i,c) in INPUT.withIndex()) {
        if (i % 2 == 0) { // Santa
            val nextHouse = currentHouseSanta + step(c)
            houses.merge(nextHouse, 1, Int::plus)
            currentHouseSanta = nextHouse
        } else { // RoboSanta
            val nextHouse = currentHouseRoboSanta + step(c)
            houses.merge(nextHouse, 1, Int::plus)
            currentHouseRoboSanta = nextHouse
        }
    }
    println(houses.size)
}