package com.example.aspectchat

import com.example.aspectchat.core.data.repositories.MainServerRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class FastifyServerInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mainServerRepository: MainServerRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun serverResponds() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainServerRepository.getSignUpResponse()
            Assert.assertEquals(String, response?.userID)
        }
    }
}