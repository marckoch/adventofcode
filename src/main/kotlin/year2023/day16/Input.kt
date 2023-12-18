package year2023.day16

const val SAMPLE = """
.|...\....
|.-.\.....
.....|-...
........|.
..........
.........\
..../.\\..
.-.-/..|..
.|....-|.\
..//.|...."""

const val INPUT = """
\..\.....-............-......................-...................-............|.-..../..\/.|.......-..........
...............-....-.-...........\......-............/..\............|........|...........-.........../..\...
-..............|..............-.\......................-.............../.............|..../......-/......-....
......................-....-.........-.........../-.\..........|./.......-................./....-..../........
.............................................|........./|.-.....|..................../......../........\...|..
.....-.........-.....|....-............................/-.............../..-.....-......\.......//\.../.-.....
.........../.|...../........-...............|..|\.........|..........\.....................-..................
...................|..........-/|/........\.....................................\.../.|.....\..........-......
........./.............................-......../...........-...........-.....................\........./.....
....-......./-......./.\-......|.....|...-......|............|...../...|...\.........\....|/.........|........
......./......-......-.....-.|.........../...........|.....................//.....-....-......................
..-................./../..........-..\.........\..\.............\/........\...../.........|....-|.............
.....\................-...................\...................................................-|.\.........|..
..\.......-.\-....\................/..........|............../.../.......-............\.............|...|.....
........./..........|....\................-...................|-.........................\.......|............
-..../..................\.....-........../........./\........./....\-..........\..............................
-........./........\....|.....\.|......../|................|..........................|...\.../...|...........
.......||...............................\.......\.-.......................|...\.....\.......................\.
............|....................|............./\............................-............-...................
.......-...............\......-......|......................\\..../.....-.....-.|...........-.......-.........
|........../......|..../...-...|...../..-../...............................|\.............../.../.............
..............-...\.................-................-......|...-....\|.\..\..................................
...........-./......../.......................-|../...../..................|..................................
-.-............../................./-.........-............||...........-../....-...../..............|..|.....
\-.\/.....................................-.........|.\..........|....../......\..........|.|.................
.............-\..../.....-...../..\................................................-.|..\......|-..\...|......
..\...................................\............|............|.-...\.../..../....\.\.......................
.........-............/.....-..\..../.......-......./.../.\|.........../........\.......\........\............
.|.\..........................|....|.........../................../../-......|/../.........\......./...\......
........./|....|..|.-....................||..|.............................................|..................
..................|.|............/....|.............\...-....-\.....|....../../...............................
..............|...........\.....-.......\..........................|.........-./\.............|........-......
...........................|......\..|.......................................................\.|..............
/..\.........................../........./......\...\...........\.........\./............/......../....../.\..
.\...-\....-..................-..............-.......|....\.....-...-|.|.......-.........../......\...........
...|../-......../....\.-..........\............./.-........|...................................|..............
..\...\.................|............././...\|../....\.............-....../........-\.................\......|
...\...........-.............|......../...................\...\..........................\...............-....
....................\......\................................................//......................././......
....-.|......|......../..-..|..|.............../.........................\.........-......................./..
...|............./......./..../..-..............................-......../..-....\...........-.-....|../......
.........-......................|............................/......\.........................\......|....|...
........../...........\................//....-................-.............\............................../..
........\......./..........|-............./\............../.....\......-....................................\\
....\|....../........./-..........................-.../........./|...|-.\./.................|....|-.\.........
-|............................|\.............................................................../.........-....
................\/....|............-......\/.\.-...|.................................../................./\.\.
.....-....\............|............................../.........../....\...........................\..../-|...
.............................\...................-...\..........|/.........../........\.....\....\..........|.
...................\......./...../............./..\./.....-.|..........|...../-.....--..-..........|..........
...\..............-.-....-..|............../......................-.........................-..-............|.
....|......./-................-................\...........|......../.................|/..............\.......
........./..............\.....\..........\..|..|-........-..........\./.............\............|............
........\..................\........-..../.....-.\...../.-....................-................|..........-...
.........................-...|......-...\....|......\.......\.........-....\.|\......\.........\..............
.........\...................-......................|.....-...............|...../......-.....\.|.......|..-...
../.......-.......................|............|/.\........-...................|............/......|.-........
........-../..........|................/........-.\...-..|......./.....-..\.............../..........\....\..-
............./../...-...................../.............................\...|.............../........-........
...................../...................|..........|........................||-..................\../........
.....|...........-...............-.....\.....\..../................\.....././.................................
...........|.|...........|...........\............\......-......................-.........|................\..
......../............|................../.-.........../......|.\...............-..........\............\....-.
..../\.................../....................-......................-..........\............./............./.
........../.........-.\.-/-...................................................................................
...../..../..-..................................|...................................|..............|...|......
..............\/......\...|\......................-................|../..-..................-...-.|..|....\...
.....................|.\-...............................|......-.........\.......-.............-.....-........
............/........../....|../...................-....-...|./.....-.|.......|..|.........../.............../
.........../..../.................-..../....\................./...-...........................................
..................................\..............|.........-....../..|........\...-....\.......-..|.........-.
..../.|.....\............./....../........\................./....|.........................-..\-..............
....|........................|..........|........|.........../....-.\\-............/.....\.......|............
|.......-.....|.....................|.\...-....\..........-................................................../
.................................|............././........\........../..\......\/...................\./...|...
.....-.............|..........\.............\.../..........\.......-|.........\.......\......-.../......|.....
.......................\....................|.....|......-....................|...-...../................\.|..
.|.|..\...../...|............................./......................|................................/.......
........\.....................-...............|...................-.\.....-.......|....../..........\./.......
....//......../.-..../......................\..-...../.........|......................|...|.....|\............
...\-/..../-....../......................|........../....-...................-.../......|.\...........\/......
.............\..\.-................|.............|.............\..-......./..-....\.../........../.||\........
..........\...\./\|...\..................................................|.....-.\.-....\..\..................
......\...|....-.......-......\......|..............\..................\\...............................|.....
......|....|.................\...|-.....|......./...|......../...../.............|...\.........|............\.
.....|....-.\../-..........\............................../......./...-.......|.......|..............-.\......
...........................|...\/............................./.../..........\............|...................
......-..........\./..........-.....................-...........................................\......|.-....
....-.../-.......|......|....../../............/..-......../.\..\|.-/.........-.../........\......\....../....
|...............\.............|......................|-..............\......................|......../.-....|.
../././................|.....\..\../.\.|...........\.............-.....\.\..............-....\................
..................../............../...-........./-...-.........|.........../.............\...............-...
.............................\.........\...-.............\......../....../.-..............................-...
................/.|..../........\................./....\.......\........../..-.|...-.........\.............-..
.|../.|.................................................-.../...............................................|.
..............................................|........../......................................-.............
..............|...-....|......-.....|...-..........-.........-......-.\.........-...........-........|.......\
.....-......./...-..........|...\/...../.-.................-......\..........\....\...................../..-..
...../............./..........-...................\......................-......./../.................../|....
........|.............\...\--..-.../.......................................-............../..\............/.-.
...............|-.............../-.........\.....................\..-.......................-........./\......
.........\.....\.............../....\.../.|.....................-.............................................
........./...............|.../........../././.../-./-..............//..|.........|...|.....-.......\.........\
..........|.........\.\..............-..\.\.\..|-..............\.....|........................................
.....................-....-./../....../.....\-..............-....|......\........\...|................-.......
/..............\.............|............\.\...\|.../.........-........../.....\...../|......................
.............-....-........................................|.........../....................|......-/.........
....../......-..../.....-..............././.....|...|.../.....|......\........\|...-..-........\\....../\.....
.....\......................................./..|......./\....|..|............./..............................
../..................\...|....../................\....\.../.........\..|........./......\/......|/............"""