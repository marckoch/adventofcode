package year2016.day24

val SAMPLE = """
###########
#0.1.....2#
#.#######.#
#4.......3#
###########
""".trimIndent()

val INPUT = """
###################################################################################################################################################################################
#...............#.........#...#...#.......#.......#.#...#.........#...........#...............#.#.#.......#.......#.....#...........#...#...#.......#.......#...#....2#...........#
#.#.#.#.#.###.#.#####.#.#.#.###.#.#.#######.###.#.#.#.#.#.#.###.#.#.###.###.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#####.#.#.#.#.#.###.#.#.#.#####.###.#.#.#.#.#.#.#####.#.#.###.#######
#....1#...#.#...#.......#.......#...#.......#.....#...#.#...#...#.......#.............#.....#...#.#.........#...#.....#.....#.........#...#...#.....#...#.#...#.#.#...#.........#.#
#######.#.#.###.#.#######.#####.#.#.#.#.###.#.###.#.#####.#.#.#.#.#.#########.###.#.#.#.###.#.#.#.###.#.###.#.#.#.#.#.#######.#.###########.#.#.###.#.###.#.#.#.#.#.#.#.###.#.#.#.#
#...#.....#.#...#.......#.#...#.....#.....#.......#.#...#...........#.........#.....#.........#.#.#...#.....#.....#.......#...#.............#...#.#.#.....#...#.#...#.#.....#.....#
#.###.###.#.#.#.#####.#.#.#.#.#.#.#.#.###.###.#.#.#.#.#.###.#.###.#####.#.#####.###.#####.#.#.#.#.#.#.#####.#.###.#.###.#.###.#.#.###.#.#.#.#.#.#.###.###.#.###.#.###########.#.###
#...........#.......#...........#.#...#...#.........#.......#.....#...............#.....#...#.........#...#.#...#.....#...#...............#.#.....#.........#.........#.........#3#
#####.#####.#######.#.#######.#.#.###.#.#.#.#.#########.#####.#####.#######.#.###.#.#.#.#.###.#.#.#.#.#.#.###.#.#.#.#.#.#.###.#####.#######.###.###.#.#####.#.#.###.#.#.#######.#.#
#...........#.........#.........#...........#...#.....#.....#...................#.......#.........#.#.#.#.#.#...#.#.........#.#...#.....#...#.#.#.#.....#.#.#...#.#.....#.#.......#
#.###.#####.###.#.#.#.#.#.#.#####.###.#.#####.#.#.#.#####.###.###.###.#.#######.#.#.###.#.#.#######.#.#.#.#.#####.#.#.#.#.#.#.###.#######.###.###.#######.#.#.###.#####.#.###.###.#
#.....#...............#.#...#.....#...............#.......#.........#.#.#.........#.......#.....#...#.#.....#.....#.#...#.......#.#.#.#...#.....#.......#.#.#.#.#.#.#.#.......#...#
#.#.###.#.#.#.#.#####.#####.#.#.#.#.#.#####.#.#####.#.#.###.#####.#.#.#.#.###.#####.#.#.#.#.#.#.#.###.#####.#.#.###.#.#.###.#.#.#.#.#.#########.#######.#.#.###.#.#.#.#.#.#.###.#.#
#.#...#...#...#.............#.#.#.........#...#.........#.#.#...#...#...#.........#.#.....#.....#...........#.....#.#.....#.#...#.......#.#.......#.....#.#.#...#...#...#.#.......#
#.#.#.#.###.###.###.#####.#.#.#.#.#####.#.###.###.#.#.#.#.###.#.#.#.###.#.#.#####.#.#.#.###.#######.###.###.#.###.###.###.#.#.#.###.###.#.#.###.#.#.#.#.#.#.#####.#.###.#.#.#.#####
#...........#.......#.....#...#...#.......#.......#.........#...........#.#...#...#.#...#.........#.#.#.....#.......#.#...#...#.........#.....#.#.#.....#.........#.#...#.#...#...#
#.###.###.#.#.###.#.#.###.#####.#.#.###.#.###.###.#.###.###.#####.#.#.#.#.#.#.#.#.#.###.#.#######.#.#.###.#.#.###.#.#.#.#####.#.#.#####.#.#.#.#.#######.#.#.#.#.###.#.#.###.#.#.#.#
#.#0......#.#...............#.....#.#.#...#...#...................#...#.#...#...#.....#...#...#.#...#...........#.....#...#.....#.#.....#...#...#.....#...#.....#.......#...#.....#
#.###.#.#####.#####.#########.###.#.#.#######.#.#.#.#####.###.#.###.#.#.#.#.#.#.#.#.#.#.#.#.#.#.###.#.#.###.#.#.#######.#.#.###.#.#.#.#.#.#.#.#.#.###########.#.###.#.#.#.#.#.###.#
#.....#.............#.......#.#.....#.....#.#...#...#.#.#.....#.......#...#...#.#.......#...#.....#.....#...#.#.#...#...#.#.#.....#.#...#.....#.#...#.#.....#.#...#.......#...#...#
#.#.#.###.#.#.###.#.#.#.###.#.#.#####.#####.#.#.#####.#.#.#.#.###.#.#.###.#####.#.###.###.###.###.###.###.#.#.###.###.#.###.#.#.#.#####.#####.#.#.#.#.#.#.#.#.#.#.#.###.###.#.#.#.#
#.....#.#...#...#...#...#...#.....#.........#.......#...#...#.#.#...#.#.....#.....#.....#.....#...#...#.......#.................#.....#...#.....#...#.........#.#...#...#...#...#.#
###.#.#.###.#.#.#####.#.###.#.#.#.#.#.###.###.###.#.#.#.#.#.#.#.###.###.###.#.#.#.#.#.#####.#####.#.#####.###.#####.###########.###.#.#.###.#.#.#.#.#######.#.#.#.###.#.#.#.#.#####
#...#.#.......#.#.......#...#.....#.#.#...........#.#.#...#.........#...#...#.#...#.#.............#.............#...#.#...#...........#.#...#...#...#...#.#.#.....#.........#7#...#
#.#.#.#.#######.#.#.#.#.###.#.###.#.#.#.#######.###.#.#.#.#.#.###########.###.#.###.#.#.#.###.#########.#.#######.###.#.#.#.#.###.#.#.#.#.#.#.###.#.#.###.#.#######.#.#####.###.#.#
#...............#...#...#.....#...#.............#.#.#.#...#.#...#.#.......#.....#...#...#.#...#.#.........#.......#.#.....#...#.#.#.#...#.#.#.......#.#.#.....#.......#.#...#...#.#
###########.#.#.###.#####.#########.#####.#.###.#.#.#.#.#.#.###.#.#.#####.#####.#.#.###.###.###.#.#.#.#.#.#####.###.#####.#.###.#######.###.###.#.#.###.#.#.#.#.#.#.###.#.#.#.###.#
#.#...#.........#.#.......#...#.....#.#...#...#.......#.........#...#.#.........#.#.#.........#.#.#...#.....#...#.........#.#...#...#...#...#.....#...#.#.#.....#...#...#...#.....#
#.#.#.#.#.###.#.#.#######.#.#.#.###.#.#.#####.#######.#.#.#.#.#.###.#.###.#.#.#.#########.#.#.#.#.#.#.#.#.#.#.###.#.#.#.###.#.#.#.###.#.#.#.#.#.#.#.#.#.#.#####.###.#.#.#.#.#.###.#
#...#.#...........#.......#...........#.......#.......#...#...#.#.#.....#.#...........#.#.#.....#.#.........#.....#.....#...#.............#...#...#.....#.....#.....#...#.#.#.#...#
#.#.#####.#.#####.#.#####.#########.###.#.###.###.#.#.#.#.#.###.#.#.#.#.#.#######.#.#.#.###.###.#.#.#####.#####.###.###.#.#######.#########.#####.#####.###.#.#######.#.#.#.#####.#
#.#.......#...#...#.....#.#6#.......#...#.#.......#.....#.....#...........#...#...#.#.....#.#.#...#.........#.........#.#.....#...#.........#...#.#.#...#.....#.#.....#.#.........#
#.#.#.#.#.#.#.#.#######.#.#.###.#.#.#.#.#.#.#.#.#.#.#.#.#######.#.#.#.#.#.#.#.#.#.#.#.#####.#.###.#####.#####.#.#.###.#.###.#.#.#.#.#.#.#.###.#.#.#.###.#.###.#.#.#.###.###.#.#.#.#
#.#.#.#...#.#.....#.#.#.....#.................#...#.#.#.#...#...........#.#...#.......#...........#...#.........#.#.#.......#...#.......#...#.#...#.#...#...#.#.....#.......#...#.#
#.###.#.###.#.#.###.#.#.#.###.#########.#.#.#.#.#.#.#.#.#.#.#.#####.#.#.###.#.#.###.###.#.#.#.#.#.#.#####.#.#.#.###.#.#.#.#.#.#.#.#####.###.#.#.###.#####.###.#.#############.#.#.#
#.....#...#.#.........#.......#.....#...#.....#.#.......#...........#.......#.#.#.......#.....#.#.#.......#.....#...#...#...#.....#...#.....#.......#.........#.......#5#.......#.#
#.#####.#.#.#.###.###########.#.###.#.#####.#####.###.#####.#################.#.#.#.#.#.###.#####.###.###.###.#.###.###.#.#####.#.#.#.###.#.#####.#.###.#.###.#.###.#.#.#.#.#.###.#
#.#.....#...#.........#.....#.#.......#...#...#4#...#...#.#.#...#.....#...#.#...#.........#...#.............#...#.#.#.....#.........#.#.....#...........#.....#.#...#...#.#.....#.#
#.#.#.#.#.#.#########.#.#.#.#.#.#.#####.#.###.#.###.###.#.#.#.#.#.#.#.###.#.#######.#####.#.#.###.#.#.###.#.###.#.#.#.#####.#.###.#.#.###.###.#####.#.#.#.#####.#.#.#.###.#.###.#.#
#.#...#.....#.......#.....#...#...#.............#.#.#.....#...#...#...#.....#.#.#...#.....#...#...#.#.#.#...#.#.........#.#.......#.......#...#...#.......#.....#.#.#.....#...#...#
###################################################################################################################################################################################
""".trimIndent()