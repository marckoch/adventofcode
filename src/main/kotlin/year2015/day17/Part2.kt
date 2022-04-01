package year2015.day17

fun main() {
    // we build each subset of all containers and check if the summ is what we need
    val testContainers = TEST_INPUT.lines().map { s -> s.toInt() }.sorted().reversed()
    val minCountTestContainers = buildSubSets(testContainers).filter { ints -> ints.sum() == 25 }.minOf { list -> list.size }

    println(buildSubSets(testContainers).count { ints -> ints.sum() == 25 && ints.size == minCountTestContainers })

    val realContainers = INPUT.lines().map { s -> s.toInt() }.sorted().reversed()
    val minCountRealContainers = buildSubSets(realContainers).filter { ints -> ints.sum() == 150 }.minOf { list -> list.size }
    println(buildSubSets(realContainers).count { ints -> ints.sum() == 150 && ints.size == minCountRealContainers})
}

