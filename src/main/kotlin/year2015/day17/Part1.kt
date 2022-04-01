package year2015.day17

fun main() {
    // recursive, does not work
//    val containers = TEST_INPUT.lines().map { s -> s.toInt() }.sorted().reversed()
//    println(containers)
//    fill(25, emptyList(),  containers)

    // we build each subset of all containers and check if the sum is what we need
    val testContainers = TEST_INPUT.lines().map { s -> s.toInt() }.sorted().reversed()
    println(buildSubSets(testContainers).filter { ints -> ints.sum() == 25 }.also { println(it) }.count())

    val realContainers = INPUT.lines().map { s -> s.toInt() }.sorted().reversed()
    println(buildSubSets(realContainers).count { ints -> ints.sum() == 150 })
}

// recursive
// does not work yet because it counts solutions double (e.g. 20+5 == 5+20)
// and we can not distinguish the two 5 containers
fun fill(toFill: Int, usedContainers: List<Int>, emptyContainers: List<Int>) {
//    println("to fill: $toFill, usedContainers: $usedContainers, emptyContainers: $emptyContainers")
    if (toFill == 0) {
        println("found one combination: $usedContainers")
        return
    }
    val possibleContainers = emptyContainers.filter { i -> i <= toFill }

    for (c in possibleContainers) {
        val newUsedContainers = usedContainers.toMutableList()
        newUsedContainers.add(c)

        val newEmptyContainers = emptyContainers.toMutableList()
        newEmptyContainers.remove(c)

        fill(toFill - c, newUsedContainers, newEmptyContainers)
    }
}