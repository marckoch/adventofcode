package year2021.day06

fun main() {
    trackSingleFish(SAMPLE, 80)
    trackSingleFish(INPUT, 80)

    // OutOfMemory :-(
//    trackSingleFish(SAMPLE, 256)
//    trackSingleFish(INPUT, 256)

    trackGroups(SAMPLE, 80)
    trackGroups(INPUT, 80)

    trackGroups(SAMPLE, 256)
    trackGroups(INPUT, 256)
}

// track each fish individually like in the task -> list will get very long, very quickly
fun trackSingleFish(input: String, days: Int) {
    var numbers = input.split(",").map { it.toInt() }.toList()

    repeat(days) { day ->
        numbers = step(numbers)
        //println("${day + 1} : $numbers")
        //println("${day + 1} : ${numbers.size}")
    }

    println(numbers.size)
}

// track groups of fish of the same age
// we only need an array of length 9 (0 - 8)
// example input from task (3,4,3,1,2) will become [0,1,1,2,1,0,0,0,0]
// that means on day 3 (index 4 in array) we have 2 fish
//
// in each day this array shift to the left by one position
// the fish at pos 0 fall off and will be added to index 6 and 8
// (6 because they start again at 6 and 8 because they spawn children)
fun trackGroups(input: String, days: Int) {
    var population = input.split(",")
        .map { it.toInt() }
        .fold(Array(9) { 0L }) { acc, i ->
            acc[i] += 1L
            acc
        }.toList()

    repeat(days) { day ->
        population = stepGroup(population)
        //println("${day + 1} : population")
        //println("${day + 1} : ${population.sum()}")
    }

    println(population.sum())
}

fun stepGroup(fish: List<Long>): List<Long> {
    val day0 = fish.first()

    // day0 fish are cut off and 0 appended at end, to keep length at 9
    val new = (fish.drop(1) + listOf(0)).toMutableList()

    new[6] = new[6] + day0  // each day0 fish starts again at pos 6
    new[8] = new[8] + day0  // each day0 fish has 1 child that starts at 8
    return new
}

fun step(fish: List<Int>): List<Int> {
    var newFish = 0
    val new = fish.map { n ->
        when {
            n > 0 -> n - 1
            else -> {
                newFish++
                6
            }
        }
    }
    return if (newFish > 0)
        new + List(newFish) { 8 }
    else
        new
}