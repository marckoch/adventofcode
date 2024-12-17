package year2024.day17

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day17Test {

    @Test
    fun part1() {
        assertThat(AOC2024D17(SAMPLE).solve()).isEqualTo("4,6,3,5,6,3,5,2,1,0")
        assertThat(AOC2024D17(SAMPLE2).solve()).isEqualTo("0,3,5,4,3,0")
        assertThat(AOC2024D17(INPUT).solve()).isEqualTo("7,0,7,3,4,1,3,0,1")
    }

    @Test
    fun part2() {
    }

}
