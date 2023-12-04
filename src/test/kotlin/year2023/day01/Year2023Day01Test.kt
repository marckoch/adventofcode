package year2023.day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day01Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(142)
        assertThat(part1(INPUT)).isEqualTo(54390)
    }

    @Test
    fun part2() {
        assertThat(part2(SAMPLE2)).isEqualTo(281)
        assertThat(part2(INPUT)).isEqualTo(54277)
    }
}
