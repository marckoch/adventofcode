package year2015.day21

import adventofcode.Problem
import util.parser.readChunks

fun main() {
    AOC2015D21(INPUT).run()
}

class AOC2015D21(input: String) : Problem(input) {
    private var weapons: List<Equipment>
    private var armors: List<Equipment>
    private var rings: List<Equipment>

    private val NULL = Equipment("null", 0, 0, 0)

    init {
        val (w, a, r) = readChunks(EQUIPMENT)
        weapons = w.drop(1).map { Equipment.from(it) }
        armors = a.drop(1).map { Equipment.from(it) }.toMutableList().also { it.add(NULL) }
        rings = r.drop(1).map { Equipment.from(it) }.toMutableList().also { it.add(NULL) }
    }

    override fun solve1(): Int {
        val inventories = buildInventoryCombinations()

        return inventories.filter { playerInventory ->
            val game = createGame(playerInventory)
            game.fightTilDeath() == Game.Winner.PLAYER
        }.minOf { list -> list.sumOf { it.cost } }
    }

    override fun solve2(): Int {
        val inventories = buildInventoryCombinations()

        return inventories.filter { playerInventory ->
            val game = createGame(playerInventory)
            game.fightTilDeath() == Game.Winner.BOSS
        }.maxOf { list -> list.sumOf { it.cost } }
    }

    private fun buildInventoryCombinations(): List<Inventory> {
        // combine weapon with armor and two rings
        return weapons
            .flatMap { w -> armors.map { w to it } }
            .flatMap { (w, a) ->
                rings
                    .flatMap { r1 -> rings.map { r1 to it } }
                    .filter { (r1, r2) -> r1 != r2 } // ring can only be chosen once
                    .map { (r1, r2) -> listOf(w, a, r1, r2).filter { it != NULL } }
            }
    }

    private fun createGame(playerInventory: Inventory) : Game {
        val playerDamage = playerInventory.sumOf { it.damage }
        val playerArmor = playerInventory.sumOf { it.armor }

        val player = Stats(100, playerDamage, playerArmor)
        val boss = Stats(104, 8, 1)

        return Game(player, boss)
    }
}

typealias Inventory = List<Equipment>

data class Equipment(val name: String, val cost: Int, val damage: Int, val armor: Int) {
    companion object {
        fun from(s: String): Equipment {
            val tokens = s.split(Regex("\\s+")).map { it.trim() }
            return when (tokens.size) {
                4 -> Equipment(tokens[0], tokens[1].toInt(), tokens[2].toInt(), tokens[3].toInt())
                5 -> Equipment(tokens[0] + " " + tokens[1], tokens[2].toInt(), tokens[3].toInt(), tokens[4].toInt())
                else -> error("Invalid equipment tokens: $tokens")
            }
        }
    }
}

class Game(private var player: Stats, private var boss: Stats) {

    enum class Winner { PLAYER, BOSS, NOONE }

    fun fightTilDeath(): Winner {
        var winner = Winner.NOONE
        while (winner == Winner.NOONE) {
            winner = fight()
        }
        // println("Game over! winner: $winner")
        return winner
    }

    private fun fight(): Winner {
        val playerDealsDamage = (player.damage - boss.armor).coerceAtLeast(1)
        boss = boss.copy(hitpoints = (boss.hitpoints - playerDealsDamage).coerceAtLeast(0))
//        println("The player deals $playerDealsDamage damage; the boss goes down to ${boss.hitpoints} hit points.")
        if (boss.hitpoints == 0) {
            return Winner.PLAYER
        }

        val bossDealsDamage = (boss.damage - player.armor).coerceAtLeast(1)
        player = player.copy(hitpoints = (player.hitpoints - bossDealsDamage).coerceAtLeast(0))
//        println("The boss deals $bossDealsDamage damage; the player goes down to ${player.hitpoints} hit points.")
        if (player.hitpoints == 0) {
            return Winner.BOSS
        }

        return Winner.NOONE
    }
}

data class Stats(val hitpoints: Int, val damage: Int, val armor: Int)