package year2015.day18

// like game of life
fun main() {
    testInput1()
    realInput1()
}

fun testInput1() {
    val initialField = createFieldFromInput(TEST_INPUT.lines())

    val life = Life(initialField)
//    println(life.toString())
    repeat (4) {
        life.step()
//        println(life.toString())
    }
    println(life.countLights())
}

fun realInput1() {
    val initialField = createFieldFromInput(INPUT.lines())

    val life = Life(initialField)
//    println(life.toString())
    repeat (100) {
        life.step()
//        println(life.toString())
    }
    println(life.countLights())
}