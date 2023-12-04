package year2023.day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day02Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE, 12, 13, 14)).isEqualTo(8)
        assertThat(part1(INPUT, 12, 13, 14)).isEqualTo(2563)
    }

    @Test
    fun part2() {
        assertThat(part2(SAMPLE)).isEqualTo(2286)
        assertThat(part2(INPUT)).isEqualTo(70768)
    }
}
