package year2023.day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day03Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(4361)
        assertThat(part1(INPUT)).isEqualTo(527446)
    }

    @Test
    fun part2() {
        assertThat(part2(SAMPLE)).isEqualTo(467835)
        assertThat(part2(INPUT)).isEqualTo(73201705)
    }
}
