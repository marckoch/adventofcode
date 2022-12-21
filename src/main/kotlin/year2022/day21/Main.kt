package year2022.day21

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    // idea for part2: parse monkeys and invert their operations
    // remove 'humn: 5'
    // eval roots two parts, one will eval and give number, other will fail (because it has 'humn')
    // correct one can be added as simple number to tree
    // question: how can we handle multiple monkeys? a: b + c means b = a - c and c - a - b
    // what if a and b are already given in other equations?
}

val PLUS  = """(.*) \+ (.*)""".toRegex()
val MINUS = """(.*) - (.*)""".toRegex()
val MULTIPLICATION = """(.*) \* (.*)""".toRegex()
val DIVISION = """(.*) / (.*)""".toRegex()
val NUMBER = """(\d+)""".toRegex()

fun part1(input: String) {
    val monkeys = input.lines().map { l ->
        l.split(": ").let { s -> Monkey(s[0], s[1]) }
    }.associateBy { it.name }

    Monkeys(monkeys).eval()
}

class Monkeys(private val monkeys: Map<String, Monkey>) {

    fun eval() {
        eval(monkeys["root"]!!).let { println("part1: $it") }
    }

    private fun eval(monkey: Monkey): Long {
        return when {
            PLUS.matches(monkey.op) -> eval(PLUS, monkey.op, Long::plus)
            MINUS.matches(monkey.op) -> eval(MINUS, monkey.op, Long::minus)
            MULTIPLICATION.matches(monkey.op) -> eval(MULTIPLICATION, monkey.op, Long::times)
            DIVISION.matches(monkey.op) -> eval(DIVISION, monkey.op, Long::div)
            NUMBER.matches(monkey.op) -> monkey.op.toLong()
            else -> throw IllegalArgumentException("computer says no: ${monkey.op}")
        }
    }

    private fun eval(regex: Regex, op: String, f: (Long, Long) -> Long): Long {
        val (m1, m2) = regex.find(op)!!.destructured
        val e1 = eval(monkeys[m1]!!)
        val e2 = eval(monkeys[m2]!!)
        return f(e1, e2)
    }
}

class Monkey(val name: String, val op: String) {
    override fun toString(): String {
        return "name=$name op=$op"
    }
}