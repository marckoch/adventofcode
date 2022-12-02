package year2022.day02.part1

import year2022.day02.INPUT
import year2022.day02.shapeISelected

fun main() {
    println(getScoresPerRound().sum())
}

// matrix of win situations
// row is opponents move (A,B,C), column is my move (X,Y,Z),
// element is result of this round: 0 means draw, 1 I win, -1, I lose
//
//      X(R) Y(P) Z(S)
// A(R)   0    1   -1
// B(P)  -1    0    1
// C(S)   1   -1    0
val winMatrix = arrayOf(
    arrayOf( 0,  1, -1),
    arrayOf(-1,  0,  1),
    arrayOf( 1, -1,  0)
)

fun getScoresPerRound(): List<Int> {
    return INPUT.lines().map { getScore(it) }
}

// map line of input ("A X") to result of this round
fun getScore(line: String): Int {
    val opponentsMove = line.first().code - 'A'.code    // will be rowIndex (0,1,2)
    val myMove = line.trim().last().code - 'X'.code     // will be colIndex (0,1,2)

    val result = winMatrix[opponentsMove][myMove]

    return shapeISelected(myMove) + outcomeOfRound(result)
}

// input -> output
// -1       0           I lose
// 0        3           draw
// 1        6           I win
fun outcomeOfRound(result: Int): Int = 3 + 3 * result