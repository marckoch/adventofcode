package year2023.day06

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day06Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(288)
        assertThat(part1(INPUT)).isEqualTo(840336)
    }

    @Test
    fun part2() {
        assertThat(part2(SAMPLE)).isEqualTo(71503)
        assertThat(part2(INPUT)).isEqualTo(41382569)
    }
}
