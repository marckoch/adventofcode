package year2023.day05

val SEED_LINE_PATTERN = """seeds: ([\d ]+)""".toRegex()

fun main() {
    part1(SAMPLE).let { println(it) } // 35
    part1(INPUT).let { println(it) }  // 240320250

    part2(SAMPLE).let { println(it) } // 46
    part2(INPUT).let { println(it) }  // 28580589 but super slow :(
}

fun part1(input: String): Long {
    val lineGroups: List<List<String>> = readLineGroups(input)

    val seedsLine = lineGroups.first().first()
    val mappingGroups = lineGroups.drop(1) // drop first group with seeds

    val seeds = extractSeeds(seedsLine)
    val mappers = buildMappers(mappingGroups)

    return seeds.map { seed ->
        applyMapperChain(mappers, seed)
    }.minBy { it }
}

fun part2(input: String): Long {
    val lineGroups: List<List<String>> = readLineGroups(input)

    val seedsLine = lineGroups.first().first()
    val mappingGroups = lineGroups.drop(1) // drop first group with seeds

    val seeds = extractSeeds(seedsLine)
    val mappers = buildMappers(mappingGroups)

    var min = Long.MAX_VALUE
    seeds.windowed(2, 2).forEach { (start, length) ->
        // println("checking $start $length")
        for (seed in start until start + length) {
            val mapped = applyMapperChain(mappers, seed)
            if (mapped < min) {
                min = mapped
            }
        }
    }
    return min
}

fun applyMapperChain(mappers: List<Mapper>, input: Long): Long {
    var x = input
    mappers.forEach { mapper ->
        x = mapper.map(x)
    }
    return x
}

fun buildMappers(mappingGroups: List<List<String>>): List<Mapper> {
    return mappingGroups.map { mapGroup ->
        val nameOfMapper = mapGroup.first().substring(0, mapGroup.first().indexOf(" map:"))
        // println(nameOfMapper)

        val mappingLines = mapGroup.drop(1) // drop first line with mapper name

        val mapper = Mapper(nameOfMapper, mappingLines)
        mapper
    }
}

class Mapper(val name: String, mappingStrings: List<String>) {
    private val mappings: List<Mapping>

    init {
        this.mappings = buildMappings(mappingStrings)
    }

    fun map(input: Long): Long {
        mappings.forEach { mapping ->
            val mapped = mapping.map(input)
            if (mapped != input) {
                return mapped
            }
        }
        return input // no mapper found
    }
    private fun buildMappings(mappingStrings: List<String>): List<Mapping> {
        return mappingStrings.map { mappingString ->
            val (dest, src, range) = mappingString.split(" ").map { it.toLong() }
            Mapping(dest, src, range)
        }
    }
}

class Mapping(private val dest: Long, private val src: Long, private val range: Long) {
    fun map(input: Long): Long {
        if (input in src..<src + range) {
            return input + (dest - src)
        }
        return input
    }
}

fun extractSeeds(seedsLine: String): List<Long> {
    return SEED_LINE_PATTERN.matchEntire(seedsLine)!!.groupValues[1]
        .split(" ")
        .map { it.toLong() }
}

fun readLineGroups(input: String): List<MutableList<String>> {
    return input.lines()
        .fold(mutableListOf()) { acc, line ->
        if (line.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            if (acc.isEmpty()) {
                acc.add(mutableListOf())
            }
            acc.last().add(line)
        }
        acc
    }
}