package solver

import grid.*


class Mold private constructor(cells: MutableMap<Position, Set<ICellState>>, val origin: Position) :
    AbstractGrid<Set<ICellState>>(cells) {

    companion object{
        fun fromRows(origin: Position, block: RowBuilder.() -> Unit) = RowBuilder().apply(block).build(origin)
    }

    context(ActualGrid)
    fun matches(gridPos: Position): Boolean {
        for ((moldPos, possibilities) in this) {
            @Suppress("INVALID_IF_AS_EXPRESSION_WARNING")
            this@ActualGrid[gridPos + moldPos]?.let {
                if (it !in possibilities) return false
            } ?: if (ImaginaryCell.OUTSIDE !in possibilities) return false
        }
        return true
    }

    class RowBuilder {
        private var width = 0
        private var height = 0
        private val cells = mutableMapOf<Position, Set<ICellState>>()

        fun row(block: RowBuilder.Row.() -> Unit) {
            Row().block()
            height++
            width = -1
        }

        inner class Row {

            private fun get(pos: Position) = (cells.getOrPut(pos) { mutableSetOf() } as MutableSet)

            private fun add(cell: ICellState) = get(height pos ++width).apply { add(cell) }
            private fun add(cell: Iterable<ICellState>) = get(height pos ++width).apply { addAll(cell) }


            operator fun ICellState.unaryPlus() = add(this)
            operator fun ICellState.not() = add(ICellState.values.filter { it != this })

            operator fun Set<ICellState>.plus(other: ICellState) =
                get(height pos width).apply { add(other) }

            operator fun Set<ICellState>.minus(other: ICellState) =
                get(height pos width).apply { remove(other) }
        }

        fun build(origin: Position) = Mold(cells, origin)
    }
}