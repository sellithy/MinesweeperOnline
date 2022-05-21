object Resources {
    private fun getResource(string: String) = Resources::class.java.getResource(string)!!

    val FirefoxDriverPath by lazy { getResource("geckodriver.exe") }
}