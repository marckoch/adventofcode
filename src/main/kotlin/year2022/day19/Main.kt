package year2022.day19

import kotlin.math.max

val LINE_PATTERN =
    "Blueprint (.*): Each ore robot costs (.*) ore. Each clay robot costs (.*) ore. Each obsidian robot costs (.*) ore and (.*) clay. Each geode robot costs (.*) ore and (.*) obsidian.".toRegex()

fun main() {
    part1(SAMPLE)
}

fun part1(input: String) {
    val bluePrints = input.lines().map { parseBlueprint(it) }
    bluePrints.forEach { println(it) }

    calc(bluePrints.first())
//    calc(bluePrints[1])
}

fun calc(bp: Blueprint) {
    val initial = State(24, Resources(0, 0, 0, 0), Robots(1, 0, 0, 0))

    val queue = ArrayDeque<State>()
    queue.addLast(initial)

    val seenStates = mutableSetOf<State>()
    var maxGeode = 0

    while (queue.isNotEmpty()) {
        val state = queue.removeFirst()
        val resources = state.resources

        if (state.time == 0) {
            //println(state)
            maxGeode = max(maxGeode, resources.geode)
            continue
        }

        // reduce resources for robot builds
        // collect new resources
        // add new robots

        // println(state)

        // we do not buy anything, we just wait and collect resources
        State(state.time - 1, resources.collectResources(state.robots), state.robots)
            .let { seenStates.add(it) && queue.add(it) }

        // if prioritize the expensive robots first
        if (resources.cover(bp.geodeRobot)) {
            // we buy one geode robot
            State(state.time - 1, resources.spendOn(bp.geodeRobot).collectResources(state.robots), state.robots.incGeodeRobots())
                .let { seenStates.add(it) && queue.add(it) }
        } else if (resources.cover(bp.obsidianRobot)) {
            // we buy one obsidian robot
            State(state.time - 1, resources.spendOn(bp.obsidianRobot).collectResources(state.robots), state.robots.incObsidianRobots())
                .let { seenStates.add(it) && queue.add(it) }
        } else if (resources.cover(bp.clayRobot)) {
            // we buy one clay robot
            State(state.time - 1, resources.spendOn(bp.clayRobot).collectResources(state.robots), state.robots.incClayRobots())
                .let { seenStates.add(it) && queue.add(it) }
        } else if (resources.cover(bp.oreRobot)) {
            // we buy one ore robot
            State(state.time - 1, resources.spendOn(bp.oreRobot).collectResources(state.robots), state.robots.incOreRobots())
                .let { seenStates.add(it) && queue.add(it) }
        }
    }

    println("part1: $maxGeode")
}


fun parseBlueprint(l: String): Blueprint {
    val (id, oro, cro, obsro, obsrc, gro, grobs) = LINE_PATTERN.find(l)!!.destructured

    val oreRobot = Cost(oro.toInt(), 0, 0)
    val clayRobot = Cost(cro.toInt(), 0, 0)
    val obsidianRobot = Cost(obsro.toInt(), obsrc.toInt(), 0)
    val geodeRobot = Cost(gro.toInt(), 0, grobs.toInt())

    return Blueprint(id.toInt(), oreRobot, clayRobot, obsidianRobot, geodeRobot)
}

class State(val time: Int, val resources: Resources, val robots: Robots) {
    override fun toString(): String {
        return "time=$time res=$resources robots=$robots"
    }
}

class Blueprint(val id: Int, val oreRobot: Cost, val clayRobot: Cost, val obsidianRobot: Cost, val geodeRobot: Cost) {
    override fun toString(): String {
        return "Blueprint($id, oreRobot=$oreRobot, clayRobot=$clayRobot, obsidianRobot=$obsidianRobot, geodeRobot=$geodeRobot)"
    }
}

class Cost(val ore: Int, val clay: Int, val obsidian: Int) : Elements(ore, clay, obsidian, 0)

class Robots(val ore: Int, val clay: Int, val obsidian: Int, val geode: Int) : Elements(ore, clay, obsidian, geode) {
    fun incOreRobots(): Robots = Robots(ore + 1, clay, obsidian, geode)
    fun incClayRobots(): Robots = Robots(ore, clay + 1, obsidian, geode)
    fun incObsidianRobots(): Robots = Robots(ore, clay, obsidian + 1, geode)
    fun incGeodeRobots(): Robots = Robots(ore, clay, obsidian, geode + 1)
}

class Resources(val ore: Int, val clay: Int, val obsidian: Int, val geode: Int) : Elements(ore, clay, obsidian, geode) {
    fun collectResources(robots: Robots): Resources {
        return Resources(ore + robots.ore, clay + robots.clay, obsidian + robots.obsidian, geode + robots.geode)
    }

    fun cover(robotCost: Cost): Boolean {
        return ore >= robotCost.ore && clay >= robotCost.clay && obsidian >= robotCost.obsidian
    }

    fun spendOn(robotCost: Cost): Resources {
        return Resources(ore - robotCost.ore, clay - robotCost.clay, obsidian - robotCost.obsidian, geode)
    }
}

open class Elements(private val ore: Int, private val clay: Int, private val obsidian: Int, private val geode: Int) {
    override fun toString(): String {
        val o = if (ore > 0) "$ore ore" else ""
        val c = if (clay > 0) "$clay clay" else ""
        val obs = if (obsidian > 0) "$obsidian obsidian" else ""
        val g = if (geode > 0) "$geode geode" else ""
        return listOf(o, c, obs, g).filter { it.isNotEmpty() }.joinToString(", ", "(", ")")
    }
}