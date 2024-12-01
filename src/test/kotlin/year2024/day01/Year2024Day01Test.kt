package year2024.day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day01Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(11)
        assertThat(part1(INPUT)).isEqualTo(2164381)

        assertThat(part2(SAMPLE)).isEqualTo(31)
        assertThat(part2(INPUT)).isEqualTo(20719933)
    }
}
