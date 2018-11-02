package inc.holdings.spruce.http.access.logs.stats.generator

import java.io.BufferedReader
import java.io.File
import kotlin.system.measureTimeMillis


val usersByUniquePageViewsStats = UsersByUniquePageViewsStats()
val pagesByUniqueHitsStats = PagesByUniqueHitsStats()
val pagesByNumberOfUsersStats = PagesByNumberOfUsersStats()

/**
 * Main entry  point for this application
 *
 * @author Dhruv Patel
 */
fun main(args: Array<String>) {
    val ms = measureTimeMillis {
        //check to see if no args are passed into the program
        if (args.isEmpty()) {
            println("Please provide access log csv file location.")
            return
        }

        val accessLogPath = args[0] // get access log csv location from application arguments
        if (!accessLogPath.endsWith(".csv", true)) {
            println("Invalid file type. please provide valid access log in 'csv' format")
            return
        }
        try {
            val accessLogFile = File(accessLogPath)
            if (!accessLogFile.exists()) {
                println("file doesn't exist. Please provide valid access log csv file location.")
                return
            }
            val reader = accessLogFile.bufferedReader()
            reader.use {
                // auto close the reader after reading is done so it will release resources
                it.lines()
                        .skip(1) // skip header from csv
                        .map { line -> line.split(",").map { it.trim() } } // map csv line into List<String>
                        .map { line -> LogEntry(line) } // map List<String> to LogEntry object
                        .peek { logEntry -> pagesByNumberOfUsersStats.addLogEntry(logEntry) } // add log entry into pagesByNumberOfUsersStats. if we need unique # user then add this call after distinct but then it will be same as pagesByUniqueHitsStats
                        .distinct() // filter to only distinct log entry per day as LogEntry only stores day part
                        .forEach { logEntry ->
                            // add each distinct log entry into pagesByUniqueHitsStats and usersByUniquePageViewsStats
                            pagesByUniqueHitsStats.addLogEntry(logEntry)
                            usersByUniquePageViewsStats.addLogEntry(logEntry)
                        }
            }

            // prints stats gathered by reading file
            pagesByUniqueHitsStats.print()
            pagesByNumberOfUsersStats.print()
            usersByUniquePageViewsStats.print()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    println("time took to complete program exec $ms ms")
}

/**
 * data class which represent LogEntry .
 *
 * Note: here I have used date as String because parsing date in java.time.LocalDate was taking too much time.
 */
data class LogEntry(val page: String, val user: String, val date: String) {
    constructor(stringArr: List<String>) : this(stringArr[0], stringArr[1], stringArr[2].substring(0..9))
}
