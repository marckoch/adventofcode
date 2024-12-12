package year2024.day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day12Test {

    @Test
    fun part1() {
        assertThat(AOC2024D12(SAMPLE1).solve()).isEqualTo(140)
        assertThat(AOC2024D12(SAMPLE2).solve()).isEqualTo(772)
        assertThat(AOC2024D12(SAMPLE3).solve()).isEqualTo(1930)
        assertThat(AOC2024D12(INPUT).solve()).isEqualTo(1465968)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D12(SAMPLE1).solve2()).isEqualTo(80)
        assertThat(AOC2024D12(SAMPLE2).solve2()).isEqualTo(436)
        assertThat(AOC2024D12(SAMPLE3).solve2()).isEqualTo(1206)
        assertThat(AOC2024D12(INPUT).solve2()).isEqualTo(897702)
    }
}
