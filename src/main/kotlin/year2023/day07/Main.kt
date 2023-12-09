package year2023.day07

fun main() {
    part1(SAMPLE).let { println(it) } // 6440
    part1(INPUT).let { println(it) }  // 252656917

    part2(SAMPLE).let { println(it) } // 5905
    part2(INPUT).let { println(it) } // 253499763
}

fun part1(input: String): Int {
    val c = input.lines()
        .asSequence()
        .drop(1) // first line is empty
        .map { line ->
            Hand1(line)
        }
        .sortedBy { it }
        .withIndex()
        .map { IndexedValue(it.index + 1, it.value) }
        .toList()
//         .forEach { println(it) }

    return c.sumOf { it.index * it.value.bid }
}

fun part2(input: String): Int {
    val c = input.lines()
        .asSequence()
        .drop(1) // first line is empty
        .map { line ->
            Hand2(line)
        }
        .sortedBy { it }
        .withIndex()
        .map { IndexedValue(it.index + 1, it.value) }
        .toList()
//         .forEach { println(it) }

    return c.sumOf { it.index * it.value.bid }
}

data class Hand1(val line: String): Comparable<Hand1> {
    private val freq: List<Map.Entry<Char, Int>>
    val bid: Int
    private val cards: List<Char>

    init {
        val (h, b) = line.split(" ")
        cards = h.toList()
        bid = b.toInt()

        // build frequency map for hand
        freq = cards.groupingBy { it }.eachCount().entries
            .sortedWith(
                compareByDescending<Map.Entry<Char, Int>> { it.value }
                    .thenByDescending { strength(it.key) })
        // println("$line -> $freq ${type()} $bid")
    }

    private fun strength(card: Char): Int {
        return "23456789TJQKA".indexOf(card)
    }

    override fun compareTo(other: Hand1): Int {
        // frequencies always look like this:
        // 5, 0, 0, 0, 0 five of a kind
        // 4, 1, 0, 0, 0 four of a kind
        // 3, 2, 0, 0, 0 full house
        // 3, 1, 1, 0, 0 three of a kind
        // 2, 2, 1, 0, 0 two pair
        // 2, 1, 1, 1, 0 one pair
        // 1, 1, 1, 1, 1 high card
        // --> it is enough two compare the first 2 groups to determine hand type
        return compareBy(
            { hand: Hand1 -> hand.freq[0].value },
            { hand: Hand1 -> hand.freq[1].value },
            // after this we now we have two equally strong hands, so we need to compare the individual cards
            // "second ordering rule" in text
            { hand: Hand1 -> strength(hand.cards[0]) },
            { hand: Hand1 -> strength(hand.cards[1]) },
            { hand: Hand1 -> strength(hand.cards[2]) },
            { hand: Hand1 -> strength(hand.cards[3]) },
            { hand: Hand1 -> strength(hand.cards[4]) }).compare(this, other)
    }

    fun type(): Type {
        // 5, 0, 0, 0, 0 five of a kind
        // 4, 1, 0, 0, 0 four of a kind
        // 3, 2, 0, 0, 0 full house
        // 3, 1, 1, 0, 0 three of a kind
        // 2, 2, 1, 0, 0 two pair
        // 2, 1, 1, 1, 0 one pair
        // 1, 1, 1, 1, 1 high card
        return if (freq[0].value == 5) {
            Type.FIVE_OF_A_KIND
        } else if (freq[0].value == 4) {
            Type.FOUR_OF_A_KIND
        } else if (freq[0].value == 3 && freq[1].value == 2) {
            Type.FULL_HOUSE
        } else if (freq[0].value == 3) {
            Type.THREE_OF_A_KIND
        } else if (freq[0].value == 2 && freq[1].value == 2) {
            Type.TWO_PAIR
        } else if (freq[0].value == 2) {
            Type.ONE_PAIR
        } else {
            Type.HIGH_CARD
        }
    }

    enum class Type {
        FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD;
    }
}

data class Hand2(val line: String): Comparable<Hand2> {
    private val freq: List<Map.Entry<Char, Int>>
    val bid: Int
    private val cards: List<Char>

