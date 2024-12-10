package year2024.day09

fun main() {
    AOC2024D09(SAMPLE).solvePart1().let { println(it) } // 1928
    AOC2024D09(INPUT).solvePart1().let { println(it) } // 6337921897505

//    AOC2024D09(SAMPLE).solvePart2().let { println(it) } // 2858
//    AOC2024D09(INPUT).solvePart2().let { println(it) } // 6362722604045
}

class AOC2024D09(val input: String) {

    fun solvePart1(): Long {
        val i = expand(input)
        val d = defrag(i)
        return checksum(d)
    }

    fun solvePart2(): Long {
        val i = expand(input)
        val d = defrag2(i)
        return checksum(d)
    }

    private fun expand(input: String): IntArray {
        var isFile = true
        var fileIdNumber = 0
        val result = mutableListOf<Int>()
        input.forEach {
            val n = it.digitToInt()
            if (isFile) {
                List(n) { fileIdNumber }.also { fileIdNumber++ }
            } else {
                List(n) { -1 } // empty == dot represented by -1
            }.also { result.addAll(it) }

            isFile = !isFile
        }
        return result.toIntArray()
    }

    /**
     * one IntArray, two iterators:
     * one going from 0->lastIndex, looking for free space (==-1)
     * the other going from lastIndex->0, looking for file (>-1)
     * abort if they meet.
     * if both found something, swap contents
     */
    private fun defrag(arr: IntArray): IntArray {
        var fileIndex = arr.lastIndex
        var freeSpaceIndex = 0
        while (true) {
            // look for file
            while (arr[fileIndex] < 0) {
                fileIndex--
            }
            // look for free space
            while (arr[freeSpaceIndex] >= 0) {
                freeSpaceIndex++
            }

            if (freeSpaceIndex >= fileIndex) {
                break
            }

            // swap file and free space
//                println("moving file $fileIndex to $freeSpaceIndex")
            arr[freeSpaceIndex] = arr[fileIndex]
            arr[fileIndex] = -1
        }
        return arr
    }

    private fun defrag2(input: IntArray): IntArray {
        var end = input.lastIndex
        while (end >= 0) {
            val (from, to) = findLastFile(input, end)
            val length = to - from + 1
//            println("$from, $to -> $length ${input.slice(from..to)}")
            val startOfFreeSpace = findFreeSpace(input, length, from)
            if (startOfFreeSpace >= to)
                break
            if (startOfFreeSpace >= 0) {
//                println("moving ${input.slice(from..to)} from [$from,$to] to [$startOfFreeSpace]")
                for (i in 0 until length) {
                    input[startOfFreeSpace + i] = input[from + i]
                    input[from + i] = -1
                }
//                println(input.toList())
            } else {
//                println("NOT moving ${input.slice(from..to)} from [$from,$to] because no free space found")
                end = from - 1
            }
        }

        return input
    }

    private fun findLastFile(arr: IntArray, beingSearchIndex: Int) : Pair<Int, Int> {
//        println("findLastFile $end in \n${arr.toList()}")

        // starting from the 'beingSearchIndex' and going to front of file,
        // search for the last element >=0
        var lastIndexOfFile = beingSearchIndex
        var file = arr[lastIndexOfFile]
        while (file < 0) {
            lastIndexOfFile--
            file = arr[lastIndexOfFile]
        }

        // starting from the found end of file and going to the front,
        // search for the start of the file
        var startIndexOfFile = lastIndexOfFile
        while (startIndexOfFile > 1 && arr[startIndexOfFile - 1] == arr[lastIndexOfFile]) {
            startIndexOfFile--
        }

        return startIndexOfFile to lastIndexOfFile
    }

    private fun findFreeSpace(arr: IntArray, length: Int, upTo: Int): Int {
//        println("findFreeSpace $length in \n${arr.toList()}")
        if (arr.sliceArray(0 .. upTo).all { it >= 0 }) return -1
        for (start in 0 until upTo) {
            val isFile = arr[start] >=0
            if (isFile) {
                continue
            } else {
                if (start + length > arr.lastIndex) {return -1}
                if (arr.sliceArray(start until start + length).all { it == -1 }) {
                    return start
                }
            }
        }
        return -1
    }

    private fun checksum(ints: IntArray): Long {
        return ints
            // remove all -1 again before calculating check sum
            .map { i -> if (i < 0) 0 else i }
            .withIndex()
            .fold(0L) { acc, i -> acc + (i.index * i.value) }
    }
}
