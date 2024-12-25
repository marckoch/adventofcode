package year2024.day25

val SAMPLE = """
#####
.####
.####
.####
.#.#.
.#...
.....

#####
##.##
.#.##
...##
...#.
...#.
.....

.....
#....
#....
#...#
#.#.#
#.###
#####

.....
.....
#.#..
###..
###.#
###.#
#####

.....
.....
.....
#....
#.#..
#.#.#
#####
""".trimIndent()

val INPUT = """
#####
#.##.
#.#..
#.#..
#....
#....
.....

#####
#####
#.#.#
#.#.#
#.#.#
..#..
.....

#####
#.###
#.##.
..##.
..##.
..#..
.....

#####
###.#
.#..#
....#
.....
.....
.....

.....
.....
.#...
.##.#
.####
.####
#####

#####
#####
.##.#
.##.#
.#...
.#...
.....

.....
#.#.#
#.#.#
#.#.#
#.#.#
#.###
#####

#####
##.#.
##.#.
##.#.
#..#.
.....
.....

.....
....#
.#..#
###.#
###.#
#####
#####

#####
.####
.#.##
.#.#.
.#...
.#...
.....

#####
#####
#####
#.###
#.#.#
#....
.....

#####
#####
.###.
.#.#.
.#...
.#...
.....

.....
#....
#...#
#.#.#
#.###
#.###
#####

.....
....#
....#
.#.##
#####
#####
#####

#####
.#.##
.#.##
.#.#.
...#.
.....
.....

#####
###.#
.#..#
....#
....#
.....
.....

#####
.####
..###
..##.
..#..
..#..
.....

.....
....#
....#
....#
.#.##
##.##
#####

.....
.#...
.#...
###.#
###.#
#####
#####

#####
#####
#.###
#.###
#..##
...#.
.....

.....
...#.
.#.##
.#.##
.####
#####
#####

#####
###.#
#.#.#
#.#.#
..#.#
.....
.....

.....
.....
.....
.#.#.
.#.##
##.##
#####

#####
####.
####.
###..
#.#..
..#..
.....

#####
#.###
#.###
..#.#
..#.#
.....
.....

#####
#####
####.
.###.
.##..
.#...
.....

.....
#.#..
#.#..
#.#..
#.#.#
###.#
#####

.....
.....
.....
....#
#...#
#.#.#
#####

#####
##.##
##..#
#...#
#...#
#....
.....

.....
...#.
.#.#.
####.
####.
####.
#####

.....
....#
....#
....#
#.#.#
#####
#####

#####
#####
#.###
#.###
..#.#
..#.#
.....

#####
.####
.##.#
.#..#
.#...
.....
.....

#####
#####
####.
####.
#.##.
...#.
.....

#####
#####
#.###
..#.#
....#
....#
.....

#####
#.###
#.###
...##
....#
....#
.....

.....
..#.#
..#.#
.##.#
.####
.####
#####

#####
###.#
##..#
#...#
#...#
.....
.....

.....
.....
.....
.#...
##..#
###.#
#####

.....
.....
#.#..
#.#.#
#.#.#
#####
#####

#####
#####
.#.##
.#.#.
.#...
.....
.....

.....
#....
#....
#.#..
#.##.
####.
#####

.....
....#
#...#
#...#
##..#
##.##
#####

#####
####.
####.
.#.#.
.#...
.#...
.....

.....
.....
.....
.#...
.##..
.###.
#####

.....
.#..#
.#..#
.#..#
##.##
#####
#####

.....
.....
..#..
#.##.
#.##.
#.###
#####

#####
##.##
##.##
#..##
...##
...#.
.....

#####
####.
.###.
.#.#.
.#...
.#...
.....

#####
###.#
#.#.#
#.#.#
.....
.....
.....

.....
.....
.....
#....
#.#..
#.##.
#####

.....
....#
#...#
#..##
##.##
##.##
#####

.....
.....
.#..#
.#..#
##.##
#####
#####

.....
.....
#.#..
#.#..
#.#..
###.#
#####

.....
....#
....#
#..##
#..##
##.##
#####

#####
#####
#.##.
..##.
..#..
.....
.....

#####
#####
#####
###.#
.#..#
.#...
.....

.....
....#
....#
.#..#
.#..#
###.#
#####

.....
.....
.#...
##.#.
##.#.
####.
#####

.....
#....
#..#.
#.###
#####
#####
#####

.....
.#..#
###.#
#####
#####
#####
#####

#####
###.#
###..
#.#..
#....
.....
.....

#####
##.##
##..#
.#...
.#...
.#...
.....

#####
.####
.#.#.
.#.#.
.#.#.
.#.#.
.....

.....
...#.
...##
...##
#..##
#.###
#####

.....
.....
..#..
..#..
.##..
.##.#
#####

#####
.####
.#.##
.#.##
.#.#.
...#.
.....

.....
.#...
.#...
##.#.
##.#.
####.
#####

.....
.#.#.
##.#.
##.#.
#####
#####
#####

.....
.....
#..#.
#.###
#.###
#####
#####

.....
.....
.....
..#..
..#.#
.##.#
#####

.....
.#..#
##.##
##.##
##.##
#####
#####

#####
#.#.#
#...#
#....
#....
#....
.....

.....
...#.
#..#.
#.###
#####
#####
#####

.....
...#.
.#.#.
.####
#####
#####
#####

#####
###.#
.#..#
.#..#
.#..#
.#...
.....

.....
#...#
#.#.#
#.#.#
#.#.#
#.#.#
#####

#####
#####
#.#.#
#.#.#
#....
#....
.....

#####
#.###
#.##.
...#.
.....
.....
.....

#####
#####
#.#.#
....#
....#
.....
.....

.....
.....
..#..
#.#..
###.#
#####
#####

#####
#####
####.
.##..
.##..
..#..
.....

#####
.####
.##.#
.##.#
.##.#
..#..
.....

#####
####.
#.##.
#.##.
#.#..
#.#..
.....

.....
.....
.....
..#..
#.#..
#.#.#
#####

#####
###.#
.##..
.##..
..#..
..#..
.....

#####
#.##.
#.##.
#.#..
..#..
.....
.....

.....
...#.
.#.#.
.#.##
#####
#####
#####

#####
#.###
#..##
#..##
...##
...#.
.....

.....
...#.
#.##.
####.
#####
#####
#####

#####
###.#
.#..#
.#...
.....
.....
.....

.....
...#.
...#.
.#.#.
.#.##
.####
#####

#####
##.##
#..##
#..#.
#..#.
#..#.
.....

#####
#####
#.###
..##.
..##.
..#..
.....

#####
##.#.
#..#.
#..#.
#..#.
...#.
.....

#####
#.###
#.###
#..#.
#....
#....
.....

#####
####.
####.
#.##.
#.#..
#.#..
.....

#####
#####
####.
##.#.
##...
.#...
.....

#####
#####
.####
.####
.###.
..#..
.....

.....
.#.#.
.###.
.###.
####.
#####
#####

.....
...#.
...##
#..##
##.##
##.##
#####

#####
#.###
#.##.
..##.
..#..
..#..
.....

.....
#.#..
#.#..
#.#..
###.#
#####
#####

#####
#.###
#.###
#.#.#
#.#.#
....#
.....

#####
###.#
###..
.#...
.#...
.....
.....

.....
.....
..#..
..#..
#.##.
#.##.
#####

#####
####.
####.
###..
###..
#.#..
.....

#####
#.###
#..#.
#....
.....
.....
.....

#####
#.#.#
#.#..
..#..
..#..
..#..
.....

.....
.#..#
.#..#
##..#
##..#
##.##
#####

.....
#....
#....
##..#
##.##
##.##
#####

#####
#####
####.
#.##.
#.##.
...#.
.....

#####
#####
#####
#.##.
..#..
..#..
.....

.....
.#.#.
#####
#####
#####
#####
#####

.....
.#..#
.#..#
###.#
###.#
#####
#####

#####
#####
####.
#.##.
...#.
.....
.....

#####
#####
#####
#.###
#.###
..#.#
.....

#####
####.
##.#.
#..#.
#....
#....
.....

.....
.#...
###..
###.#
#####
#####
#####

.....
....#
....#
.#.##
.#.##
.####
#####

#####
##.##
.#..#
.#..#
....#
....#
.....

#####
.####
.#.##
....#
.....
.....
.....

.....
.....
.#..#
.#..#
.#.##
.#.##
#####

#####
##.##
##.##
##.##
##.##
.#.#.
.....

#####
##.##
##.##
#...#
#...#
#...#
.....

.....
...#.
..##.
.####
.####
#####
#####

.....
#..#.
#..#.
#..##
#..##
#.###
#####

#####
.####
.####
.####
..##.
...#.
.....

#####
####.
.#.#.
...#.
...#.
...#.
.....

#####
#.###
#.###
#.###
...#.
.....
.....

#####
#.#.#
#.#.#
#.#..
#....
#....
.....

.....
#....
##..#
##.##
#####
#####
#####

#####
.###.
.###.
.###.
..##.
..#..
.....

#####
#####
#.##.
#.##.
#..#.
#....
.....

#####
.####
.##.#
.#...
.#...
.....
.....

.....
#....
#...#
#...#
##..#
##.##
#####

#####
.##.#
.#..#
.#..#
.#..#
....#
.....

#####
#####
.####
.#.##
.#.##
....#
.....

#####
.#.##
.#.##
...#.
...#.
.....
.....

.....
...#.
...#.
#..#.
#..#.
#.##.
#####

#####
###.#
##...
#....
#....
#....
.....

#####
##.##
.#.##
...#.
...#.
...#.
.....

.....
..#..
..#.#
..#.#
#.#.#
###.#
#####

#####
###.#
.##.#
.##..
.##..
.#...
.....

.....
.....
.#.#.
.#.##
.####
.####
#####

.....
....#
....#
..#.#
#.###
#.###
#####

#####
##.##
##..#
#...#
....#
....#
.....

.....
.....
.....
.#...
.#..#
##.##
#####

#####
#####
.#.##
...##
....#
.....
.....

#####
#.###
#..##
#...#
#....
#....
.....

#####
.####
.####
..###
...#.
...#.
.....

#####
#####
#####
#.###
#.##.
#.#..
.....

#####
#.##.
#.##.
..##.
..##.
...#.
.....

.....
.#...
.#...
.#..#
##.##
##.##
#####

.....
.....
.....
.#..#
##.##
#####
#####

.....
#.#..
#.##.
#.##.
####.
####.
#####

#####
###.#
#.#.#
#.#..
..#..
.....
.....

.....
..#..
#.#..
###..
###.#
###.#
#####

.....
.#...
.#...
.##..
####.
####.
#####

#####
#####
##.#.
##.#.
##.#.
#....
.....

#####
#####
#.##.
#.##.
#.#..
#.#..
.....

#####
#####
#####
#.###
..###
...#.
.....

.....
....#
.#..#
##..#
##..#
##.##
#####

#####
####.
#.##.
#.#..
.....
.....
.....

.....
.....
....#
..#.#
..#.#
.####
#####

.....
..#.#
..#.#
..#.#
.##.#
###.#
#####

#####
.#.##
.#..#
.#..#
.#..#
.....
.....

#####
#####
#####
###.#
.#..#
....#
.....

.....
.....
....#
....#
..#.#
#.#.#
#####

#####
.###.
.###.
.#.#.
.#...
.#...
.....

#####
###.#
##..#
##...
.#...
.....
.....

.....
.....
..#..
.###.
.###.
.###.
#####

#####
#####
#####
#.###
#..##
....#
.....

#####
####.
#.##.
...#.
...#.
.....
.....

.....
..#.#
..#.#
..#.#
..###
.####
#####

#####
##.##
#..##
#..##
#..##
....#
.....

.....
...#.
.#.#.
.#.#.
##.#.
##.##
#####

.....
..#..
..##.
.###.
.###.
####.
#####

#####
##.##
##..#
#...#
#...#
#...#
.....

#####
#.###
#..##
....#
.....
.....
.....

.....
.#...
.#...
.#.#.
.#.#.
.###.
#####

#####
#####
#####
.####
..#.#
..#..
.....

#####
#.###
#.###
..##.
..#..
.....
.....

.....
....#
.#..#
.#..#
.#..#
###.#
#####

.....
.....
....#
..#.#
#.###
#####
#####

#####
#####
###.#
##...
.#...
.....
.....

#####
#####
###.#
#.#.#
..#..
..#..
.....

.....
.....
...#.
...#.
#.###
#####
#####

.....
.....
.....
...#.
.#.##
#####
#####

#####
.####
.####
..##.
..#..
.....
.....

.....
.....
.....
.....
.#...
##.#.
#####

#####
###.#
###.#
##...
#....
#....
.....

.....
#....
##..#
###.#
###.#
#####
#####

.....
...#.
...##
..###
#.###
#####
#####

#####
#.#.#
#...#
#...#
....#
....#
.....

.....
....#
....#
.#..#
.#..#
.##.#
#####

.....
.....
...#.
.#.#.
##.##
##.##
#####

#####
#####
.#.##
.#.##
.#.##
.#..#
.....

.....
....#
....#
....#
#.#.#
#.###
#####

.....
#..#.
##.#.
##.#.
##.#.
####.
#####

.....
.....
#...#
#.#.#
#.#.#
###.#
#####

#####
.####
.#.##
.#.##
....#
....#
.....

.....
#....
#....
#...#
#.#.#
#.###
#####

.....
.#...
.##..
.###.
.###.
.####
#####

#####
#.#.#
#...#
#...#
#...#
....#
.....

.....
.....
.#...
##..#
##.##
##.##
#####

#####
#.#.#
#....
#....
#....
.....
.....

#####
#####
####.
.#.#.
.#.#.
...#.
.....

.....
.....
.#...
.#..#
.#.##
#####
#####

#####
#.###
#..##
#..#.
#..#.
...#.
.....

.....
#..#.
#.##.
#.##.
####.
####.
#####

.....
....#
....#
.#..#
.#..#
.#.##
#####

.....
..#.#
.##.#
.####
#####
#####
#####

.....
.#...
.#..#
##..#
##.##
#####
#####

#####
####.
####.
.###.
.#.#.
.#...
.....

#####
.#.##
.#.#.
.#.#.
.#...
.....
.....

#####
##.#.
#..#.
.....
.....
.....
.....

.....
....#
....#
#.#.#
#.###
#.###
#####

.....
.....
...#.
.#.##
.#.##
#####
#####

.....
.....
#..#.
#..#.
#..#.
##.##
#####

#####
#####
#####
#.#.#
..#..
..#..
.....

#####
.####
.####
.####
.#.#.
.#...
.....

.....
...#.
..##.
.###.
.###.
####.
#####

#####
.####
.####
.####
.##.#
..#..
.....

#####
#####
.####
.##.#
..#..
..#..
.....

.....
....#
....#
....#
.#..#
###.#
#####

.....
.....
.#.#.
.###.
.###.
####.
#####

.....
..#..
..#.#
..#.#
.##.#
###.#
#####

#####
#####
.##.#
.#..#
.#...
.....
.....

.....
.....
...#.
#..##
##.##
##.##
#####

.....
#....
#....
#..#.
##.##
##.##
#####

.....
....#
..#.#
..#.#
.####
.####
#####

#####
###.#
###.#
.##..
.##..
..#..
.....

#####
##.##
##.##
##.#.
#..#.
...#.
.....

.....
#..#.
#..##
#..##
##.##
##.##
#####

#####
.##.#
.##.#
.#..#
....#
.....
.....

#####
#.#.#
..#.#
..#.#
..#..
.....
.....

.....
.#...
.##..
.##..
.##..
###.#
#####

.....
.#.#.
.#.##
.#.##
.#.##
#####
#####

#####
#####
###.#
###.#
#.#.#
#....
.....

.....
.#.#.
.#.#.
.#.#.
#####
#####
#####

#####
##.##
#...#
#...#
#...#
#...#
.....

#####
.###.
.###.
.##..
..#..
..#..
.....

.....
...#.
...#.
...#.
.#.##
.#.##
#####

#####
.####
.#.##
...#.
...#.
...#.
.....

.....
...#.
..##.
.####
.####
.####
#####

.....
#.#.#
#.#.#
#.#.#
###.#
#####
#####

.....
#.#..
#.##.
#.##.
#.###
#####
#####

.....
.....
...#.
.#.#.
##.#.
##.##
#####

#####
#####
####.
####.
.#.#.
.#.#.
.....

.....
..#..
..##.
#.##.
#.###
#.###
#####

.....
.#.#.
##.##
##.##
##.##
##.##
#####

#####
.##.#
.##..
.##..
.##..
.#...
.....

#####
#.#.#
#.#.#
#...#
.....
.....
.....

.....
#....
#...#
##..#
##.##
##.##
#####

#####
#####
.#.#.
.#...
.#...
.#...
.....

#####
#####
##.##
#..##
#..#.
#..#.
.....

#####
#.###
...#.
...#.
...#.
...#.
.....

.....
.....
...#.
.#.#.
####.
#####
#####

#####
##.#.
##.#.
.#.#.
.#.#.
...#.
.....

#####
#.##.
#.#..
..#..
..#..
.....
.....

#####
.####
..##.
...#.
.....
.....
.....

.....
.#...
.#..#
.#..#
.##.#
.####
#####

#####
##.##
##.#.
##.#.
.#.#.
.#.#.
.....

#####
.####
.###.
..#..
.....
.....
.....

.....
#..#.
#.##.
####.
#####
#####
#####

#####
####.
####.
####.
##.#.
.#.#.
.....

#####
####.
####.
#.#..
#.#..
.....
.....

.....
.#..#
###.#
###.#
###.#
###.#
#####

#####
#.###
#.###
#.##.
..##.
..#..
.....

.....
....#
....#
#...#
#..##
#.###
#####

.....
..#..
..##.
.###.
.###.
.###.
#####

.....
..#.#
..###
..###
.####
.####
#####

#####
#####
#####
##.#.
.#...
.....
.....

#####
#.###
#..##
#..#.
#..#.
#....
.....

.....
...#.
..##.
#.###
#.###
#####
#####

#####
#####
###.#
.##..
.##..
..#..
.....

#####
.####
.#.#.
...#.
...#.
.....
.....

.....
.#.#.
.###.
.###.
.####
.####
#####

#####
.###.
.#.#.
...#.
.....
.....
.....

.....
.....
.....
...#.
..##.
#.###
#####

.....
.....
.#...
.#..#
###.#
###.#
#####

#####
###.#
###.#
###..
.#...
.#...
.....

#####
#####
.##.#
.##.#
.##.#
.#...
.....

#####
#####
##.##
##.##
.#..#
.#...
.....

#####
#####
#####
.#.##
.#.#.
.....
.....

.....
..#..
..#..
..#..
.##.#
.##.#
#####

#####
####.
####.
.###.
.#.#.
.....
.....

#####
##.##
##.##
#..##
#...#
#...#
.....

.....
.#...
.##.#
###.#
#####
#####
#####

#####
#####
##.##
#..##
#..##
#..#.
.....

#####
#####
#####
.#.##
...#.
.....
.....

.....
..#..
.##.#
.##.#
.##.#
###.#
#####

.....
..#.#
..#.#
..#.#
#.#.#
###.#
#####

#####
##.#.
##.#.
##.#.
##...
.#...
.....

.....
.#...
.#.#.
##.#.
####.
#####
#####

.....
.....
.....
#....
##.#.
##.#.
#####

#####
#.###
#..##
...#.
.....
.....
.....

.....
.....
.....
....#
.#..#
.#.##
#####

#####
.####
.###.
..#..
..#..
..#..
.....

.....
..#.#
..#.#
..#.#
#.###
#.###
#####

#####
###.#
###..
##...
.#...
.....
.....

#####
#####
#####
#.#.#
..#.#
..#.#
.....

#####
#####
###.#
###.#
###.#
#.#.#
.....

#####
#.#.#
#.#.#
#.#.#
#.#.#
#...#
.....

#####
#####
##.##
##.##
.#.##
.#.#.
.....

#####
####.
#.##.
#.##.
..##.
...#.
.....

#####
#####
###.#
###.#
#.#.#
....#
.....

.....
.#...
.#...
.#.#.
.#.#.
#####
#####

.....
#...#
#...#
#.#.#
###.#
###.#
#####

.....
...#.
...#.
...##
#..##
##.##
#####

#####
#.###
#.###
#.#.#
#.#..
.....
.....

.....
#..#.
#..#.
#.##.
####.
####.
#####

.....
.#..#
.##.#
.####
.####
.####
#####

.....
.....
...#.
..##.
#.##.
#.###
#####

#####
#.###
#.##.
#..#.
#..#.
#....
.....

#####
#####
###.#
###.#
.#...
.....
.....

#####
.####
..###
..#.#
....#
....#
.....

#####
##.##
.#..#
....#
....#
.....
.....

#####
####.
####.
#.##.
#.#..
#....
.....

.....
.....
...#.
..###
.####
.####
#####

#####
#.#.#
#.#..
#.#..
#.#..
#....
.....

.....
.....
.#..#
.##.#
.##.#
.####
#####

#####
#.###
#.###
#..##
#..#.
...#.
.....

.....
..#.#
..#.#
..#.#
#.#.#
#.#.#
#####

.....
.#...
.#.#.
.#.#.
.#.#.
.###.
#####

.....
#....
#....
##..#
###.#
#####
#####

.....
..#..
#.#..
###.#
###.#
#####
#####

.....
..#..
#.#..
###..
####.
#####
#####

.....
..#..
#.##.
#.##.
####.
####.
#####

#####
###.#
###.#
#.#.#
#...#
.....
.....

.....
#....
#....
#....
##...
###.#
#####

.....
.#...
.#...
.#...
.#.#.
##.##
#####

.....
.....
...#.
..###
#.###
#.###
#####

.....
..#.#
..#.#
.##.#
#####
#####
#####

#####
##.#.
##.#.
#....
.....
.....
.....

.....
#...#
#.#.#
###.#
###.#
#####
#####

#####
#####
###.#
###.#
#.#..
.....
.....

#####
#####
#####
.##.#
..#.#
....#
.....

.....
.#...
.#...
.##..
.##.#
#####
#####

#####
####.
####.
#.##.
#.##.
...#.
.....

.....
....#
..#.#
..#.#
..#.#
#.#.#
#####

.....
.....
.....
..#..
.##..
.###.
#####

.....
.....
.....
.#...
.#.#.
#####
#####

.....
.#..#
.#.##
.####
.####
.####
#####

#####
####.
#.##.
#.##.
..##.
..#..
.....

#####
#####
#.###
#.##.
#.#..
..#..
.....

#####
#####
#####
####.
####.
#.#..
.....

.....
.....
#....
#..#.
##.#.
####.
#####

.....
.....
...#.
...#.
.#.#.
.#.##
#####

.....
.....
#.#..
#.#..
#.##.
#.##.
#####

.....
#....
#.#..
###.#
###.#
#####
#####

.....
#....
#....
#.#.#
#.###
#.###
#####

.....
#....
#.#..
###..
###.#
###.#
#####

#####
#.##.
#.##.
#..#.
#..#.
...#.
.....

.....
.....
.....
.#..#
##..#
##.##
#####

.....
.....
...#.
.#.#.
##.#.
##.#.
#####

#####
#####
#.###
#.##.
..##.
..#..
.....

.....
.....
...#.
...#.
.#.#.
.#.#.
#####

#####
#####
####.
.#.#.
.....
.....
.....

#####
#####
#.###
..##.
...#.
...#.
.....

#####
.####
.###.
.#.#.
...#.
...#.
.....

.....
.#...
.#...
.#..#
###.#
#####
#####

.....
.#.#.
.###.
.###.
.###.
.###.
#####

.....
..#..
..#..
.###.
####.
####.
#####

.....
#..#.
#..##
#..##
#.###
#.###
#####

.....
..#..
#.##.
#.##.
####.
#####
#####

#####
.##.#
.##.#
..#..
.....
.....
.....

#####
#####
.##.#
.##.#
.#...
.....
.....

#####
#####
###.#
###.#
#.#.#
#.#.#
.....

.....
#.#..
#.#.#
#.#.#
#####
#####
#####

.....
.#...
###..
####.
#####
#####
#####

.....
...#.
..##.
#.##.
#.##.
#.###
#####

.....
....#
....#
..#.#
..#.#
#.###
#####

#####
.####
.#.##
.#.##
.#.##
...#.
.....

.....
....#
....#
#.#.#
#.#.#
###.#
#####

.....
.....
....#
#.#.#
#.###
#.###
#####

.....
...#.
#..#.
#..#.
#.###
#.###
#####

.....
.#...
.#...
.#...
.##.#
###.#
#####

#####
#.#.#
#.#.#
....#
....#
....#
.....

.....
#....
#....
#.#..
#.#..
####.
#####

#####
#####
###.#
###.#
#.#..
#.#..
.....

#####
####.
####.
.#.#.
.#.#.
.#.#.
.....

#####
#####
#####
##.##
#..##
....#
.....

#####
.####
.###.
..##.
..##.
..#..
.....

#####
#.#.#
#.#.#
..#.#
....#
.....
.....

#####
#.###
#.###
..##.
...#.
.....
.....

.....
.....
..#..
.##..
.##..
.##.#
#####

.....
.#...
.##.#
.##.#
.##.#
.####
#####

.....
.....
.#.#.
####.
#####
#####
#####

#####
###.#
###..
.##..
.##..
.#...
.....

.....
.....
..#.#
#.###
#.###
#.###
#####

.....
.#.#.
.#.#.
##.#.
#####
#####
#####

.....
.#..#
.#..#
##..#
##.##
##.##
#####

#####
#####
##.##
##..#
.#..#
.#..#
.....

.....
..#..
.##..
.##..
.###.
.###.
#####

.....
.....
.....
.#..#
###.#
#####
#####

.....
.....
#.#..
#.#.#
#.###
#.###
#####

#####
.###.
.#.#.
.#.#.
...#.
.....
.....

#####
.##.#
..#.#
..#.#
..#.#
.....
.....

.....
....#
....#
....#
...##
.#.##
#####

.....
.....
#....
#..#.
#.###
#####
#####

#####
####.
##.#.
##...
#....
#....
.....

.....
...#.
...#.
...#.
.#.#.
.#.#.
#####

#####
###.#
###.#
#.#.#
#.#..
.....
.....

.....
#....
#....
#..#.
#..#.
##.##
#####

.....
..#..
..##.
.###.
.####
.####
#####

.....
...#.
...#.
.#.#.
##.#.
#####
#####

.....
.....
...#.
.#.#.
.#.#.
##.##
#####

#####
#####
.#.##
.#.##
.#..#
....#
.....

.....
.....
#.#..
#.#..
#.##.
#####
#####

#####
#.###
#.###
#.###
#.#.#
#.#..
.....

#####
#####
.####
.#.#.
.#...
.#...
.....

#####
#.##.
...#.
...#.
.....
.....
.....

.....
.#..#
.#..#
.#.##
##.##
#####
#####

.....
#....
#....
#....
#...#
##.##
#####

#####
#.###
#.###
#.###
#.##.
...#.
.....

.....
...#.
.#.#.
.#.##
.####
.####
#####

#####
.####
.####
.#.##
.#.##
....#
.....

.....
...#.
...#.
..###
.####
#####
#####

#####
#.###
#..#.
#....
#....
#....
.....

#####
#.##.
#.##.
...#.
...#.
.....
.....

.....
.....
.....
#.#.#
#####
#####
#####

.....
#.#..
#.#..
###.#
###.#
###.#
#####

.....
.#.#.
.#.#.
.###.
####.
#####
#####

#####
#####
##.##
.#.##
....#
....#
.....

.....
.#...
.#...
.#...
##...
##.#.
#####

.....
#....
#....
##.#.
####.
####.
#####

#####
#.###
#.#.#
#...#
#...#
....#
.....

.....
.#...
##...
##.#.
##.#.
#####
#####

.....
..#..
..#..
..#..
..#.#
#.#.#
#####

#####
###.#
##...
#....
#....
.....
.....

.....
..#..
#.#..
####.
####.
#####
#####

#####
####.
####.
####.
##.#.
.#...
.....

#####
.####
.#.##
...##
...##
...#.
.....

.....
....#
...##
.#.##
.#.##
#####
#####

#####
##.##
##.##
#..##
...##
....#
.....

.....
.....
..#..
..##.
#.##.
#.###
#####

.....
.#...
.##..
.##.#
.##.#
#####
#####

#####
##.#.
##...
##...
##...
#....
.....

.....
.....
.....
.#...
##.#.
##.#.
#####

#####
.#.#.
.#.#.
.#.#.
.#.#.
.....
.....

.....
.....
#....
##...
###.#
###.#
#####

.....
.....
#....
##.#.
####.
#####
#####

#####
#####
#####
#.#.#
....#
.....
.....

#####
#####
###.#
##...
#....
.....
.....

.....
...#.
...#.
.#.#.
####.
#####
#####

.....
..#..
.##..
.##..
###..
####.
#####

#####
####.
##.#.
##.#.
##...
#....
.....

#####
.####
.####
..#.#
....#
.....
.....

.....
.....
..#..
#.#..
####.
#####
#####

#####
###.#
###..
.##..
.##..
..#..
.....

.....
.....
..#.#
..#.#
..###
.####
#####

#####
#####
###.#
##..#
.#...
.....
.....

#####
##.#.
##.#.
##.#.
.#.#.
...#.
.....

.....
.#.#.
.#.##
.####
.####
#####
#####

.....
....#
#.#.#
###.#
###.#
#####
#####

#####
###.#
#.#.#
#.#.#
..#.#
..#.#
.....

.....
..#..
..#..
.##..
.##..
####.
#####

.....
.....
.#...
.#...
.##.#
###.#
#####

.....
...#.
...#.
...##
..###
#.###
#####

#####
##.##
.#..#
.#..#
....#
.....
.....

.....
..#..
.##..
.##..
###..
###.#
#####

#####
#####
###.#
#.#.#
..#..
.....
.....

.....
.....
...#.
...#.
#.##.
####.
#####

.....
#....
#..#.
#.##.
#####
#####
#####

#####
#.###
#.###
#.###
#.###
..#.#
.....

#####
#.#.#
....#
.....
.....
.....
.....

.....
...#.
...#.
..###
#.###
#####
#####

#####
##.##
.#..#
.#..#
.....
.....
.....

.....
.#..#
.#..#
.#..#
##..#
###.#
#####

.....
.#...
.#..#
.##.#
.##.#
###.#
#####

.....
.....
..#..
#.#..
#.#.#
#####
#####

#####
#####
###.#
##..#
.#..#
.....
.....

#####
#.##.
#.##.
#.#..
.....
.....
.....

#####
#.#.#
#.#.#
#....
.....
.....
.....

#####
#####
#####
.#.##
.#.##
.#..#
.....

.....
....#
....#
..#.#
..###
#.###
#####

#####
#####
###.#
#.#..
#.#..
..#..
.....

.....
.....
#..#.
#..#.
#.##.
#.###
#####

.....
..#..
.##..
.##.#
.##.#
###.#
#####

.....
...#.
...#.
...#.
.#.#.
####.
#####

#####
####.
##.#.
##.#.
.#.#.
.#.#.
.....

.....
#....
#....
#...#
##.##
#####
#####

#####
#####
###.#
.##.#
.##.#
.#..#
.....

#####
##.##
##.##
.#..#
.#..#
.#...
.....

.....
.#...
.##.#
#####
#####
#####
#####

#####
##.##
##.##
##.##
#..#.
#....
.....

.....
....#
....#
....#
#.#.#
#.#.#
#####

#####
#.###
#.#.#
....#
....#
.....
.....

#####
#####
#####
##.##
##.#.
#....
.....

#####
#.###
..###
..#.#
..#.#
....#
.....

.....
#....
#..#.
##.##
#####
#####
#####

.....
#....
#.#..
####.
####.
#####
#####

.....
.....
.#..#
.#..#
.#.##
##.##
#####

#####
#####
#####
.###.
.###.
.#.#.
.....

.....
.....
...#.
...#.
#.##.
#####
#####

#####
#####
####.
##.#.
#..#.
#....
.....

.....
#.#..
#.#..
#.#..
####.
####.
#####

#####
#####
.####
..##.
...#.
.....
.....
""".trimIndent()