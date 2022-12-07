package year2022.day07

const val DIGIT = "(\\d*)"
const val NAME = "(.*)"
val CD = """\$ cd $NAME""".toRegex()
val DIR = "dir $NAME".toRegex()
val FILE = "$DIGIT $NAME".toRegex()

fun main() {
    val sampleTree = buildTree(SAMPLE)
    println(sampleTree)
    part1(sampleTree)
    part2(sampleTree)

    val realTree = buildTree(INPUT)
    println(realTree)
    part1(realTree)
    part2(realTree)
}

fun part1(root: File) {
    println("part1 answer: ${getSumOfSmallDirs(root)}")
}

fun part2(root: File) {
    val dirs = getDirList(root, mutableListOf())

    val free = 70000000 - root.size()
    val freeNeeded = 30000000 - free

    println("free: $free")
    println("free needed for update: $freeNeeded")
    dirs
        .filter { it.size() > freeNeeded }
        .minBy { it.size() }
        .let { println("part2 answer: ${it.name} ${it.size()}") }
}

fun buildTree(input: String): File {
    val root = File("/", 0, FileType.DIR, null)
    var currentDir = root

    for (line in input.lines().drop(1)) {
        if (line.startsWith("$ ls")) {
            // do nothing, just read following lines
        } else if (CD.matches(line)) {
            val (newDir) = CD.find(line)!!.destructured
            currentDir = if (newDir == "..")
                currentDir.parent!!
            else
                currentDir.children.first { it.name == newDir }
        } else if (DIR.matches(line)) {
            val (name) = DIR.find(line)!!.destructured
            currentDir.children.add(File(name, 0, FileType.DIR, currentDir))
        } else if (FILE.matches(line)) {
            val (size, name) = FILE.find(line)!!.destructured
            currentDir.children.add(File(name, size.toLong(), FileType.FILE, currentDir))
        }
    }
    return root
}

fun getSumOfSmallDirs(f: File): Long {
    return if (f.isDir()) {
        val mySize =
            if (f.size() <= 100000) {
                f.size()
            } else
                0  // big dirs don't count

        mySize + f.children
            .filter { it.isDir() }
            .sumOf { getSumOfSmallDirs(it) }
    } else
        0  // files don't count
}

// take tree and build list of all directories found
fun getDirList(f: File, acc: MutableList<File>): List<File> {
    if (f.isDir()) {
        acc.add(f)
        f.children.forEach { getDirList(it, acc) }
    }
    return acc
}

// like in Linux everything is a file: files and directories
class File(val name: String, private val size: Long = 0, private val type: FileType, val parent: File?) {
    val children: MutableList<File> = mutableListOf()

    private fun depth(): Int {
        return if (parent != null) {
            2 + parent.depth()
        } else {
            0
        }
    }

    fun isFile(): Boolean = type == FileType.FILE
    fun isDir(): Boolean = type == FileType.DIR

    fun size(): Long {
        return if (isFile())
            size
        else
            children.sumOf { it.size() }
    }

    override fun toString(): String {
        val indent = " ".repeat(depth())
        var t = "$indent- $name (${type.s}, size=${size()})"
        for (c in children) {
            t += "\n  $c"
        }
        return t
    }
}

enum class FileType(val s: String) {
    DIR("dir"), FILE("file")
}