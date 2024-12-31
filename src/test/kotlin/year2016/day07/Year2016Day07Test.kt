package year2016.day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

class Year2016Day07Test {

    @Test
    fun part1() {
        assertThat(AOC2016D07(SAMPLE).solve1()).isEqualTo(2)
        assertThat(AOC2016D07(INPUT).solve1()).isEqualTo(105)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D07(SAMPLE).solve2()).isEqualTo(0)
        assertThat(AOC2016D07(INPUT).solve2()).isEqualTo(258)
    }
}
