package year2015.day16

// we convert each aunt info to a number, each thing has a fixed index, like in the list we need to find:
// children first, cats second, etc.
// that means: first digit of number is number of children, second digit of number is no of cats, etc.
//
// rest is filled with "." (any single char in regexp)
fun main() {
    val target = "3723005321"

    INPUT.lines().map { line ->
        val (sueNo, thing1, count1, thing2, count2, thing3, count3) = LINE_PATTERN.find(line)!!.destructured

        val pat = MutableList(10) { "." }

        // we replace the elements in the list that we know
        // we convert to HEX because some things can occur 10 times (which makes "a" in the regexp)
        pat[listOfThings.indexOf(thing1)] = Integer.toHexString(count1.toInt())
        pat[listOfThings.indexOf(thing2)] = Integer.toHexString(count2.toInt())
        pat[listOfThings.indexOf(thing3)] = Integer.toHexString(count3.toInt())

        println("$sueNo: $thing1, $count1, $thing2, $count2, $thing3, $count3 \t-> " + pat.joinToString(""))

        if (pat.joinToString("").toRegex().matches(target)) {
            println("done!")
            return
        }
    }
}