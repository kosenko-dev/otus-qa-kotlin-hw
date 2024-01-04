class TestRunnerImpl : TestRunner {
    override fun <T> runTest(steps: T, test: () -> Unit) {
        steps!!::class.members.filter { it.name.startsWith("before") }.sortedBy { it.name }
            .forEach { it.call(steps) }
        test()
        steps!!::class.members.filter { it.name.startsWith("after") }.sortedByDescending { it.name }
            .forEach { it.call(steps) }
    }
}