package year2015.day22

import adventofcode.Problem

fun main() {
    AOC2015D22(INPUT).run()
}

class AOC2015D22(input: String) : Problem(input) {
    override fun solve1(): Int {
        val player = PlayerState(50, 500, emptyList())
        val boss = BossState(71, 10)

        return play(1, Turn.Player, 0, player, boss)
            .min()
    }

    override fun solve2(): Any {
        val player = PlayerState(50, 500, emptyList())
        val boss = BossState(71, 10)

        return play(2, Turn.Player, 0, player, boss)
            .min()
    }
}

data class Spell(
    val name: String,
    val cost: Int,
    val damage: Int,
    val heal: Int,
    val armor: Int,
    val mana: Int,
    var duration: Int
)
enum class Turn { Player, Boss }
data class PlayerState(val health: Int, val mana: Int, val activeSpells: List<Spell>)
data class BossState(val hp: Int, val damage: Int)

val allSpells = listOf(
    Spell("Magic Missile", 53, 4, 0, 0, 0, 1),
    Spell("Drain", 73, 2, 2, 0, 0, 1),
    Spell("Shield", 113, 0, 0, 7, 0, 6),
    Spell("Poison", 173, 3, 0, 0, 0, 6),
    Spell("Recharge", 229, 0, 0, 0, 101, 5)
)

fun play(
    part: Int,
    turn: Turn,
    spent: Int,
    playerState: PlayerState,
    bossState: BossState
): Sequence<Int> {
    val (playerHp, playerMana, activeSpells) = playerState
    val (bossHp, bossDamage) = bossState

    // Part 2, check if I die before any effects play out
    if (part == 2 && turn == Turn.Player && playerHp == 1) return sequenceOf(Int.MAX_VALUE)

    // Apply effects
    val updatedMana = activeSpells.sumOf { it.mana } + playerMana
    val damage = activeSpells.sumOf { it.damage }
    val armor = activeSpells.sumOf { it.armor }

    // Does the boss die from effects?
    val updatedBossHp = bossHp - damage
    if (updatedBossHp <= 0) return sequenceOf(spent)

    // Decrement duration of all effects and groom expired ones
    val updatedSpells = activeSpells
        .map { it.copy(duration = it.duration - 1) }
        .filter { it.duration > 0 }

    if (turn == Turn.Player) {
        // Part 2, I lose 1 HP on my turn
        val newHp = if (part == 2) playerHp - 1 else playerHp

        // What spells can I afford and don't already have running?
        val buyableSpells = allSpells.filter { spell ->
            spell.cost <= updatedMana && updatedSpells.none { it.name == spell.name }
        }

        if (buyableSpells.isEmpty()) return sequenceOf(Int.MAX_VALUE)

        // Play out the rest of the game with each possible purchase
        return sequence {
            for (spell in buyableSpells) {
                val newSpent = spent + spell.cost
                val newMana = updatedMana - spell.cost
                val (extraDamage, heal, newSpells) = if (spell.duration == 1)
                    Triple(spell.damage, spell.heal, updatedSpells)
                else
                    Triple(0, 0, updatedSpells + spell.copy())

                val newBossHp = updatedBossHp - extraDamage
                if (newBossHp <= 0) {
                    yield(newSpent)
                } else {
                    yieldAll(
                        play(
                            part,
                            Turn.Boss,
                            newSpent,
                            PlayerState(newHp + heal, newMana, newSpells),
                            BossState(newBossHp, bossDamage)
                        )
                    )
                }
            }
        }
    } else {
        // Boss's turn
        val damageTaken = (bossDamage - armor).coerceAtLeast(1)
        val newHp = playerHp - damageTaken
        if (newHp <= 0) return sequenceOf(Int.MAX_VALUE)
        val player = PlayerState(newHp, updatedMana, updatedSpells)
        val boss = BossState(updatedBossHp, bossDamage)
        return play(part, Turn.Player, spent, player, boss)
    }
}
