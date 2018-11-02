package inc.holdings.spruce.http.access.logs.stats.generator


/**
 * Statistics open class for grouping LogEntries by 'statsBy' function when LogEntry gets added.
 */
open class Statistics(private val statsBy: (LogEntry) -> String, private val statsByDateMap: MutableMap<String, MutableMap<String, MutableList<LogEntry>>> = hashMapOf()) {

    fun addLogEntry(logEntry: LogEntry) {
        val innerMap = statsByDateMap.getOrDefault(logEntry.date, hashMapOf())
        statsByDateMap.put(logEntry.date,innerMap)

        val listOfLogEntries = innerMap.getOrDefault(statsBy.invoke(logEntry), mutableListOf())
        listOfLogEntries.add(logEntry)
        innerMap.put(statsBy.invoke(logEntry), listOfLogEntries)
    }

     fun print(print: (LogEntry) -> String) {
        val s = StringBuilder()
        for ((date, innerMap) in statsByDateMap.entries.sortedByDescending { it.key }) {
            s.append("%s%n".format(date))
            for ((statsBy, listOfLogEntries) in innerMap.entries.sortedByDescending { it.value.size }) {
                s.append("\t%-20s %-20s %s%n".format(statsBy, listOfLogEntries.size ,listOfLogEntries.map { lr -> print.invoke(lr) }))
            }
        }
        println(s.toString())
    }
}

class PagesByUniqueHitsStats : Statistics({ logEntry: LogEntry -> logEntry.page }) {
     fun print() {
        println("______________________________________________________________________________________")
        println("Pages By Unique Hits Stats")
        super.print{logEntry: LogEntry -> logEntry.user}
        println("______________________________________________________________________________________")
    }
}

class PagesByNumberOfUsersStats : Statistics({ logEntry: LogEntry -> logEntry.page }) {
     fun print() {
        println("______________________________________________________________________________________")
        println("Pages By Number Of Users Stats")
        super.print{logEntry: LogEntry -> logEntry.user}
        println("______________________________________________________________________________________")
    }
}

class UsersByUniquePageViewsStats : Statistics({ logEntry: LogEntry -> logEntry.user }) {
     fun print() {
        println("______________________________________________________________________________________")
        println("Users By Unique Page Views Stats")
        super.print{ logEntry: LogEntry -> logEntry.page }
        println("______________________________________________________________________________________")
    }
}



