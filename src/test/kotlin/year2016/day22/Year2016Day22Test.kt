package year2016.day22

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day22Test {

    @Test
    fun part1() {
        assertThat(AOC2016D22(SAMPLE).solve1()).isEqualTo(7)
        assertThat(AOC2016D22(INPUT).solve1()).isEqualTo(937)
    }

    @Test
    fun part2() {
//        assertThat(AOC2016D21(SAMPLE).solve2()).isEqualTo("abcde")
//        assertThat(AOC2016D21(INPUT).solve2()).isEqualTo("egfbcadh")
    }
}
