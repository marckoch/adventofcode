package year2015.day07

import adventofcode.Problem
import util.parser.findTokens

const val DIGIT = "(-?\\d*)"
const val WORD = "(\\w*)"
val CLEAR_SIGNAL = "$DIGIT -> $WORD".toRegex()
val CLEAR_SIGNAL_FOR_B = "$DIGIT -> b".toRegex()
val AND_INSTRUCTION = "$DIGIT AND $DIGIT -> $WORD".toRegex()
val OR_INSTRUCTION = "$DIGIT OR $DIGIT -> $WORD".toRegex()
val LSHIFT_INSTRUCTION = "$DIGIT LSHIFT $DIGIT -> $WORD".toRegex()
val RSHIFT_INSTRUCTION = "$DIGIT RSHIFT $DIGIT -> $WORD".toRegex()
val NOT_INSTRUCTION = "NOT $DIGIT -> $WORD".toRegex()

typealias Signal = Int
typealias Wire = String

fun main() {
    AOC2015D07(INPUT).run()
}

class AOC2015D07(input: String) : Problem(input) {
    override fun solve1(): Int {
        return inputLines()
            .let { reduceInstructions(it) }
    }

    override fun solve2(): Int {
        return inputLines()
            .map { i ->
                // replace '.* -> b' with '46065 -> b' which was a's signal in Part 1
                if (CLEAR_SIGNAL_FOR_B.matches(i)) {
                    "46065 -> b"
                } else i
            }.let { reduceInstructions(it) }
    }

    private fun reduceInstructions(initialInstructions: List<String>): Int {
        var values = mapOf<Wire, Signal>()
        var remainingInstructions = initialInstructions

        while (remainingInstructions.isNotEmpty()) {
            // extract clear signals (clear signal means: 'number -> wire', left side is completely evaluated)
            remainingInstructions
                .partition { i -> CLEAR_SIGNAL.matches(i) }
                .also {
                    val clearSignals = it.first
                    values = clearSignals.map { s -> CLEAR_SIGNAL.findTokens(s) }
                        .associate { l -> l[1] to l[0].toInt() }

                    remainingInstructions = it.second
                }

            remainingInstructions = remainingInstructions
                .map { replaceWireWithSignal(it, values) }
                .map { reduceSignals(it) }
        }

        return values["a"]?: throw NoSuchElementException("no 'a' found in values!")
    }

    // here we take the list of signals (e.g. '123 -> c') and replace the wire 'c'
    // in all other given instructions with its signal value '123',
    // e.g. the instruction 'c AND lc -> f' will become '123 AND lc -> f' after
    // merging it with the signal '123 -> c'
    private fun replaceWireWithSignal(instruction: String, values: Map<Wire, Signal>): String {
        return values.entries.fold(instruction) {
            acc, (wire, signal) -> acc.replace(("""\b+$wire\b+""").toRegex(), signal.toString())
        }
    }

    // here we check if on the left side we can evaluate the operation, that means:
    // on the left side we only have numbers and operations, all variables/wires on
    // the left have been replaced with their signal value,
    // e.g. '123 AND 456 -> d' will become '72 -> d'
    // (because we replaced '123 AND 456' with its result '72')
    private fun reduceSignals(instruction: String): String {
        return if (AND_INSTRUCTION matches instruction) {
            val (sig1, sig2, wire) = AND_INSTRUCTION.findTokens(instruction)
            val newSignal = sig1.toInt() and sig2.toInt()
            "$newSignal -> $wire"
        } else if (OR_INSTRUCTION matches instruction) {
            val (sig1, sig2, wire) = OR_INSTRUCTION.findTokens(instruction)
            val newSignal = sig1.toInt() or sig2.toInt()
            "$newSignal -> $wire"
        } else if (LSHIFT_INSTRUCTION matches instruction) {
            val (sig1, sig2, wire) = LSHIFT_INSTRUCTION.findTokens(instruction)
            val newSignal = sig1.toInt() shl sig2.toInt()
            "$newSignal -> $wire"
        } else if (RSHIFT_INSTRUCTION matches instruction) {
            val (sig1, sig2, wire) = RSHIFT_INSTRUCTION.findTokens(instruction)
            val newSignal = sig1.toInt() shr sig2.toInt()
            "$newSignal -> $wire"
        } else if (NOT_INSTRUCTION matches instruction) {
            val (signal, wire) = NOT_INSTRUCTION.findTokens(instruction)
            val newSignal = signal.toInt().inv()
            "$newSignal -> $wire"
        } else {
            instruction
        }
    }
}


