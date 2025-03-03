package com.example.aspectchat

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import java.io.File


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class DeviceMemoryInfo {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Test
    fun deviceMemoryInfo() {
        println(
            "Available processors (cores): " +
                    Runtime.getRuntime().availableProcessors()
        )

        /* Total amount of free memory available to the JVM */
        println(
            "Free memory (bytes): " +
                    Runtime.getRuntime().freeMemory()
        )

        /* This will return Long.MAX_VALUE if there is no preset limit */
        val maxMemory = Runtime.getRuntime().maxMemory()
        /* Maximum amount of memory the JVM will attempt to use */
        println(
            "Maximum memory (bytes): " +
                    (if (maxMemory == Long.MAX_VALUE) "no limit" else maxMemory)
        )

        /* Total memory currently in use by the JVM */
        println(
            "Total memory (bytes): " +
                    Runtime.getRuntime().totalMemory()
        )

        /* Get a list of all filesystem roots on this system */
        val roots = File.listRoots()

        /* For each filesystem root, print some info */
        for (root in roots) {
            println("File system root: " + root.absolutePath)
            println("Total space (bytes): " + root.totalSpace)
            println("Free space (bytes): " + root.freeSpace)
            println("Usable space (bytes): " + root.usableSpace)
        }
    }
}