    init {
        val (h, b) = line.split(" ")
        cards = h.toList()
        bid = b.toInt()

        // build frequency map for hand
        freq = cards.groupingBy { it }.eachCount().entries
            .sortedWith(
                compareByDescending<Map.Entry<Char, Int>> { it.value }
                    .thenByDescending { strength(it.key) })
        // println("$line -> $freq ${type()} $bid")
    }

    private fun strength(card: Char): Int {
        return "J23456789TQKA".indexOf(card)
    }

    override fun compareTo(other: Hand2): Int {
        // frequencies always look like this:
        // 5, 0, 0, 0, 0 five of a kind
        // 4, 1, 0, 0, 0 four of a kind
        // 3, 2, 0, 0, 0 full house
        // 3, 1, 1, 0, 0 three of a kind
        // 2, 2, 1, 0, 0 two pair
        // 2, 1, 1, 1, 0 one pair
        // 1, 1, 1, 1, 1 high card
        // --> it is enough two compare the first 2 groups to determine hand type
        return compareBy(
            { hand: Hand2 -> hand.promote().freq[0].value },
            { hand: Hand2 -> if (hand.promote().freq.size > 1) hand.promote().freq[1].value else 0 },
            // after this we now we have two equally strong hands, so we need to compare the individual cards
            // "second ordering rule" in text
            { hand: Hand2 -> strength(hand.cards[0]) },
            { hand: Hand2 -> strength(hand.cards[1]) },
            { hand: Hand2 -> strength(hand.cards[2]) },
            { hand: Hand2 -> strength(hand.cards[3]) },
            { hand: Hand2 -> strength(hand.cards[4]) })
            .compare(this, other)
    }

    private fun promote(): Hand2 {
        if (!line.contains('J')) return this

        val newHand = when {
            type() == Type.FIVE_OF_A_KIND -> {
                Hand2(line.replace("J", "A"))
            }
            type() == Type.FOUR_OF_A_KIND -> {
                if (freq[0].key == 'J')
                    Hand2(line.replace("J", freq[1].key.toString()))
                else
                    Hand2(line.replace("J", freq[0].key.toString()))
            }
            type() == Type.FULL_HOUSE -> {
                if (freq[0].key == 'J')
                    Hand2(line.replace("J", freq[1].key.toString()))
                else
                    Hand2(line.replace("J", freq[0].key.toString()))
            }
            type() == Type.THREE_OF_A_KIND -> {
                if (freq[0].key == 'J')
                    Hand2(line.replace("J", freq[1].key.toString()))
                else
                    Hand2(line.replace("J", freq[0].key.toString()))
            }
            type() == Type.TWO_PAIR -> {
                if (freq[0].key == 'J')
                    Hand2(line.replace("J", freq[1].key.toString()))
                else
                    Hand2(line.replace("J", freq[0].key.toString()))
            }
            type() == Type.ONE_PAIR -> {
                if (freq[0].key == 'J')
                    Hand2(line.replace("J", freq[1].key.toString()))
                else
                    Hand2(line.replace("J", freq[0].key.toString()))
            }
            type() == Type.HIGH_CARD -> {
                Hand2(line.replace("J", freq[0].key.toString()))
            }
            else -> this
        }
//        if (newHand.line != line)
//            println("promoting $line -> $newHand")
        return newHand
    }

    fun type(): Type {
        // 5, 0, 0, 0, 0 five of a kind
        // 4, 1, 0, 0, 0 four of a kind
        // 3, 2, 0, 0, 0 full house
        // 3, 1, 1, 0, 0 three of a kind
        // 2, 2, 1, 0, 0 two pair
        // 2, 1, 1, 1, 0 one pair
        // 1, 1, 1, 1, 1 high card
        return if (freq[0].value == 5) {
            Type.FIVE_OF_A_KIND
        } else if (freq[0].value == 4) {
            Type.FOUR_OF_A_KIND
        } else if (freq[0].value == 3 && freq[1].value == 2) {
            Type.FULL_HOUSE
        } else if (freq[0].value == 3) {
            Type.THREE_OF_A_KIND
        } else if (freq[0].value == 2 && freq[1].value == 2) {
            Type.TWO_PAIR
        } else if (freq[0].value == 2) {
            Type.ONE_PAIR
        } else {
            Type.HIGH_CARD
        }
    }

    enum class Type {
        FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD;
    }
}