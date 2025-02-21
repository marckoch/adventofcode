package year2016.day21

val SAMPLE = """
abcde
decab
swap position 4 with position 0
swap letter d with letter b
reverse positions 0 through 4
rotate left 1 step
move position 1 to position 4
move position 3 to position 0
rotate based on position of letter b
rotate based on position of letter d
""".trimIndent()

val INPUT = """
abcdefgh
fbgdceah
swap position 7 with position 1
swap letter e with letter d
swap position 7 with position 6
move position 4 to position 0
move position 1 to position 4
move position 6 to position 5
rotate right 1 step
swap letter e with letter b
reverse positions 3 through 7
swap position 2 with position 6
reverse positions 2 through 4
reverse positions 1 through 4
reverse positions 5 through 7
rotate left 2 steps
swap letter g with letter f
rotate based on position of letter a
swap letter b with letter h
swap position 0 with position 3
move position 4 to position 7
rotate based on position of letter g
swap letter f with letter e
move position 1 to position 5
swap letter d with letter e
move position 5 to position 2
move position 6 to position 5
rotate right 6 steps
rotate left 4 steps
reverse positions 0 through 3
swap letter g with letter c
swap letter f with letter e
reverse positions 6 through 7
move position 6 to position 1
rotate left 2 steps
rotate left 5 steps
swap position 3 with position 6
reverse positions 1 through 5
rotate right 6 steps
swap letter a with letter b
reverse positions 3 through 4
rotate based on position of letter f
swap position 2 with position 6
reverse positions 5 through 6
swap letter h with letter e
reverse positions 0 through 4
rotate based on position of letter g
rotate based on position of letter d
rotate based on position of letter b
swap position 5 with position 1
rotate based on position of letter f
move position 1 to position 5
rotate right 0 steps
rotate based on position of letter e
move position 0 to position 1
swap position 7 with position 2
rotate left 3 steps
reverse positions 0 through 1
rotate right 7 steps
rotate right 5 steps
swap position 2 with position 0
swap letter g with letter a
rotate left 0 steps
rotate based on position of letter f
swap position 5 with position 1
rotate right 0 steps
rotate left 5 steps
swap letter e with letter a
swap position 5 with position 4
reverse positions 2 through 5
swap letter e with letter a
swap position 3 with position 7
reverse positions 0 through 2
swap letter a with letter b
swap position 7 with position 1
move position 1 to position 6
rotate right 1 step
reverse positions 2 through 6
rotate based on position of letter b
move position 1 to position 0
swap position 7 with position 3
move position 6 to position 5
rotate right 4 steps
reverse positions 2 through 7
reverse positions 3 through 4
reverse positions 4 through 5
rotate based on position of letter f
reverse positions 0 through 5
reverse positions 3 through 4
move position 1 to position 2
rotate left 4 steps
swap position 7 with position 6
rotate right 1 step
move position 5 to position 2
rotate right 5 steps
swap position 7 with position 4
swap letter a with letter e
rotate based on position of letter e
swap position 7 with position 1
swap position 7 with position 3
move position 7 to position 1
swap position 7 with position 4
""".trimIndent()