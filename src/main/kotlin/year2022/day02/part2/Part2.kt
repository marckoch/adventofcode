package year2022.day02.part2

import year2022.day02.INPUT
import year2022.day02.shapeISelected
import year2022.day02.toIndices

fun main() {
    INPUT.lines()
        .sumOf { getScore(it) }
        .let { println(it) }
}

// matrix of my moves
// row is opponents move (A,B,C), column is desired result (X,Y,Z)
// element in matrix is move i should make
//
//      X(lose) Y(draw)  Z(win)
// A(R)  2(S)     0(R)    1(P)
// B(P)  0(R)     1(P)    2(S)
// C(S)  1(P)     2(S)    0(R)
val matrixOfMyMoves = arrayOf(
    arrayOf(2, 0, 1),
    arrayOf(0, 1, 2),
    arrayOf(1, 2, 0)
)

// map line of input ("A X") to my move
fun getScore(line: String): Int {
    val (opponentsMove, desiredResult) = toIndices(line)

    val myMove = matrixOfMyMoves[opponentsMove][desiredResult]

    return shapeISelected(myMove) + 3 * desiredResult
}