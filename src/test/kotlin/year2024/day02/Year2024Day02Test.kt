package year2024.day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day02Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(2)
        assertThat(part1(INPUT)).isEqualTo(680)

        assertThat(part2(SAMPLE)).isEqualTo(4)
        assertThat(part2(INPUT)).isEqualTo(710)
    }
}
