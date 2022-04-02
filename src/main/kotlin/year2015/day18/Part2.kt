package year2015.day18

fun main() {
    testInput2()
    realInput2()
}

class Life2(field: Field) : Life(field) {
    override fun step() {
        super.step()

        // corner lights are always on, no matter what the normal step in super does
        a.turnOnCornerLights()
    }
}

fun testInput2() {
    val initialField = createFieldFromInput(TEST_INPUT.lines())

    // corner lights are always on, even in initial field
    initialField.turnOnCornerLights()

    val life = Life2(initialField)
//    println(life.toString())
    repeat(5) {
        life.step()
//        println(life.toString())
    }
    println(life.countLights())
}

fun realInput2() {
    val initialField = createFieldFromInput(INPUT.lines())

    // corner lights are always on, even in initial field
    initialField.turnOnCornerLights()

    val life = Life2(initialField)
//    println(life.toString())
    repeat(100) {
        life.step()
//        println(life.toString())
    }
    println(life.countLights())
}

