package year2023.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day05Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(35)
        assertThat(part1(INPUT)).isEqualTo(240320250)
    }

    @Test
    fun part2() {
        assertThat(part2(SAMPLE)).isEqualTo(46)
        // assertThat(part2(INPUT)).isEqualTo(28580589) // too slow for unit test
    }

    @Test
    fun mappers() {
        val mapper = Mapper("seed-to-soil", listOf("50 98 2", "52 50 48"))
        assertThat(mapper.map(1)).isEqualTo(1)
        assertThat(mapper.map(49)).isEqualTo(49)
        assertThat(mapper.map(50)).isEqualTo(52)
        assertThat(mapper.map(51)).isEqualTo(53)
        assertThat(mapper.map(97)).isEqualTo(99)
        assertThat(mapper.map(98)).isEqualTo(50)
        assertThat(mapper.map(99)).isEqualTo(51)
    }
}
