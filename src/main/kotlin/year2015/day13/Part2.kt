package year2015.day13

fun main() {
    // including me in the matrix is like adding one row of 0's and one column of 0's
    // we can do this easily by increasing the dimension by 1

    getMaximalHappiness(TEST_INPUT.lines(), 5)
    getMaximalHappiness(INPUT.lines(), 9)
}
