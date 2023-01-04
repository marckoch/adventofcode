package year2020.day22

fun main() {
    part1(SAMPLE)
    part1(INPUT)

//    part2(SAMPLE)
//    part2(INPUT)
}

fun part1(input: String) {
    val game = parse(input)

    while (!game.isFinished()) {
        game.playCrabCombat()
    }

    game.printFinalResult()

    println("part1: ${game.finalScore()}")
}

fun part2(input: String) {
    val game = parse(input)

    while (!game.isFinished()) {
        game.playRecursiveCombat()
    }

    game.printFinalResult()

    println("part2: ${game.finalScore()}")
}

fun parse(input: String): Game {
    val (player1, player2) = readLineChunks(input).map { readPlayer(it) }
    return Game(1, player1, player2)
}

fun readPlayer(lines: List<String>): Player {
    val (id) = """Player (\d):""".toRegex().find(lines.first())!!.destructured
    val cards = lines.drop(1).map { it.toInt() }
    return Player(id.toInt(), cards.toMutableList())
}

class Game(private val id: Int, private val player1: Player, private val player2: Player) {
    private var round = 0
    private val memory = mutableListOf<String>()

    private fun playTopCards(): Pair<Int, Int> {
        println()
        println("-- Round ${++round} (Game $id) --")
        println("Player 1's deck: ${player1.cards}")
        println("Player 2's deck: ${player2.cards}")

        val player1Plays = player1.removeTopCard()
        val player2Plays = player2.removeTopCard()

        println("Player 1 plays: $player1Plays")
        println("Player 2 plays: $player2Plays")
        return player1Plays to player2Plays
    }

    fun playCrabCombat() {
        val (player1Plays, player2Plays) = playTopCards()

        if (player1Plays > player2Plays) {
            println("Player 1 wins the round!")
            player1.addCardAtBottom(player1Plays)
            player1.addCardAtBottom(player2Plays)
        } else {
            println("Player 2 wins the round!")
            player2.addCardAtBottom(player2Plays)
            player2.addCardAtBottom(player1Plays)
        }
    }

    fun playRecursiveCombat() {
        if (memory.contains(state())) {
            println("player 1 wins because we have seen this before!")
            player2.cards.clear()
            return
        } else {
            memory.add(state())
        }

        val (player1Plays, player2Plays) = playTopCards()

        if (player1.cards.size >= player1Plays &&
            player2.cards.size >= player2Plays
        ) {
            // we are doing recursion and start a sub game!
            println("Playing a sub-game to determine the winner...")

            val subgame = Game(
                2, Player(1, player1.cards.toMutableList().subList(0, player1Plays)),
                Player(2, player2.cards.toMutableList().subList(0, player2Plays))
            )
            while (!subgame.isFinished()) {
                subgame.playRecursiveCombat()
            }
            subgame.printFinalResult()

            if (subgame.getWinner().id == player1.id) {
                player1.addCardAtBottom(player1Plays)
                player1.addCardAtBottom(player2Plays)
            } else {
                player2.addCardAtBottom(player2Plays)
                player2.addCardAtBottom(player1Plays)
            }
        } else {
            if (player1Plays > player2Plays) {
                println("Player 1 wins round $round!")
                player1.addCardAtBottom(player1Plays)
                player1.addCardAtBottom(player2Plays)
            } else {
                println("Player 2 wins round $round!")
                player2.addCardAtBottom(player2Plays)
                player2.addCardAtBottom(player1Plays)
            }
        }
    }

    fun isFinished(): Boolean {
        return player1.cards.isEmpty() || player2.cards.isEmpty()
    }

    private fun getWinner(): Player {
        return if (player1.cards.isEmpty()) return player2 else player1
    }

    fun printFinalResult() {
        println()
        println("== Post-game results ==")
        println("Player 1's deck: ${player1.cards}")
        println("Player 2's deck: ${player2.cards}")
    }

    fun finalScore(): Int {
        return if (player1.cards.isEmpty()) {
            sumCards(player2.cards)
        } else
            sumCards(player1.cards)
    }

    private fun sumCards(cards: List<Int>): Int {
        return cards.reversed().withIndex().fold(0) { acc, (index, card) -> acc + ((index + 1) * card) }
    }

    private fun state(): String {
        return player1.cards.toString() + player2.cards.toString()
    }
}

class Player(val id: Int, var cards: MutableList<Int>) {
    fun removeTopCard(): Int {
        return cards.removeAt(0)
    }

    fun addCardAtBottom(card: Int) {
        cards.add(card)
    }
}

fun readLineChunks(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf<MutableList<String>>()) { list, line ->
        if (line.isEmpty()) {
            list.add(mutableListOf())
        } else {
            if (list.isEmpty()) {
                list.add(mutableListOf())
            }
            list.last().add(line.trim())
        }
        list
    }
}