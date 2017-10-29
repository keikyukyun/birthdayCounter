package com.keiapp.birthdaycounter.test

import org.junit.Test

/**
 * Created by murakamikei on 2017/10/14.
 */
class RationalTest {
    @Test
    fun check() {
        val rat = Rational(55, 100)
        System.out.println(rat.toString())
    }

    @Test
    fun lazyCheck() {
        val rat = Rational(17, 17)
        System.out.println(rat.str)
    }
}