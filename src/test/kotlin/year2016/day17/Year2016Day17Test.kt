package year2016.day17

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day17Test {

    @Test
    fun bfsSamples() {
        assertThat(AOC2016D17("").bfs(salt = "ihgpwlah", from = 1 to 1, to = 4 to 4)).isEqualTo("DDRRRD")
        assertThat(AOC2016D17("").bfs(salt = "kglvqrro", from = 1 to 1, to = 4 to 4)).isEqualTo("DDUDRLRRUDRD")
        assertThat(AOC2016D17("").bfs(salt = "ulqzkmiv", from = 1 to 1, to = 4 to 4)).isEqualTo("DRURDRUDDLLDLUURRDULRLDUUDDDRR")
    }

    @Test
    fun bfs2Samples() {
        assertThat(AOC2016D17("").bfs2(salt = "ihgpwlah", from = 1 to 1, to = 4 to 4)).isEqualTo(370)
        assertThat(AOC2016D17("").bfs2(salt = "kglvqrro", from = 1 to 1, to = 4 to 4)).isEqualTo(492)
        assertThat(AOC2016D17("").bfs2(salt = "ulqzkmiv", from = 1 to 1, to = 4 to 4)).isEqualTo(830)
    }

    @Test
    fun part1() {
        assertThat(AOC2016D17(SAMPLE).solve1()).isEqualTo("DDRRRD")
        assertThat(AOC2016D17(INPUT).solve1()).isEqualTo("DDURRLRRDD")
    }

    @Test
    fun part2() {
        assertThat(AOC2016D17(SAMPLE).solve2()).isEqualTo(370)
        assertThat(AOC2016D17(INPUT).solve2()).isEqualTo(436)
    }
}
