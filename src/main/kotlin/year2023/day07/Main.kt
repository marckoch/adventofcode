package year2023.day07

fun main() {
    part1(SAMPLE).let { println(it) } // 6440
    part1(INPUT).let { println(it) }  // 252656917
}

fun part1(input: String): Int {
    val c = input.lines()
        .asSequence()
        .drop(1) // first line is empty
        .map { line ->
            Hand(line)
        }
        .sortedBy { it }
        .withIndex()
        .map { IndexedValue(it.index + 1, it.value) }
        .toList()
//         .forEach { println(it) }

    return c.sumOf { it.index * it.value.bid }
}

data class Hand(val line: String): Comparable<Hand> {
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
//         println("$line -> $freq ${type()} $bid")
    }

    private fun strength(card: Char): Int {
        return "23456789TJQKA".indexOf(card)
    }

    override fun compareTo(other: Hand): Int {
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
            { hand: Hand -> hand.freq[0].value },
            { hand: Hand -> hand.freq[1].value },
            // after this we now we have two equally strong hands, so we need to compare the individual cards
            // "second ordering rule" in text
            { hand: Hand -> strength(hand.cards[0]) },
            { hand: Hand -> strength(hand.cards[1]) },
            { hand: Hand -> strength(hand.cards[2]) },
            { hand: Hand -> strength(hand.cards[3]) },
            { hand: Hand -> strength(hand.cards[4]) }).compare(this, other)
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
