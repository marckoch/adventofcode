package year2015.day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day12Test {

    @Test
    fun part1() {
        assertThat(AOC2015D12("[1,2,3]").solve1()).isEqualTo(6)
        assertThat(AOC2015D12("""{"a":2,"b":4}""").solve1()).isEqualTo(6)
        assertThat(AOC2015D12("""[[[3]]]""").solve1()).isEqualTo(3)
        assertThat(AOC2015D12("""{"a":{"b":4},"c":-1}""").solve1()).isEqualTo(3)
        assertThat(AOC2015D12("""{"a":[-1,1]}""").solve1()).isEqualTo(0)
        assertThat(AOC2015D12("""[-1,{"a":1}]""").solve1()).isEqualTo(0)

        assertThat(AOC2015D12(INPUT).solve1()).isEqualTo(111754)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D12("[1,2,3]").solve2()).isEqualTo(6)
        assertThat(AOC2015D12("""[1,{"c":"red","b":2},3]""").solve2()).isEqualTo(4)
        assertThat(AOC2015D12("""{"d":"red","e":[1,2,3,4],"f":5}""").solve2()).isEqualTo(0)
        assertThat(AOC2015D12("""[1,"red",5]""").solve2()).isEqualTo(6)

        assertThat(AOC2015D12(INPUT).solve2()).isEqualTo(65402)
    }
}
