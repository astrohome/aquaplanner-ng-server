package org.galaxysoft.aquaplannerserver

class TimeUtilsTests {
/*
    private lateinit var tasks: List<LedTask>

    @Before
    fun setup() {
        tasks = listOf(
                LedTask(7, LedPhysicalChannel.C1, 10, 70, LocalTime.of(9, 0), LocalTime.of(10, 0)),
                LedTask(8, LedPhysicalChannel.C1, 10, 70, LocalTime.of(10, 0), LocalTime.of(11, 0)),
                LedTask(9, LedPhysicalChannel.C1, 10, 70, LocalTime.of(9, 0), LocalTime.of(11, 1)),

                LedTask(2, LedPhysicalChannel.C1, 10, 70, LocalTime.of(23, 31), LocalTime.of(1, 0)),
                LedTask(3, LedPhysicalChannel.C1, 10, 70, LocalTime.of(20, 0), LocalTime.of(1, 0)),
                LedTask(4, LedPhysicalChannel.C1, 10, 70, LocalTime.of(23, 0), LocalTime.of(23, 20)),

                LedTask(5, LedPhysicalChannel.C1, 10, 70, LocalTime.of(23, 0), LocalTime.of(5, 20)),
                LedTask(6, LedPhysicalChannel.C1, 10, 70, LocalTime.of(21, 0), LocalTime.of(21, 5))
        )
    }

    @Test
    fun testBetween() {
        assertAll("between",
                Executable { assertEquals(60, TimeUtils.between(LocalTime.of(9, 0), LocalTime.of(10, 0))) },
                Executable { assertEquals(60, TimeUtils.between(LocalTime.of(23, 30), LocalTime.of(0, 30))) },
                Executable { assertEquals(120, TimeUtils.between(LocalTime.of(23, 0), LocalTime.of(1, 0))) }
        )
    }

    private fun filterTasks(it: LedTask, now: LocalTime): Boolean {
        return TimeUtils.isInInterval(it.startTime, it.endTime, now)
    }

    @Test
    fun testFilterTasks1() {
        val list = tasks.filter { filterTasks(it, LocalTime.of(9, 30)) }
        assertEquals(2, list.size)
        assertTrue(list.map { it.id }.containsAll(listOf(7, 9)))
    }

    @Test
    fun testFilterTasks2() {
        val list = tasks.filter { filterTasks(it, LocalTime.of(23, 30)) }
        assertEquals(2, list.size)
        assertTrue(list.map { it.id }.containsAll(listOf(3, 5)))
    }

    @Test
    fun testFilterTasks3() {
        val list = tasks.filter { filterTasks(it, LocalTime.of(0, 15)) }
        assertEquals(3, list.size)
        assertTrue(list.map { it.id }.containsAll(listOf(2, 3, 5)))
    }

    @Test
    fun testNoTasks() {
        val list = tasks.filter { filterTasks(it, LocalTime.of(15, 15)) }
        assertEquals(0, list.size)
    }

    @Test
    fun testTheLastMinuteOfTask() {
        val list = tasks.filter { filterTasks(it, LocalTime.of(11, 1)) }
        assertEquals(1, list.size)
    }

*/
}
