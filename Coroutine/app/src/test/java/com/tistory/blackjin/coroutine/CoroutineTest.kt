package com.tistory.blackjin.coroutine

import kotlinx.coroutines.*
import org.junit.Test

class CoroutineTest {

    //job : 코루틴의 상태를 가지고 있다.
    @Test
    fun waitingJob() {
        runBlocking {
            val job = GlobalScope.launch {
                // launch a new coroutine and keep a reference to its Job
                delay(1000L)
                println("World!")
            }
            println("Hello,")
            job.join()  // wait until child coroutine completes
        }
    }

    @Test
    fun scopeBuilder() {
        runBlocking {
            // this: CoroutineScope
            launch {
                delay(200L)
                println("Task from runBlocking")
            }

            coroutineScope {
                // Creates a coroutine scope
                launch {
                    delay(500L)
                    println("Task from nested launch")
                }

                delay(100L)
                println("Task from coroutine scope") // This line will be printed before the nested launch
            }

            println("Coroutine scope is over") // This line is not printed until the nested launch completes
        }
    }
}
