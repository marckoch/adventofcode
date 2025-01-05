package year2016.day23

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day23Test {

    @Test
    fun part1() {
        assertThat(AOC2016D23(SAMPLE).solve1()).isEqualTo(3)
        assertThat(AOC2016D23(INPUT).solve1()).isEqualTo(11478)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D23(SAMPLE).solve2()).isEqualTo(3)
        assertThat(AOC2016D23(INPUT).solve2()).isEqualTo(479008038)
    }
}
