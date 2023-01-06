package year2020.day23

const val SAMPLE = "389125467"

const val INPUT = "789465123"

fun main() {
    // first part can be solved with linked list, second part can not be solved with it, it is too slow
    solveWithLinkedList(SAMPLE)
    solveWithLinkedList(INPUT)

    // as reference, we solve part 1 again with array, results are the same
    solveWithArray(SAMPLE, 9, 100).let {
        println("part1:")
        print(it, 1)
    }
    solveWithArray(INPUT, 9, 100).let {
        println("part1:")
        print(it, 1)
    }

    solveWithArray(SAMPLE, 1_000_000, 10_000_000).let {
        print(it, 1, 10)
        println("part2: ${it[1].toLong() * it[it[1]]}")
    }
    solveWithArray(INPUT, 1_000_000, 10_000_000).let {
        print(it, 1, 10)
        println("part2: ${it[1].toLong() * it[it[1]]}")
    }
}

// we use the 'natural' approach and use a linked list
fun solveWithLinkedList(input: String) {
    val numbers = input.map { it.digitToInt() }

    val min = numbers.min()
    val max = numbers.max()

    // build linked list
    val nodes = numbers.map { Node(it) }
    nodes.windowed(2, 1).map { (c1, c2) -> c1.next = c2 }
    nodes.last().next = nodes.first()

    var currentNode = nodes.first()
    repeat(100) {
        val cut = removeSubRingAfter(currentNode, 3)
        val destination = destination(currentNode, currentNode.label, min, max)
        insertRingAfter(destination, cut)
        currentNode = currentNode.next
    }
    println("part1 with node:")
    printRing(currentNode)
}

// we use an array 'nextOf', index is the number x and the value 'nextOf[x]' is the next number after x
fun solveWithArray(input: String, N: Int, times: Int): Array<Int> {
    val numbers = input.map { it.digitToInt() } + (input.length + 1..N)

    val min = numbers.min()
    val max = numbers.max()

    // build array
    val nextOf = Array(numbers.size + 1) { 0 }
    numbers.windowed(2, 1).map { (n1, n2) -> nextOf[n1] = n2 }
    nextOf[numbers.last()] = numbers.first()

    var current = numbers.first()
    repeat(times) {
        val removed = removeSubRingAfter(nextOf, current)
        val destination = findDestination(current, removed, min, max)
        insertAfter(nextOf, destination, removed.first())
        current = nextOf[current]
    }
    return nextOf
}

fun removeSubRingAfter(nextOf: Array<Int>, current: Int): List<Int> {
    val startOfSubChain = nextOf[current]
    val middleOfSubChain = nextOf[startOfSubChain]
    val endOfSubChain = nextOf[middleOfSubChain]

    val removedNumbers = listOf(startOfSubChain, middleOfSubChain, endOfSubChain)

    nextOf[current] = nextOf[endOfSubChain]
    nextOf[endOfSubChain] = startOfSubChain

    return removedNumbers
}

fun insertAfter(nextOf: Array<Int>, destination: Int, startOfSubChain: Int) {
    val middleOfSubChain = nextOf[startOfSubChain]
    val endOfSubChain = nextOf[middleOfSubChain]

    val nextOfDest = nextOf[destination]

    nextOf[destination] = startOfSubChain
    nextOf[endOfSubChain] = nextOfDest
}

fun findDestination(current: Int, removedNumbers: List<Int>, min: Int, max: Int): Int {
    var destination = if (current <= min) max else current - 1
    while (destination in removedNumbers) {
        destination = if (destination <= min) max else destination - 1
    }
    return destination
}

fun print(nextOf: Array<Int>, current: Int, limit: Int=nextOf.size) {
    var curr = current
    print("(${curr}) ")
    curr = nextOf[curr]
    var count = 0
    while (count++ < limit) {
        print("${curr} ")
        curr = nextOf[curr]
        if (curr == current) break
    }
    println()
}

fun destination(node: Node, label: Int, min: Int, max: Int): Node {
    val dest = if (label < min) max else label - 1
    return node.findByLabel(dest) ?: destination(node, dest, min, max)
}

// after the node we remove the next 'lengthOfCut' nodes
// we return the remaining ring and the removes sub ring
// e.g. when ...->2->3->4->5->6->...
// -> remove 2, 3 will change node to ...->2->6->... and return 3->4->5->3
fun removeSubRingAfter(node: Node, lengthOfCut: Int): Node {
    val startOfSubRing = node.next
    val endOfSubRing = node.nThNext(lengthOfCut)
    node.next = endOfSubRing.next
    endOfSubRing.next = startOfSubRing
    return startOfSubRing
}

// after given 'node' we insert subRing, starting with 'firstOfSubRing'
// e.g. when ...->2->3->   and subRing is 6->7->8->6
// insert 2, 6 will return --->2->6->7->8->3->...
fun insertRingAfter(node: Node, firstOfSubRing: Node): Node {
    val nextOfInsertionStart = node.next
    val endOfSubRing = firstOfSubRing.nThNext(firstOfSubRing.length() - 1)
    endOfSubRing.next = nextOfInsertionStart
    node.next = firstOfSubRing
    return node
}

fun printRing(node: Node) {
    var failsafe = 0
    var curr = node
    print("(${curr.label}) ")
    while (true && failsafe++ < 15) {
        curr = curr.next()
        if (curr.label == node.label)
            break
        print("${curr.label} ")
    }
    println()
}

data class Node(val label: Int) : Iterator<Node> {
    lateinit var next: Node

    override fun hasNext(): Boolean = true // because we have a ring

    override fun next(): Node {
        return next
    }

    // get the n-th next node starting from this (call 'next' n times)
    fun nThNext(n: Int): Node {
        var curr = this
        repeat(n) {
            curr = curr.next()
        }
        return curr
    }

    // find a node with given label in this ring
    fun findByLabel(label: Int): Node? {
        var failsafe = 0
        var curr = this
        while (true && failsafe++ < 15) {
            if (curr.label == label) return curr
            curr = curr.next()
            if (curr == this) return null
        }
        throw IllegalStateException("can not find label in ring")
    }

    // how many nodes do we have in this ring?
    fun length(): Int {
        var failsafe = 0
        var curr = this
        var count = 0
        while (true && failsafe++ < 15) {
            count++
            curr = curr.next()
            if (curr.label == this.label)
                return count
        }
        throw IllegalStateException("can not determine length of ring")
    }

    override fun toString(): String {
        return "($label -> ${next.label})"
    }
}