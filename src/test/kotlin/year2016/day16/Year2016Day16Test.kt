package year2016.day16

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day16Test {

    @Test
    fun dragon() {
        assertThat(dragon("1")).isEqualTo("100")
        assertThat(dragon("0")).isEqualTo("001")
        assertThat(dragon("11111")).isEqualTo("11111000000")
        assertThat(dragon("111100001010")).isEqualTo("1111000010100101011110000")
    }

    @Test
    fun part1() {
        assertThat(AOC2016D16(SAMPLE).solve1()).isEqualTo("01100")
        assertThat(AOC2016D16(INPUT).solve1()).isEqualTo("11111000111110000")
    }

    @Test
    fun part2() {
        assertThat(AOC2016D16(SAMPLE).solve2()).isEqualTo("10111110011110111")
        assertThat(AOC2016D16(INPUT).solve2()).isEqualTo("10111100110110100")
    }
}
