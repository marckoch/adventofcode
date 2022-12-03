package year2022.day02

fun main() {
    part1()

    part2()
}

fun part1() {
    INPUT.lines()
        .sumOf { Part1().getScore(it) }
        .let { println(it) }
}

fun part2() {
    INPUT.lines()
        .sumOf { Part2().getScore(it) }
        .let { println(it) }
}

class Part1 {
    // matrix of win situations
    // row is opponents move (A,B,C), column is my move (X,Y,Z),
    // element is result of this round: 3 means draw, 6 I win, 0 I lose
    //
    //      X(R) Y(P) Z(S)
    // A(R)  3    6    0
    // B(P)  0    3    6
    // C(S)  6    0    3
    private val winMatrix = arrayOf(
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
}

class Part2 {
    // matrix of my moves
    // row is opponents move (A,B,C), column is desired result (X,Y,Z)
    // element in matrix is move i should make
    //
    //      X(lose) Y(draw)  Z(win)
    // A(R)  2(S)     0(R)    1(P)
    // B(P)  0(R)     1(P)    2(S)
    // C(S)  1(P)     2(S)    0(R)
    private val matrixOfMyMoves = arrayOf(
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
}

// returns 1 for Rock, 2 for Paper, 3 for Scissors
fun shapeISelected(myMove: Int): Int = myMove + 1

// map line of input ("A X") to indices (first is 'A' based, last is 'X' based),
// "A X" would return 0,0
fun toIndices(line: String): Pair<Int, Int> {
    val opponentsMove = line.first().code - 'A'.code    // will be rowIndex (0, 1, 2)
    val myMove = line.trim().last().code - 'X'.code     // will be colIndex (0, 1, 2)

    return Pair(opponentsMove, myMove)
}