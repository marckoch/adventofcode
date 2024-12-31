package year2016.day08

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

class Year2016Day08Test {

    @Test
    fun part1() {
        assertThat(AOC2016D08(INPUT).solve1()).isEqualTo(119)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D08(INPUT).solve2()).isEqualTo("ZFHFSFOGPO")
    }
}
