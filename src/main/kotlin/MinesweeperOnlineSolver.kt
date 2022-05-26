import grid.*
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.interactions.Actions
import solver.ActionType
import solver.solve

class MinesweeperOnlineSolver(path: String, private val flagMines: Boolean = false) {
    init {
        System.setProperty("webdriver.gecko.driver", path)
    }

    private val driver = FirefoxDriver().apply {
        get("https://minesweeper.online/new-game/ng")
    }
    private val actions = Actions(driver)
    private val grid = Grid(16, 16, 40)
    private val posToDiv: Map<IPosition, WebElement>

    init {
        Thread.sleep(1000)
        driver.findElement(By.id("level_select_12")).click()
        Thread.sleep(1000)
        with(driver.findElement(By.id("A43"))) {
            findElement(By.className("start")).click()
            posToDiv = mutableMapOf()
            for (cell in grid) posToDiv[cell.position] =
                findElement(By.id("cell_${cell.position.column}_${cell.position.row}"))
        }
    }

    fun runSolver() {
        do {
            updateUnknownPositions()
        } while (solve1Step())

        if (grid.isNotSolved) println("Could not solve the grid")
    }

    private fun updateUnknownPositions() {
        for (pos in grid.unknownPositions) {
            val classes = posToDiv[pos]!!.getAttribute("class").split(" ")

            if (classes.contains("hd_opened")) grid[pos] =
                classes.first { it.startsWith("hd_type") }.last().digitToInt().asState
            else if (classes.contains("hd_flag")) grid[pos] = CellState.FLAG
        }
    }

    private fun solve1Step(): Boolean {
        var changed = false
        grid.solve().forEach { (type, pos) ->
            when (type) {
                ActionType.OPEN -> actions.click(posToDiv[pos])
                ActionType.FLAG -> if (flagMines)
                    actions.contextClick(posToDiv[pos])
                else
                    grid[pos] = CellState.FLAG

            }
            changed = true
        }

        actions.perform()
        return changed
    }
}