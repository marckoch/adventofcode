package year2019.day05

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2019Day05Test {

    @Test
    fun part1() {
        assertThat(execute("3,0,4,0,99", 1)).contains(1)

        assertThat(execute("1002,4,3,4,33", 1)).isEmpty()

        assertThat(execute(PROGRAMM, 1).last()).isEqualTo(5044655)
    }

    @Test
    fun part2_SAMPLE2_7() {
        val result = execute2(SAMPLE2, 7)
        assertThat(result).hasSize(1)
        assertThat(result).contains(999)
    }

    @Test
    fun part2_SAMPLE2_8() {
        val result = execute2(SAMPLE2, 8)
        assertThat(result).hasSize(1)
        assertThat(result).contains(1000)
    }

    @Test
    fun part2_SAMPLE2_9() {
        val result = execute2(SAMPLE2, 9)
        assertThat(result).hasSize(1)
        assertThat(result).contains(1001)
    }

    @Test
    fun part2_PROGRAMM_5() {
        val result = execute2(PROGRAMM, 5)
        assertThat(result).hasSize(1);
        assertThat(result).contains(7408802)
    }
}