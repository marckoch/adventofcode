package year2021.day16

fun main() {
//    part1(SAMPLE)
//    part1(INPUT)
//    part1("D14")
//    part1("5224")
//    part1("38006F45291200")
//    part1("EE00D40C823060")
//    part1("8A004A801A8002F478")
//    part1("620080001611562C8802118E34")
//    part1("C0015000016115A2E0802F182340")
//    part1("A0016C880162017C3686B18A3D4780")

    part2("C200B40A82")
    part2("04005AC33890")
    part2("880086C3E88112")
    part2("CE00C43D881120")
    part2("D8005AC2A8F0")
    part2("F600BC2D8F")
    part2("9C005AC2F8F0")
    part2("9C0141080250320F1802104A08")
    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    println()
    println(input)
    val bin = BinaryString(toBinaryString(input))

    val p = bin.parseBinary()
    println(p)

    p.sumOf { it.versionSum() }.let { println("versionSum: $it") }
}

fun part2(input: String) {
    println()
    println(input)
    val bin = BinaryString(toBinaryString(input))

    val p = bin.parseBinary()
    println(p)

    p.map { it.eval() }.let { println(it) }
}

fun toBinaryString(s: String): String {
    return s.map { c ->
        Integer.toBinaryString(c.digitToInt(16))
            .padStart(4, '0')
    }.joinToString("")
}

class BinaryString(private val seed: String) {
    private var currentIndex = 0

    fun parseBinary(): List<Packet> {
        val ret = mutableListOf<Packet>()
        while (!isEmpty()) {
            ret.add(nextBinary())
        }
        return ret
    }

    private fun nextBinary(): Packet {
        val version = takeAsInt(3)
        val type = takeAsInt(3)

        return if (type == 4) {
            buildLiteral(version)
        } else {
            buildOp(version, type)
        }
    }

    private fun buildLiteral(version: Int): Literal {
        var acc = ""
        while (true) {
            val s = take(5)
            acc += s.substring(1)
            if (s.startsWith("0")) {
                return Literal(version, acc.toLong(2))
            }
        }
    }

    private fun buildOp(version: Int, type: Int): Packet {
        val op = Operator(version, type)

        val length = takeAsInt(1)
        if (length == 0) {
            val packetLength = takeAsInt(15)
            val packets = BinaryString(take(packetLength)).parseBinary()
            op.packets.addAll(packets)
        } else if (length == 1) {
            val packetCount = takeAsInt(11)
            repeat(packetCount) {
                op.packets.add(nextBinary())
            }
        }
        return op
    }

    fun take(i: Int): String {
        val r = seed.substring(currentIndex, currentIndex + i)
        currentIndex += i
        return r
    }

    private fun takeAsInt(i: Int): Int = take(i).toInt(2)

    private fun isEmpty():Boolean = seed.substring(currentIndex).all { it == '0' }
}

interface Packet {
    fun versionSum(): Int
    fun eval(): Long
}

class Literal(private val version: Int, val value: Long): Packet {
    override fun versionSum() = version
    override fun eval(): Long = value
    override fun toString(): String {
        return "Literal(vers:$version, value=$value)"
    }
}

class Operator(private val version: Int, private val type: Int): Packet {
    val packets: MutableList<Packet> = mutableListOf()
    override fun eval(): Long {
        return when (type) {
            0 -> packets.sumOf { it.eval() }
            1 -> packets.fold(1L) { acc, packet -> acc * packet.eval() }
            2 -> packets.minOf { it.eval() }
            3 -> packets.maxOf { it.eval() }
            5 -> if (packets[0].eval() > packets[1].eval()) 1 else 0
            6 -> if (packets[0].eval() < packets[1].eval()) 1 else 0
            7 -> if (packets[0].eval() == packets[1].eval()) 1 else 0
            else -> throw IllegalStateException("unknown type $type")
        }
    }
    override fun versionSum() = version + packets.sumOf { it.versionSum() }
    override fun toString(): String {
        return "Operator(vers:$version, type=$type, packets=$packets)"
    }
}