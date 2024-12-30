package year2016.day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day02Test {

    @Test
    fun part1() {
        assertThat(AOC2016D02(SAMPLE).solve1()).isEqualTo("1985")
        assertThat(AOC2016D02(INPUT).solve1()).isEqualTo("36629")
    }

    @Test
    fun part2() {
        assertThat(AOC2016D02(SAMPLE).solve2()).isEqualTo("5DB3")
        assertThat(AOC2016D02(INPUT).solve2()).isEqualTo("99C3D")
    }
}
