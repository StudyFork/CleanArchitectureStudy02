package com.tistory.blackjin.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

class CoroutineDrawPerson {

    @Test
    fun main() {
        drawPersonA()
        drawPersonB()
        println("main")

        Thread.sleep(5000)
    }

    private fun drawPersonA() {
        GlobalScope.launch {
            drawHeadA()
            drawBodyA()
            drawLegsA()
        }
    }

    suspend fun drawHeadA() {
        delay(200)
        println("${Thread.currentThread().name} drawHeadA")
    }

    suspend fun drawBodyA() {
        delay(1000)
        println("${Thread.currentThread().name} drawBodyA")
    }

    suspend fun drawLegsA() {
        delay(500)
        println("${Thread.currentThread().name} drawLegsA")
    }

    private fun drawPersonB() {
        GlobalScope.launch {
            drawHeadB()
            drawBodyB()
            drawLegsB()
        }
    }

    suspend fun drawHeadB() {
        delay(500)
        println("- ${Thread.currentThread().name} drawHeadB")
    }

    suspend fun drawBodyB() {
        delay(1000)
        println("- ${Thread.currentThread().name} drawBodyB")
    }

    suspend fun drawLegsB() {
        delay(200)
        println("- ${Thread.currentThread().name} drawLegsB")
    }

}
