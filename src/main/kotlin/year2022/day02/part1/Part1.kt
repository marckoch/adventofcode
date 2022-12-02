package year2022.day02.part1

import year2022.day02.INPUT
import year2022.day02.shapeISelected
import year2022.day02.toIndices

fun main() {
    println(INPUT.lines().sumOf { getScore(it) })
}

// matrix of win situations
// row is opponents move (A,B,C), column is my move (X,Y,Z),
// element is result of this round: 3 means draw, 6 I win, 0 I lose
//
//      X(R) Y(P) Z(S)
// A(R)  3    6    0
// B(P)  0    3    6
// C(S)  6    0    3
val winMatrix = arrayOf(
    arrayOf(3, 6, 0),
    arrayOf(0, 3, 6),
    arrayOf(6, 0, 3)
)

// map line of input ("A X") to result of this round
fun getScore(line: String): Int {
    val (opponentsMove, myMove) = toIndices(line)

    val result = winMatrix[opponentsMove][myMove]

    return shapeISelected(myMove) + result
}