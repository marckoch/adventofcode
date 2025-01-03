package year2016.day19

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day19Test {

    @Test
    fun part1() {
        assertThat(AOC2016D19(SAMPLE).solve1()).isEqualTo(3)
        assertThat(AOC2016D19(INPUT).solve1()).isEqualTo(1842613)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D19(SAMPLE).solve2()).isEqualTo(2)
//        assertThat(AOC2016D19(INPUT).solve2()).isEqualTo(1424135) // SLOW !!
    }

    @Test
    fun other() {
        assertThat(AOC2016D19(INPUT).partOne()).isEqualTo(1842613)
        assertThat(AOC2016D19(INPUT).partTwo()).isEqualTo(1424135)
    }
}
