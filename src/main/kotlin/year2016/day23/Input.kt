package year2016.day23

val SAMPLE = """
cpy 2 a
tgl a
tgl a
tgl a
cpy 1 a
dec a
dec a
""".trimIndent()

val INPUT = """
cpy a b
dec b
cpy a d
cpy 0 a
cpy b c
inc a
dec c
jnz c -2
dec d
jnz d -5
dec b
cpy b c
cpy c d
dec d
inc c
jnz d -2
tgl c
cpy -16 c
jnz 1 c
cpy 87 c
jnz 74 d
inc a
inc d
jnz d -2
inc c
jnz c -5
""".trimIndent()