package year2024.day16

val SAMPLE1 = """
###############
#.......#....E#
#.#.###.#.###.#
#.....#.#...#.#
#.###.#####.#.#
#.#.#.......#.#
#.#.#####.###.#
#...........#.#
###.#.#####.#.#
#...#.....#.#.#
#.#.#.###.#.#.#
#.....#...#.#.#
#.###.#.#.#.#.#
#S..#.....#...#
###############
""".trimIndent()

val SAMPLE2 = """
#################
#...#...#...#..E#
#.#.#.#.#.#.#.#.#
#.#.#.#...#...#.#
#.#.#.#.###.#.#.#
#...#.#.#.....#.#
#.#.#.#.#.#####.#
#.#...#.#.#.....#
#.#.#####.#.###.#
#.#.#.......#...#
#.#.###.#####.###
#.#.#...#.....#.#
#.#.#.#####.###.#
#.#.#.........#.#
#.#.#.#########.#
#S#.............#
#################
""".trimIndent()


val INPUT = """
#############################################################################################################################################
#...#.#.....#.......#.......#...#.........#...#...........#.....#.#.......#.......#.....#...#.......#...#.#...............#...#.....#...#..E#
#.#.#.#.#.#.#.#.###.#.#.#####.#.#.#.#####.#.#.#.###.#####.###.#.#.#.#.#.#.#.###.#.#.###.#.###.#####.#.#.#.#.#.#.#########.#.#.#.###.#.###.#.#
#.#...#...#...#...#.#.#.......#...#.....#...#.#.#.#.#.........#...#.#...#.......#.#...#.#.#...#.....#.#...#...#.#.#.....#.................#.#
#.#.#.###.#######.###.#################.#.#####.#.#.#.###########.#.###.#.#######.###.#.#.#.#.#.#.###.#.#####.#.#.#.#.#######.#.#.#####.###.#
#.#.#...#...#...#.....#...............#.#.#...#...#.#.#.........#.#.#...#.......#...#.#.#.#.#.......#.#.#.....#.#.#...................#.#...#
#.#.#.###.#.#.#.#######.#######.###.#.#.###.#.###.#.###.#######.###.#.#.###.###.###.#.#.#.#.#####.#.#.###.###.#.#.#.#######.#####.#.#.#.#.#.#
#.#...#.....#.#.........#.....#...#.#.#...#.#.....#.....#.....#.....#.......#...#.#...#.#.#...#.....#.............#.#.#...#.....#.#.#.......#
#.#.###.#####.#####.###.#.###.#####.#.###.#.#################.#.#######.#####.###.#####.#.###.###.#.#.#.###.#.#.#.#.#.#.#.#####.#.#.#.#.#.###
#.#.....#.........#.....#.#.#.......#...#...#.......................#.....#...#.......#.#...#.....#.#.......#.#.#.....#.#...#...#...#.#.#...#
#.#.#########.###.#.###.#.#.###########.#####.###.#################.###.#.#.###.#######.###.#####.#.###.#####.#.#######.###.#.#######.#####.#
#.#.#...#...#...#...#...#.#...#.......#...#...#.....#...#.........#...#.#.#.#...#.............#...#...#.....................#.......#.......#
#.#.#.#.#.#.#########.#.#.#.#.#.###.#####.###.#.###.#.#.#.#######.###.#.#.#.#.#.#.#############.#.###.#.#.###.#.#.#########.#.###.#.#######.#
#.#.#.....#...#.......#...#.#...#...#.....#...#.#.....#...#...#.....#.....#.#.#.#.#.....#.......#...#.#.............#...#...#.#.#.#.#.....#.#
#.#.#.#######.#.#####.#.#####.###.#.#.#####.###.#.#########.#.#.#######.###.#.#.#.###.#.#.#######.###.#.#.#######.#.#.###.###.#.#.#.###.#.#.#
#...#.......#...#.....#.....#...#.#.#.....#...#.#.#...#...#.#.#.........#...#.#.#.#...#.#.....#.#...#.#.#.........#...#...#.................#
#.#########.#####.#.#.#.###.#####.#######.###.#.###.#.#.#.#.#.#.#########.###.###.#.###.#####.#.#.#.#.#.#########.###.#.###.###.#.#######.###
#.#...#.....#...#.#.#...#.#.#...#.......#...........#...#...#.#...#.....#.#.....#.#.#.....#...#.#.#.......#.....#.......#...#.............#.#
#.#.#.#.#####.###.#.#####.#.#.#.#######.#######.#.###########.###.#.###.#.#####.#.#.#.#####.###.#.#######.###.#########.#.###.#####.#.###.#.#
#...#...#...#.....#.#.....#...#.......#.....#...#...........#...#...#.#.#.....#...#.#.#.....#...#.#.....#.#.............#.....#...#.#...#...#
#.#######.#.#.#####.#.#.#####.#.#####.#####.#.###.#########.###.#####.#.#####.#.###.#.#.#####.###.#.###.#.#.#.#.#############.#.#.#.#.#.#.#.#
#.#.......#.#.....#.#.#.#.....#...#.#.......#...#.#.......#...#.#.....#...#.#.#...#.#.#...#...#.......#.#...#.#.......#...#...#.#...#.#...#.#
###.#######.#######.#.#.#.#####.#.#.#######.###.#.#.#.###.###.#.#.###.###.#.#.###.#.#####.#.###.###.#.#.#############.###.#.###.###.#.###.#.#
#...#.....#.....#...#.#.#.....#.#.#...#...#...#.#.#.#...#.#...#...#.#...#.....#.....#.....#...#.....#.#.#...#...#.....#...#.#...#...#...#...#
#.#####.#######.#.#.#.#######.#.#.#.#.#.#.#.###.#.###.#.###.#.#.###.#.###.###.#######.#####.#.###.#.#.#.#.#.#.#.#.#####.###.#.###.#####.###.#
#.#...#.....#...#...#.......#.#.#.#.#.#.#...#...#...#.#...#.#...#...#.....#...........#.....#.#...#.#.#...#...#.#...#...#...#...#.......#...#
#.#.#.###.###.###.#.#.#####.#.#.#.#.#.#.#####.#.###.#.###.#.#.###.#.#######.###.#############.#.#####.#########.#.#.#.#.#.#####.#####.###.###
#.#.#...#...#...#.#.#.#...#...#.#.#.#.#...........#.#...#...#...#.#...#...#...#...#.....#...#.#...#...#.#.....#.#.#.#.#...#...#...#...#...#.#
#.#.###.#.#.###.#.#.###.#.#.#.#.#.###.###.###.#####.###.#######.#.#####.#.###.###.#.###.#.#.#.###.#.###.#.#.#.#.###.#.#######.###.#####.###.#
#.#.#...#.#...#.#.#...#.#.#.....#...#.....#...#.....#.#...#...#.#.#.....#.....#...#.#.#...#...#...#.#.....#.#.#...#...#.....#...#.......#...#
#.#.#.#####.#.#.#####.#.#.#.#######.#######.###.###.#.###.###.#.#.#.#.#########.#.#.#.#######.#.###.###.###.#####.#.#.#.###.###.#########.#.#
#...#.#.....#.#.....#...#.#.#.....#.......#.#...#.......#...#.....#.#.#.........#...#.......#.#...#...#...#.......#.#...#.......#...#.....#.#
#.###.###.#.###.###.#.#.#.#.#.#.#.#######.#.#.#######.#.###.#######.#.#.#########.#.#.#####.#.###.###.###.###.#####.#.#########.#.#.#####.#.#
#...#.#...#.#.......#.#.#.#.#.#.#.......#.#.#...#...#.#.#...........#.#.....#...#.#.#.#...#.....#...#.......#.......#.#...#.#...#.#.#.....#.#
#####.#.#####.#######.#.#.#.###.#########.#.#.#.#.#.#.#.#.###########.#.#.#.#.#.#.#.###.#.#########.###.###.#.###.#.#.#.#.#.#.###.#.#.#####.#
#...#.#.....#.....#.....#.#...#.........#.......#.#...#.#.#...#.....#.....#.#.#...#.#...#.#.......#...#...#.#...#.#.#.#.#.#.....#.#...#...#.#
#.#.#.#.###.#####.#######.###.#.#.#####.#####.###.###.###.#.#.###.#####.###.#.#####.#.###.#.#####.###.###.#.###.#.#.###.#.#####.#.#####.###.#
#.#...#...#...#...#.......#.#.#...#...#.....#...#...#.#...#.#.#...#.........#.....#.....#.......#...#.#...#.#...#.#.....#...#...#...#.#.....#
#.#########.#.#.###.#####.#.#.###.###.###.#.###.###.###.###.#.#.###.#####.#########.###.###########.#.#.#.#.#.###.#########.#.#####.#.#.#####
#.............#...#.....#...#...#...#...#.....#.........#...#.#.....#.........#...#.......#.......#.#.#...#.#...#...#.......#...............#
#.#.###.#########.###.#.###.###.###.###.#.###.###.#####.#.###.#.#######.#.###.#.#.#########.#####.#.#.#####.###.#####.#.#############.###.#.#
#.#...#.........#...#.#...#...#.#.......#...#...#.........#.#.#...#.....#.....#.#...........#.#...#.#.....#.#...#.......#.......#.....#.#...#
#####.#.#######.###.#.###.#####.###.###.###.###############.#.###.###.###.#####.#############.#.###.#####.#.#.###.###.###.#####.###.#.#.###.#
#.....#...#...#...#.#...#.....#.......#.#...#.........#.....#.......#...#.....................#.#.....#.#...#.#...#...#...#...#.#...#.....#.#
#.#######.#.#.###.#.###.#####.#######.###.###.###.###.#####.#.#####.###.#############.#####.###.#.#.#.#.#####.#.#.#.###.###.#.#.#.#######.#.#
#.#...#...#.#...#...#...#.#...#.....#.#...#...#...#...#.....#.....#.....#...#.........#...#.#...#.#.#...#.#...#.#.#...#.....#.#.#.....#...#.#
#.#.###.###.###.#####.###.#.#.#.#####.#.###.###.###.#.#.###.#####.#####.#.###.#.#####.#.#.#.#.###.#.###.#.#.###.#.#########.###.#.###.#.###.#
#.#...#...#...#.....#.....#.#...#.............#.#...#...#...#.#...#...#...#...#.#...#...#...#.#.#.#...#...#...#.#...........#...#...#.......#
#.#.#.###.###.#.#######.###.###.#.#########.#.#.#.#.#.###.#.#.#.###.#####.#.###.#.#.#########.#.#.###.#######.#.#############.#####.###.#####
#.#.#...#...#.#.........#...#...#.#.......#...#...#.#...#.#.#.#.#.....#...#...#...#.#.......#.#.....#...#...#.#.........#.....#.....#.......#
#.#.#.#####.#.#######.###.#######.#.#####.###.###.#.###.#.#.#.#.#####.#.#####.#####.#.#####.#.#.###.###.#.#.#.###########.#####.#####.#.###.#
#.#.............#...#.#...........#.#.....#...#...#...#.#...#.#.....#.#.#...#...#.#...#...#...#...#.#.#.#.#.#.#.......#...#...#.#.........#.#
#.###.#.#####.###.#.###.###.#######.#.#######.#.#####.#.#.###.#####.#.#.#.#####.#.###.###.#######.#.#.#.#.#.#.#.#####.#.###.#.#.#.###.#.#.#.#
#...#...#...#.#...#...#.#...#.......#...#...#.#.....#.#.#.........#.......#.....#.#...........#...#...#...#...#...#.#...#...#.#.#...#...#.#.#
#.#.#.###.#.###.###.#.#.#.###.#########.#.#.###.###.#.#.###########.#####.#.###.#.#.#.#######.#.#####.###########.#.#######.#.#.#######.#.#.#
#.#.............#.....#.#.....#.......#...#...#.#...#...#...#.....#.....#.#.#...#...#.....#...#.#.....#...#...............#.#.#.......#.#...#
#.#.#.#.###.#.###.#####.#.#.###.#####.#######.###.#.#####.#.#.###.#####.###.###.#.#######.#.###.#.#####.#.#.###########.#.###.#######.#.###.#
#.#.#...#...#.#.#.#.......#.#...#.......#...#.....#.#...#.#...#.#.#...#.#...#...#.......#.#.....#.......#.#...#.......#.#.....#.....#.......#
#.#.#.###.###.#.#.#####.###.#.###.#.###.#.#######.###.#.#.#####.#.#.#.#.#.###.###.#####.#.#############.#.#.#.#.#.###.#.#.#####.#.#####.#.#.#
#.#...#.......#.#.#...#.....#.#...#...#.#.........#...#...#.....#...#...#.#.......#...#.#...#...........#.#.#.#.#.#...#.#.#.................#
#.###.#########.#.#.#.#####.#.#.###.###.#.#########.###########.#.#####.#.#.#######.#.#.###.#.#######.###.###.#.#.#.###.###.#######.#.#.#####
#.......#.......#...#.....#.#.#.#...#...#...#...#...#...........#.....#.#.#.#.......#...#.#...#.....#...#.....#.#.#...#...#.................#
#.#.#.#.###.#.#######.###.###.#.#.###.#####.#.###.###.#########.#.#####.#.#.#.#.#######.#.#####.#.#.###.#########.#.#.###.#.###.#.#########.#
#.#...#.....#.......#...#.....#.#.....#.......#...#...#...........#.....#.#.#.#.#.......#.........#...#...........#.#.....#.#...#.......#...#
#.###.###.#.#####.#.#.#.#######.#######.#######.###.###.###########.#####.#.###.#####.###.#########.###########.###.#####.#.#.#######.#.#.###
#...#.#...#.#...#.#.#.#.......#...#.....#.....#.#...#...........#.......#.#...#.#...#.....#.......#.#...........#.......#.#.#...#...#.#.#.#.#
###.#.#.###.#.###.#.#####.#######.#######.###.#.#.#.#########.###.#######.###.#.#.#.#####.#.#####.#.#.###############.###.#.###.###.#.###.#.#
#.#...............#.#...#.......#.......#...#...#.#...#.....#.....#.#...#...#...#.#...#.#.#...#.#.#.#.#.....#.......#.#...#...#...#.#...#...#
#.#.#.###.#.###.###.#.#.#.###.#.#######.###.###.#.###.#.###.#.#####.#.#.###.#####.###.#.#.###.#.#.###.#.#.#.#####.#.#.#.###.#.#.#.#.###.###.#
#.....#...#.#...#.....#.#.#...#.......#.#...#.....#...#...#.....#...#.#...#.#.....#...#...#...#.#.#...#.........#.#...#...#.#...#...#.#.....#
#######.#####.#########.###.#########.#.#.###.#####.###.#.#####.#.###.###.#.#.#####.###.###.###.#.#.#####.#####.#.###.###.#.###.###.#.#######
#.....#.#.....#.....#.#.....#.....#...#...#...#.....#...#.....#...#...#...#.......#.#.....#.#.....#.#.....#...#.#...#...#...#.#.#...#...#...#
#.#.#.#.#.#####.###.#.###.#.#.###.#########.###.###.#.###.###.###.#.#######.###.###.#.#####.#.#####.#.#####.#.#.###.###.#####.#.#.#####.#.#.#
#...#...#.#...#.#.#.#.....#.#.#.#...#.....#...#.#...#...#.#...#...#.#.........#.#...#.#.....#...#...#.#...#.#.......#...#.....#.#.#...#...#.#
###.#.#.#.#.#.#.#.#.###.###.#.#.###.#.#.###.###.#.###.###.#.###.###.#.#.#.#.#.###.#####.#######.###.#.#.#.#.#.#########.#.#####.#.#.#.#####.#
#...........#.....#...#.....#...#...#.#.....#.....#.......#.#...#...#.#.#.#.#.#...#.....#.......#...#...#.#.#.........#.#.....#.#.#.#.......#
#.#.#.#.#########.###.#.#######.#.###.#######.###.#.###.###.###.#.###.#.###.#.#.#####.###.#######.###.###.#.#####.###.#.###.#.#.#.#.#.#####.#
#.#...#.....#.....#.#.#.#.....#.#.#...#.......#...#.#.#.#.#...#.#...#...#...#...#.....#...#.....#.#.#...#.#.#.......#.#...#.#.#.#...#.......#
#.#.#.#.#.#.###.#.#.#.###.###.#.#.#.###.###.#.#.###.#.#.#.###.#.###.#.#.#.#########.###.###.#.#.#.#.#.#.#.#.#####.#.#.#.#.###.#.#####.#.#####
#.#.......#.....#...#.......#.#.#...#...#.....#...#...#...#.#.......#.#...#.........#...#...#.#...#...#...#...#...#.#.#.#.....#.#.....#.....#
#.#.#.#.###.#######.#########.#.#####.#.#######.###.#####.#.#########.###########.###.#######.#####.###.#####.#.###.#.#.###.###.#.###.#.#.#.#
#.....#.....#.....#...#.....#.....#.#.#.............#.....#.......#.......#.......#.#.#.......#...#.#.#.....#.#.#...#.#...#.#...#...#.#.#.#.#
#.#.#.#.#####.###.#####.###.#####.#.#.#.#.###########.#####.#####.#####.#.#.#######.#.#.#######.#.#.#.#####.#.#.#####.#.###.#.#####.#.#.#.#.#
#...#.#.....#.#.#.#.....#.....#.....#.#.#...#.......#.....#.....#.....#.#.#...#...#.#.#.#.......#.#.#.....#.#...#.....#.#...#.....#...#...#.#
#.#.#.#.###.#.#.#.#.#########.#######.#####.#.#.#.#######.#.#.#.#####.###.#.#.#.#.#.#.#.#.#########.#.###.#.###.#.#####.#.#######.#.#.#####.#
#...#.#.....#.#...#.#.......#.......#.....#...#...#.........#.#.....#...#.#.#...#.#.#.#.#.........#...#.#.#...#.#.#.............#.#.#...#...#
#####.#.#####.#.###.#.#####.#######.#####.#####.###.#########.#.###.###.#.#.#####.#.#.#.#.#######.#####.#.###.#.#.###.###########.#.#.###.###
#...#.#.#...#.#.#.......#...#.#...#...........#...#.......#...#.#...#...#.#...#...#.#...#.#.....#.......#.#.#...#...#.#...........#.#...#.#.#
#.#.#.#.#.###.#.#########.###.#.#.#.#############.###.###.#.###.#.###.###.###.#.###.#####.#.#####.#.#.###.#.#######.###.###########.#.#.#.#.#
#.#...#.#.#...#.#.........#.#...#...#...........#.#...#...#.#...#.........#...#.#...#...#.#...#...#.#...#.#.#.......#...#...........#.#.#.#.#
#.###.#.#.#.###.#.#########.#.#######.#####.###.#.#.#.#.###.#######.#######.###.#.#.#.#.#.###.#.#####.#.#.#.#.#######.#.#.#####.#.#.#.#.#.#.#
#...#.....#.#.....#.....#.............#...#.#...#...#.#.#.#.........#.......#...#.#...#.#.#...#...#...#.....#...#...#.#...#...#...#...#.#...#
#.#.#.#.###.#######.###.#####.#####.###.#.#.#.###.#####.#.#.#######.#######.#.#########.#.#.#####.#.###########.#.#.#.#.#.#.#####.###.#.###.#
#.#.......#.........#.#...#...#.....#...#...#.....#.....#.#.#.....#.#.....#.#.......#...#.#.#.....#.#.........#...#.#.#.#.#...#.....#.#...#.#
#.#######.#.#########.###.#.#.#.#####.###.#######.#.#####.#.#.###.#.#.#.#.#.#######.#.###.#.#.###.#.#.#######.###.#.#.#.#.#.#.#.#####.#.###.#
#.......#.#.......#...#.#.....#.......#...#.....#.#.#.#.....#.#...#.....#.#.#.....#.#.......#...#.#.#.#.....#...#.#.#.#...#.#.#...#...#.....#
###.#####.#######.#.#.#.#####.#.###########.###.###.#.#.#####.###.#######.#.#.###.#.#######.###.###.#.#.#######.###.#.#.#.#.#.#.###.#####.#.#
#...#.....#.......#.#.....#...#.#...........#.#.#...#...#...#...#.........#.#...#.#.#...#...#...#...#.#.......#.....#.#.#.#.#...#...#...#.#.#
#.###.#####.#.#####.#####.#.###.#.###.#######.#.#.#######.#.###.###########.###.#.#.#.#.###.#.#.#.###.#######.#.#####.#.#.#####.#.#####.#.#.#
#...#.#.....#...#...#...#.#.#...#.....#.......#.#.#.......#.....#.#.......#...#.#.#...#...#.#.#.#...#.#.....#...#.....#.#.#...#.#.......#.#.#
###.#.#.###.###.#.#.#.#.###.#.#######.#####.#.#.#.#.#.#.#########.#.###.#####.###.#######.#.#.#.#.###.#.###.#####.#######.#.#.#####.#####.#.#
#.#.#.#.#...#...#.#...#...#.....#...#.#.....#.#.....#.#.#.....#.......#...........#.....#.#...#...#...#.#.#...#...#.....#.#.#.....#.#.....#.#
#.#.#.#.#.###.###########.#####.###.#.#.#####.#######.###.###.#.#################.###.###.###.#####.###.#.###.#.###.###.#.#.#####.#.#.#.###.#
#...#.#.#...#...#.....#...#...#...#.#.#...#.#.......#.#.....#.#.#...#.......#...#...#...#...#.....#...#.#.......#...#...#...#...#...#.#.#...#
#####.#.###.###.#.###.#.###.#.###.#.#.###.#.#######.#.#.#####.#.#.#.#.#####.#.#.###.###.###.#####.###.#.###.#####.#.###.#.###.#.#####.###.###
#.....#...#...#.#...#...#...#.#.#.#.#.....#.......#.#...#.#...#...#.#.#.....#.#...........#.#...#...#.#...#.......#...#.#.#...#.....#...#.#.#
#.#######.#.###.###.#####.###.#.#.#.###########.#.#.#.###.#.#######.#.#.#################.#.#.#.#####.###.#.#########.#.#.#.#####.#####.#.#.#
#.......#.#.........#.....#...#.......#.......#.#.#.#.....#.....#...#.#.#.............#...#...#.....#.....#.#.....#...#.#...#.....#.....#.#.#
#.#####.#.###########.#.#.#.#####.#####.#.###.#.#.#.#######.###.#####.#.#.###########.#.#######.###.#.#####.#.###.#.###.#####.#####.#####.#.#
#.#...#.#.......#.....#...#.....#.#.....#...#.#.#.#...#...#...#.#.....#.#.#.......#...#...#...#.#...#...#...#.#...#...#...#.......#...#.....#
#.#.#.#.#######.#.#############.###.#######.#.#.#.###.#.#.#####.#.#####.#.#.#.###.#.###.###.#.###.###.#.#####.#.#####.###.###########.#.###.#
#...#.#...#.....#.....#.......#.....#.......#.......#...#.#.....#.#...#.....#...#.#...#.#...#.....#.....#.....#.#...#.#.........#.....#...#.#
#####.###.###.#######.#.###.#.#######.###.#######.#.#####.#.#####.#.###.#######.#.###.#.#.###########.###.#####.#.#.#.#########.#.#.#.###.#.#
#...#...#...#.#.....#.#.#...#...#.....#...#...#...#.#...#.#.#.....#.............#...#.#.#...........#.....#...#.....#.....#.....#...#.#.#...#
#.#.###.###.###.###.#.###.#####.#.#####.#.#.#.#.#.###.#.#.#.###.###.#####.###########.#############.#########.#####.#.#.#.#.#.#.###.#.#.###.#
#.#...#.#.#.......#.#.....#.....#.....#.#.#.#...#...#.#.#...#...#.....#.#.#.......#...#.......#.....#.............#.#...#.#.#...#...#.....#.#
#.#####.#.#######.#.#######.#####.###.#.#.#.#######.#.#.#####.#######.#.#.#.#####.#.###.#####.#.#####.#####.#######.#.#.#.#.#.#.#.#.#####.#.#
#.......#...#...#.#.......#.....#.#.....#.#...........#.............#...#.....#.#...#.#...#...#.....#.....#.#...#...#...#.#.#.#.#.#.......#.#
#.#######.#.#.#.#####.#.#######.#.#.#.#.#########.#####.#.#####.###.#.#######.#.#####.#.#.#.###.###.#.###.###.#.#.#######.#.#.#.#.#.#######.#
#.#.......#.#.#.....#.......#.....#...#.........#.#.#...#.#.........#.......#.#.....#...#.#.#.....#.#...#.....#.#.#.......#.#.....#.#...#...#
#.#.#######.#.#####.#######.#.###.#.#########.#.#.#.#.###.#.###########.###.#.#.###.#####.#.#######.#.#########.#.#.#######.#####.#.#.###.###
#.#.......#.#.#.#...#...#...#...#.#.....#.....#.#...#.#.....#...........#.#.#...#.......#.#...#.....#.#.......#.#...#...#...#.....#...#...#.#
#.#########.#.#.#.###.#.#.#####.###.###.#.#.#.#####.#.#.#####.#########.#.#.###########.#.###.#.#####.#.#####.#.#####.###.###.#####.###.###.#
#.#.........#.#.#...#.#.#.#...#...#.....#.#...#.....#.#.....#.#...........#.........#.#.#.#.#...#...#.#.....#...#...........#.#...#.....#...#
#.#.#######.#.#.###.#.#.#.#.#.###.###.#.#.#####.#####.#.###.#.#.#########.#########.#.#.#.#.#####.###.#####.###.#.###.#####.#.#.#.#.#####.#.#
#...#...#...#.#.......#.#.#.#.....#...#.#.....#...#...#.#.#.#.#.........#...#.......#...#.#.#.........#...#...#...#...#...#...#.#...#.....#.#
#####.#.#.###.#.#######.#.#.#######.###.#####.#.#.#.###.#.#.#.#####.#######.#.#######.###.#.#.#########.#.###.###.#.###.#.#.#.#.###.#.#####.#
#.#...#.#.#...#.#...#.#...#.#...#.....#.....#.#.#.......................#...#.#...#...#...#...#.........#...#.....#.#...#.#...#...#...#...#.#
#.#.###.#.#.#####.#.#.###.#.#.#.#####.###.#.#.#.#.#.#.###.#####.#.###.#.#.###.#.###.###.#####.#.###.#######.#.###.#.#.###.#######.#.###.###.#
#.#...#...#.#.....#.#...#.#...#...#...#.#.....................#.....#.#.#...#.#.#...#...#...#...#...#...#...#.......#.#.#.........#...#.#...#
#.###.#.###.#.#####.#.###.#######.#.###.#.###.###.#.###.#.###.#######.#.###.#.#.#.###.###.#.#####.###.#.#.#######.#.#.#.#######.###.#.#.#.###
#...#...#...#.#.#...#...#.......#.#.....#.......#.#...#...#...................#.....#...#.#.............................#.........#...#.#...#
###.#.#.#.###.#.#.#####.#######.#.#########.###.#.###.#####.###.###.#####.#.#.###.#.###.#.#########.#.#####.#####.#.###.###.#####.#.###.###.#
#...#.#.#.....#.#.....#.....#...#.....#...#.#...#.#.......#...#.#...#...#.#.#.....#.....#.#.....#.....................#...#.#...#...#.....#.#
#.###.#.#######.#####.#####.#.#######.#.#.#.#####.#######.###.#.###.#.#.#.#.#####.#######.#.###.#.#.###.#####.#.#.###.###.#.###.#####.###.#.#
#.#...#...........#.#.....#.#.#...#...#.#.#...#...#.....#...#.#...#...#.#...#.....................#...#...#...#.....#.#...#...#.....#.#.#...#
#.#.###.#####.###.#.#####.#.#.#.#.#.###.#.###.#.###.###.#####.###.#.###.###.#.###.#.#.#.#.###.#.#.###.###.###.###.###.#.###.#.#.###.#.#.#####
#.#.....#.......#.....#.#.....#.#.#.#...#.....#.#...#.#.....#...#.#.#.#.#...#.....#...#.#...#.#.#.#.........................#.#...#.#...#...#
#.#######.#.#.#######.#.#.#####.#.#.###.#####.#.#.###.#####.###.#.#.#.#.#.#####.#.#####.###.#.#.#.#.###.###.#####.#######.###.#.#.#####.###.#
#S........#.............#.......#.......#.....#...........#.......#...#.........#.....#.......#...#.......#...................#.#...........#
#############################################################################################################################################
""".trimIndent()