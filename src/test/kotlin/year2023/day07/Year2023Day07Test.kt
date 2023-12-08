package year2023.day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day07Test {

    @Test
    fun `part 1 samples`() {
        assertThat(part1(SAMPLE)).isEqualTo(6440)
    }

    @Test
    fun `part 1`() {
        assertThat(part1(INPUT)).isEqualTo(252656917)
    }

}
