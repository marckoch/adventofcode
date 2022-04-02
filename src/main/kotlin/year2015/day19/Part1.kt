package year2015.day19

fun main() {
    val testReplacements = getReplacementsAsMap(TEST_INPUT.lines())
    replace("HOH", testReplacements)

    val realReplacements = getReplacementsAsMap(INPUT.lines())
    replace(REAL_MOLECULE, realReplacements)
}

fun replace(start: String, replacements: Map<String, List<String>>) {
    // we have to split the elements, we can not just iterate over the chars of the string,
    // because two chars might form one element (e.g. 'Ca')
    val splitStart = ELEMENT.findAll(start).toList().map { matchResult -> matchResult.value }
//    println(splitStart)

    val results = HashSet<String>()
    for ((i, c) in splitStart.withIndex()) {
        if (replacements.containsKey(c)) {
            for (r in replacements[c]!!) {
                val newList = splitStart.subList(0, i) + r + splitStart.subList(i + 1, splitStart.size)
                results.add(newList.joinToString(""))
                println(newList.joinToString(""))
            }
        }
    }
    println(results.size)
}