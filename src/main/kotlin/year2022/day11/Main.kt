package year2022.day11

fun main() {
    // part1: level is adjusted by dividing by 3
    val levelAdjustorPart1: (Long) -> Long = { i -> i / 3 }
    solve(SAMPLE, SampleRules, 20, levelAdjustorPart1)
    solve(INPUT, InputRules, 20, levelAdjustorPart1)

    // part2: level is adjusted by modulo with factor
    fun levelAdjustorPart2(moduloFactor: Int): (Long) -> Long = {i -> i % moduloFactor}
    solve(SAMPLE, SampleRules, 10_000, levelAdjustorPart2(SampleRules.getModuloFactor()))
    solve(INPUT, InputRules, 10_000, levelAdjustorPart2(InputRules.getModuloFactor()))
}

fun solve(input: String, rules: Rules, rounds: Int, levelAdjust: (Long) -> Long) {
    val monkeys = getInputForMonkeys(input).map { buildMonkey(it, rules, levelAdjust) }
    //monkeys.forEach { println(it) }

    repeat(rounds) {
        round(monkeys)
    }

    // monkeys.forEach { println(it) }

    monkeys
        .map { it.numberOfItemsChecked.toLong() }
        .sorted()
        .reversed()
        .take(2)
        .reduce { acc, i -> acc * i }
        .let { println(it) }
}

fun round(monkeys: List<Monkey>) {
    monkeys.forEach { monkey ->
        while (monkey.items.isNotEmpty()) {
            val (otherMonkey, level) = monkey.processItem()
            monkeys[otherMonkey].items.addLast(level)
        }
    }
}

fun getInputForMonkeys(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf<MutableList<String>>()) { list, line ->
        if (line.isEmpty()) {
            list.add(mutableListOf())
        } else {
            if (list.isEmpty()) list.add(mutableListOf())
            list.last().add(line.trim())
        }
        list
    }
}

fun buildMonkey(lines: List<String>, rules: Rules, levelAdjust: (Long) -> Long): Monkey {
    val (id) = "Monkey (\\d+):".toRegex().find(lines[0])!!.destructured

    val (itemsRaw) = "Starting items: (.*)".toRegex().find(lines[1])!!.destructured
    val items = itemsRaw.split(",").map { it.trim().toLong() }

    return Monkey(id.toInt(), ArrayDeque(items), rules, levelAdjust)
}

class Monkey(private val id: Int, var items: ArrayDeque<Long>, private val rules : Rules, private val levelAdjust: (Long) -> Long) {
    var numberOfItemsChecked = 0

    fun processItem(): Pair<Int, Long> {
        numberOfItemsChecked += 1
        val item = items.removeFirst()
        //println("$item , rest= $items")
        val level = op(item)

//        val level2 = level / 3  // part1
//        val level2 = level % rules.getModuloFactor()  // part2
        val level2 = levelAdjust(level)

        //println("level after check: $level, then $level2")
        val nextMonkey = test(level2)
        //println("throwing item with level $level2 to monkey $nextMonkey")
        return Pair(nextMonkey, level2)
    }

    private fun op(x: Long): Long {
        return rules.getOperationForMonkey(id)(x)
    }

    private fun test(x: Long): Int {
        return rules.getTest(id)(x)
    }

    override fun toString(): String {
        return "($id, items=$items, countItemCheck=$numberOfItemsChecked )"
    }
}

interface Rules {
    fun getOperationForMonkey(id: Int): (Long) -> Long
    fun getTest(id: Int): (Long) -> Int
    fun getModuloFactor(): Int
}

object SampleRules : Rules {
    // TODO: can we parse this from input and generate dynamically?
    override fun getOperationForMonkey(id: Int): (Long) -> Long {
        when (id) {
            0 -> return { i -> i * 19 }
            1 -> return { i -> i + 6 }
            2 -> return { i -> i * i }
            3 -> return { i -> i + 3 }
        }
        throw IllegalArgumentException("no operation method defined for id $id")
    }

    // TODO: can we parse this from input and generate dynamically?
    override fun getTest(id: Int): (Long) -> Int {
        when (id) {
            0 -> return { i -> if (i % 23 == 0L) 2 else 3 }
            1 -> return { i -> if (i % 19 == 0L) 2 else 0 }
            2 -> return { i -> if (i % 13 == 0L) 1 else 3 }
            3 -> return { i -> if (i % 17 == 0L) 0 else 1 }
        }
        throw IllegalArgumentException("no test method defined for id $id")
    }

    override fun getModuloFactor(): Int {
        // product of all reminder check in test method
        // idea behind this: the exact worry level is irrelevant,
        // just its reminder in test method is important to find next monkey
        return 13 * 17 * 19 * 23
    }
}

object InputRules : Rules {
    // TODO: can we parse this from input and generate dynamically?
    override fun getOperationForMonkey(id: Int): (Long) -> Long {
        when (id) {
            0 -> return { i -> i * 5 }
            1 -> return { i -> i + 6 }
            2 -> return { i -> i + 5 }
            3 -> return { i -> i + 2 }
            4 -> return { i -> i * 7 }
            5 -> return { i -> i + 7 }
            6 -> return { i -> i + 1 }
            7 -> return { i -> i * i }
        }
        throw IllegalArgumentException("no operation method defined for id $id")
    }

    // TODO: can we parse this from input and generate dynamically?
    override fun getTest(id: Int): (Long) -> Int {
        when (id) {
            0 -> return { i -> if (i % 3 == 0L) 7 else 4 }
            1 -> return { i -> if (i % 17 == 0L) 3 else 0 }
            2 -> return { i -> if (i % 2 == 0L) 3 else 1 }
            3 -> return { i -> if (i % 19 == 0L) 7 else 0 }
            4 -> return { i -> if (i % 11 == 0L) 5 else 6 }
            5 -> return { i -> if (i % 5 == 0L) 2 else 1 }
            6 -> return { i -> if (i % 13 == 0L) 5 else 2 }
            7 -> return { i -> if (i % 7 == 0L) 4 else 6 }
        }
        throw IllegalArgumentException("no test method defined for id $id")
    }

    override fun getModuloFactor(): Int {
        // product of all reminder check in test method
        // idea behind this: the exact worry level is irrelevant,
        // just its reminder in test method is important to find next monkey
        return 2 * 3 * 5 * 7 * 11* 13 * 17 * 19
    }
}