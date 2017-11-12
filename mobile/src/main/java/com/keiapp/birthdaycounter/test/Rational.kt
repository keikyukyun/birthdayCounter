package com.keiapp.birthdaycounter.test

/**
 * class クラス名(a, b)　←プライマリコンストラクタ。
 * 初期化したい場合はinit{}(イニシャライザ)で処理を行う。
 */
class Rational(n: Int, d: Int) {
    init {
        require(d != 0, { "denominator must not be null" }) // 要求に反した場合は、illegalArgumentExceptionがthrowされる
    }

    val str: String by lazy { "初期化します" } // 遅延初期化（by ** は移譲プロパティのこと。
    private val g by lazy { gcd(Math.abs(n), Math.abs(d)) }
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

    // ローカル関数
    /**
     * goがローカル関数で、再帰呼出しを行っている。
     * ローカル関数goをreturnで呼び出している。今まで一つのメソッドだけで呼び出していたprivateなメソッドが
     * ローカルで使用できるのでスコープ制限もできるので便利！！
     */
    fun sum(numbers: List<Long>): Long {
        tailrec fun go(numbers: List<Long>, accumulator: Long): Long =
                if (numbers.isEmpty()) accumulator
                else go(numbers.drop(1), accumulator + numbers.first())
        return go(numbers, 0)
    }

    // ↓
    // 上のsumはラムダを使っていい感じにできるのか
    fun sumsum(numbers: List<Long>): Long {
        // これはだめ
        val go: (List<Long>, Long) -> Long = { numbers: List<Long>, accumulator: Long ->
            if (numbers.isEmpty()) accumulator
            else go(numbers.drop(1), accumulator + numbers.first())
        }

        // 再帰呼び出しができない？要確認
    }

}