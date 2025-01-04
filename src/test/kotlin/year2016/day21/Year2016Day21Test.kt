package year2016.day21

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day21Test {

    @Test
    fun instructions() {
        assertThat(SwapPosition(4, 0).modify("abcde")).isEqualTo("ebcda")
        assertThat(SwapLetter('d', 'b').modify("ebcda")).isEqualTo("edcba")
        assertThat(Reverse(0, 4).modify("edcba")).isEqualTo("abcde")
        assertThat(RotateLeft(1).modify("abcde")).isEqualTo("bcdea")
        assertThat(RotateLeft(0).modify("bdcefhga")).isEqualTo("bdcefhga")
        assertThat(RotateRight(1).modify("abcde")).isEqualTo("eabcd")
        assertThat(RotateRight(0).modify("bdcefhga")).isEqualTo("bdcefhga")
        assertThat(MovePosition(1, 4).modify("bcdea")).isEqualTo("bdeac")
        assertThat(MovePosition(3, 0).modify("bdeac")).isEqualTo("abdec")
        assertThat(RotatePosition('b').modify("abdec")).isEqualTo("ecabd")
        assertThat(RotatePosition('d').modify("ecabd")).isEqualTo("decab")
        assertThat(RotatePosition('g').modify("bdcefhga")).isEqualTo("bdcefhga")
    }

    @Test
    fun part1() {
        assertThat(AOC2016D21(SAMPLE).solve1()).isEqualTo("decab")
        assertThat(AOC2016D21(INPUT).solve1()).isEqualTo("fdhbcgea")
    }

    @Test
    fun part2() {
        assertThat(AOC2016D21(SAMPLE).solve2()).isEqualTo("abcde")
        assertThat(AOC2016D21(INPUT).solve2()).isEqualTo("egfbcadh")
    }
}
