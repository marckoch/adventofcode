package year2023.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day04Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(13)
        assertThat(part1(INPUT)).isEqualTo(23441)
    }

    @Test
    fun part2() {
        assertThat(part2(SAMPLE)).isEqualTo(30)
        assertThat(part2(INPUT)).isEqualTo(5923918)
    }
}
