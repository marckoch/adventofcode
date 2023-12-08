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
    val freq: List<Map.Entry<Char, Int>>
    val bid: Int
    val cards: List<Char>

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

    fun strength(card: Char): Int {
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
        // --> it is enough two compare the first 2 groups to determine hand strength
        val countGroup1 = freq[0].value.compareTo(other.freq[0].value)
        if (countGroup1 != 0) return countGroup1
        val countGroup2 = freq[1].value.compareTo(other.freq[1].value)
        if (countGroup2 != 0) return countGroup2

        // after this we now we have two equally strong hands, so we need to compare the individual cards
        val strengthCard1 = strength(cards[0]).compareTo(strength(other.cards[0]))
        if (strengthCard1 != 0) return strengthCard1
        val strengthCard2 = strength(cards[1]).compareTo(strength(other.cards[1]))
        if (strengthCard2 != 0) return strengthCard2
        val strengthCard3 = strength(cards[2]).compareTo(strength(other.cards[2]))
        if (strengthCard3 != 0) return strengthCard3
        val strengthCard4 = strength(cards[3]).compareTo(strength(other.cards[3]))
        if (strengthCard4 != 0) return strengthCard4
        val strengthCard5 = strength(cards[4]).compareTo(strength(other.cards[4]))
        if (strengthCard5 != 0) return strengthCard5

        // real poker rules:
//        val countGroup1 = freq[0].value.compareTo(other.freq[0].value)
//        if (countGroup1 != 0) return countGroup1
//        val strengthGroup1 = strength(freq[0].key).compareTo(strength(other.freq[0].key))
//        if (strengthGroup1 != 0) return strengthGroup1
//
//        if (freq.size > 1 && other.freq.size > 1) {
//            val countGroup2 = freq[1].value.compareTo(other.freq[1].value)
//            if (countGroup2 != 0) return countGroup2
//            val strengthGroup2 = strength(freq[1].key).compareTo(strength(other.freq[1].key))
//            if (strengthGroup2 != 0) return strengthGroup2
//        }
//
//        if (freq.size > 2 && other.freq.size > 2) {
//            val countGroup3 = freq[2].value.compareTo(other.freq[2].value)
//            if (countGroup3 != 0) return countGroup3
//            val strengthGroup3 = strength(freq[2].key).compareTo(strength(other.freq[2].key))
//            if (strengthGroup3 != 0) return strengthGroup3
//        }
//
//        if (freq.size > 3 && other.freq.size > 3) {
//            val countGroup4 = freq[3].value.compareTo(other.freq[3].value)
//            if (countGroup4 != 0) return countGroup4
//            val strengthGroup4 = strength(freq[3].key).compareTo(strength(other.freq[3].key))
//            if (strengthGroup4 != 0) return strengthGroup4
//        }
//
//        if (freq.size > 4 && other.freq.size > 4) {
//            val countGroup4 = freq[4].value.compareTo(other.freq[4].value)
//            if (countGroup4 != 0) return countGroup4
//            val strengthGroup4 = strength(freq[4].key).compareTo(strength(other.freq[4].key))
//            if (strengthGroup4 != 0) return strengthGroup4
//        }

        throw RuntimeException("Should not happen, comparing $this with $other")
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
