package com.keiapp.birthdaycounter.test

/**
 * class クラス名(a, b)　←プライマリコンストラクタ。
 * 初期化したい場合はinit{}(イニシャライザ)で処理を行う。
 */
class Rational(n: Int, d: Int) {
    init {
        require(d != 0, { "denominator must not be null" }) // 要求に反した場合は、illegalArgumentExceptionがthrowされる
    }

    val str: String by lazy { "初期化します" }
    private val g by lazy { gcd(Math.abs(n), Math.abs(d))}
    private val numerator: Int = n / g
    private val denominator: Int = d / g

    fun plus(that: Rational): Rational =
            Rational(
                    numerator * that.denominator + that.numerator * denominator,
                    denominator * that.denominator
            )

    override fun toString(): String = numerator.toString() + "/" + denominator.toString()

    // tailrec修飾子は末尾再帰を表明する
    // 最大公約数を返す。
    tailrec private fun gcd(a: Int, b: Int): Int =
            if (b == 0) a
            else gcd(b, a % b)
}