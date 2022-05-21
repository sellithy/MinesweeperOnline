import grid.CellState
import solver.ActionType
import grid.Grid
import grid.Position
import grid.asState
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.interactions.Actions
import solver.solve

class MinesweeperOnlineSolver(path: String) {
    init {
        System.setProperty("webdriver.gecko.driver", path)
    }

    private val driver = FirefoxDriver().apply {
        get("https://minesweeper.online/new-game/ng")
    }
    private val actions = Actions(driver)
    private val grid = Grid(9, 9)
    private val posToDiv = setupAndGetDivs()

    fun runSolver() {
        do {
            updateUnknownPositions()
        } while (solve1Step())

        if (grid.isNotSolved)
            println("Could not solve the grid")
    }

    private fun updateUnknownPositions() {
        for (pos in grid.unknownPositions) {
            val classes = posToDiv[pos]!!.getAttribute("class").split(" ")

            if (classes.contains("hd_opened"))
                grid[pos] = classes.first { it.startsWith("hd_type") }.last().digitToInt().asState
            else if (classes.contains("hd_flag"))
                grid[pos] = CellState.FLAG
        }
    }

    private fun setupAndGetDivs(): Map<Position, WebElement> {
        Thread.sleep(2000)
        with(driver.findElement(By.id("A43"))) {
            findElement(By.className("start")).click()
            val posToDiv = mutableMapOf<Position, WebElement>()
            for (cell in grid) posToDiv[cell.position] =
                findElement(By.id("cell_${cell.position.column}_${cell.position.row}"))
            return posToDiv
        }
    }

    private fun solve1Step(): Boolean {
        var changed = false
        grid.solve().forEach { (type, pos) ->
            when (type) {
                ActionType.OPEN -> actions.click(posToDiv[pos])
                ActionType.FLAG -> actions.contextClick(posToDiv[pos])
            }
            changed = true
        }

        actions.perform()
        return changed
    }
}