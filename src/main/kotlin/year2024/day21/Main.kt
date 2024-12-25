package year2024.day21

import util.point.Point
import util.point.minus

//379A:
//
//<v<A>>^AvA^A <vA<AA>>^AAvA<^A>AAvA^A <vA>^AA<A>A <v<A>A>^AAAvA<^A>A
//   <   A > A   v <<   AA >  ^ AA > A   v  AA ^ A    < v  AAA >  ^ A
//       ^   A          <<      ^^   A      >>   A         vvv      A
//           3                       7           9                  A
//
//
//v<<A^>>AvA^A v<<A^>>AAv<A<A^>>AA<Av>AA^A v<A^>AA<A>A v<A<A^>>AAA<Av>A^A
//   <   A > A    <   AA  v <   AA ^  >> A   v  AA ^ A   v <   AAA ^  > A
//       ^   A        ^^        <<       A      >>   A         vvv      A
//           3                           7           9                  A
//
//
fun main() {
    AOC2024D21(SAMPLE2).solve().also(::println) // 126384
//    AOC2024D21(SAMPLE).solve().also(::println) // 126384
//    AOC2024D21(INPUT).solve().also(::println) // 210338 too high
}

class AOC2024D21(val input: String) {
    private val numericPadKeys = mapOf(
        '0' to Point(1, 2),
        'A' to Point(1, 3),
        '1' to Point(2, 1),
        '2' to Point(2, 2),
        '3' to Point(2, 3),
        '4' to Point(3, 1),
        '5' to Point(3, 2),
        '6' to Point(3, 3),
        '7' to Point(4, 1),
        '8' to Point(4, 2),
        '9' to Point(4, 3))
    private val directionalPadKeys = mapOf(
        '<' to Point(1, 1),
        'v' to Point(1, 2),
        '>' to Point(1, 3),
        '^' to Point(2, 2),
        'A' to Point(2, 3)
    )
    fun solve(): Int {
        return input.lines().sumOf { code ->
            val c = code
                .let { generateSequence(numericPadKeys, it, false) }
                .let { generateSequence(directionalPadKeys, it, false) }
                .let { generateSequence(directionalPadKeys, it, false) }
            val len = c.length
            val num = code.dropLast(1).toInt()
            println("$code: $len $num")
            len * num
        }
    }

    private fun generateSequence(keys: Map<Char, Point>, code: String, optimize: Boolean): String {
        var keySeq = code.toMutableList()
            .also { it.add(0, 'A') } // add 'A' because that is where we start

        if (optimize) {
            // optimize snippets between 'A's
            val k = keySeq.joinToString("")
            val a = k.split("A")
            val b = a.map { s -> s.toCharArray().sorted().joinToString("") }
            val c = b.joinToString("A")
            keySeq = c.toMutableList()
        }

        return keySeq.windowed(2, 1)
            .map {
                val (char1, char2) = it
                if (char1 != char2) {
                    val (pFrom, pTo) = keys[char1]!! to keys[char2]!!
                    val diff = pTo - pFrom
                    val m = diffToDir(diff)
//                    println("$char1, $char2, -> $diff -> $m")
                    m
                } else
                    ""
            }.joinToString("A", postfix = "A") // press 'A' between and at end
            .also { println("$code -> $it") }
            .also { s ->
                // just make sure we have a closed loop from 'A' to 'A'
                check(s.count { it == '^' } == s.count { it == 'v' } &&
                    s.count { it == '<' } == s.count { it == '>' } )
            }
    }

    private fun diffToDir(diff: Point): String {
        var chars = ""

        if (diff.first > 0)
            chars += ("^".repeat(diff.first))
        else if (diff.first < 0)
            chars += ("v".repeat(-diff.first))

        if (diff.second > 0)
            chars += (">".repeat(diff.second))
        else if (diff.second < 0)
            chars += ("<".repeat(-diff.second))

        return chars
    }
}
