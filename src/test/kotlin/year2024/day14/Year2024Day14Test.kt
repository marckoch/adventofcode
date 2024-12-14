package year2024.day14

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day14Test {

    @Test
    fun part1() {
        assertThat(AOC2024D14(SAMPLE).solve(11, 7)).isEqualTo(12)
        assertThat(AOC2024D14(INPUT).solve(101, 103)).isEqualTo(225552000)
    }

}
