package year2024.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day04Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(4)
        assertThat(part1(SAMPLE2)).isEqualTo(18)
        assertThat(part1(INPUT)).isEqualTo(2545)

        assertThat(part2(SAMPLE2)).isEqualTo(9)
        assertThat(part2(INPUT)).isEqualTo(1886)
    }
}
