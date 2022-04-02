package year2015.day19

// TODO: does not work, too slow
// this is basically a DFS approach, maybe a BFS would be better?
fun main() {
    val start = "e"
    val target = "HOHOHO"
    val step = 0
//    replaceRecur(start, target, step, getReplacementsAsMap(TEST_INPUT.lines()))
    replaceRecur(start, REAL_MOLECULE, step, getReplacementsAsMap(INPUT.lines()))
}

fun replaceRecur(s: String, target: String, step: Int, replacements: Map<String,List<String>>) {
    if (s == target) {
        println("found it after $step steps!")
        return
    }
    if (s.length >= target.length) {
        return
    }
    val splitStart = ELEMENT.findAll(s).toList().map { matchResult -> matchResult.value }
//    println(splitStart)

    println(s.length)

//    val results = HashSet<String>()
    for ((i, c) in splitStart.withIndex()) {
        if (replacements.containsKey(c)) {
            for (r in replacements[c]!!) {
                val newList = splitStart.subList(0, i) + r + splitStart.subList(i + 1, splitStart.size)
//                results.add(newList.joinToString(""))
                //println(newList.joinToString(""))
                replaceRecur(newList.joinToString(""), target, step+1, replacements)
            }
        }
    }
//    println(results.size)
}