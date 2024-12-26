package year2015.day11

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day11Test {

    @Test
    fun part1() {
        assertThat(AOC2015D11("").findNextPassword("abcdefgh")).isEqualTo("abcdffaa")
        assertThat(AOC2015D11("").findNextPassword("ghijklmn")).isEqualTo("ghjaabcc")
        assertThat(AOC2015D11("hepxcrrq").solve1()).isEqualTo("hepxxyzz")
    }

    @Test
    fun part2() {
        assertThat(AOC2015D11("hepxxyzz").solve2()).isEqualTo("heqaabcc")
    }
}
