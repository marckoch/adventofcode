package year2015.day03

fun main() {
    val houses = HashMap<Pair<Int, Int>, Int>()
    var currentHouse = Pair(0, 0)
    houses[currentHouse] = 1

    for (c in INPUT) {
        val nextHouse = currentHouse + step(c)
        houses.merge(nextHouse, 1, Int::plus)
        currentHouse = nextHouse
    }
    println(houses.size)
}