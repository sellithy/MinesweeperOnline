package solver

import grid.*

fun Mold(block: Mold.Builder.() -> Unit) = Mold.Builder().apply(block).build()

class Mold private constructor(cells: MutableMap<Position, Set<ImaginaryCell>>) :
    AbstractGrid<Set<ImaginaryCell>>(cells) {

    fun matches(grid: ActualGrid, gridPos: Position): Boolean {
        for ((moldPos, possibilities) in this) {
            grid[gridPos + moldPos]?.let {
                if (it !in possibilities) return false
            } ?: if (ImaginaryCell.OUTSIDE !in possibilities) return false
        }
        return true
    }

    class Builder {
        private var width = 0
        private var height = 0
        private val cells = mutableMapOf<Position, Set<ImaginaryCell>>()

        fun row(block: Builder.Row.() -> Unit) {
            Row().block()
            height++
            width = 0
        }

        inner class Row {

            private fun add(cell: ImaginaryCell): Set<ImaginaryCell> {
                return cells.getOrDefault(height pos width++, mutableSetOf()).apply {
                    (this as MutableSet).add(cell)
                }
            }

            operator fun ImaginaryCell.unaryPlus() = add(this)
            infix fun Set<ImaginaryCell>.or(other: ImaginaryCell) = add(other)

        }

        fun build() = Mold(cells)
    }
}