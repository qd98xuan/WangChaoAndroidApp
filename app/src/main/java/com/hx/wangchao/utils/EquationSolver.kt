package com.hx.wangchao.utils
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin

data class DataPoint(val p: Double, val v: Double)
data class Result(val index: Int, val a: Double, val b: Double)
/**
 * 电光性能测试方程求解工具类
 */
class EquationSolver {
    /**
     * 求解单组 A 和 B
     * 方程: P = A * sin(V / B * PI / 2)
     *
     * @param p1 第一个点的 P 值
     * @param v1 第一个点的 V 值
     * @param p2 第二个点的 P 值
     * @param v2 第二个点的 V 值
     * @return Pair(A, B) 或者 null (如果无解)
     */
    fun solvePair(p1: Double, v1: Double, p2: Double, v2: Double): Pair<Double, Double>? {
        // 目标比率
        val targetRatio = p1 / p2

        // 使用二分法求解 B
        // 我们需要寻找一个 B，使得 sin(k * V1 / B) / sin(k * V2 / B) - targetRatio = 0
        // 令 k = PI / 2
        // 函数 f(B) = sin(V1 * PI / (2 * B)) * P2 - sin(V2 * PI / (2 * B)) * P1 = 0

        // 定义搜索范围，假设 B 不会非常小也不会无穷大，根据实际物理意义调整
        // 这里的范围仅作示例，实际使用可能需要根据 V 的量级调整
        var low = 1e-6
        var high = 10000.0
        var bSolution: Double? = null

        val tolerance = 1e-7
        val maxIterations = 1000

        for (i in 0 until maxIterations) {
            val mid = (low + high) / 2.0
            val valMid = equationError(mid, p1, v1, p2, v2)

            if (abs(valMid) < tolerance) {
                bSolution = mid
                break
            }

            // 判断符号变化来决定缩减区间
            // 注意：三角函数的周期性可能导致多解，这里假设寻找主值
            // 为了简化，我们假设函数在区间单调（小范围内）
            // 实际上 f(low) * f(mid) < 0 说明根在左侧
            if (equationError(low, p1, v1, p2, v2) * valMid < 0) {
                high = mid
            } else {
                low = mid
            }
        }

        if (bSolution == null) return null

        // 求出 B 后代入求 A
        // A = P1 / sin(V1 / B * PI / 2)
        val sinTerm = sin(v1 / bSolution * PI / 2)
        if (abs(sinTerm) < 1e-9) return null // 避免除以零

        val aSolution = p1 / sinTerm

        return Pair(aSolution, bSolution)
    }

    // 误差函数：理想情况下该函数值为 0
    private fun equationError(b: Double, p1: Double, v1: Double, p2: Double, v2: Double): Double {
        val term1 = sin((v1 * PI) / (2 * b)) * p2
        val term2 = sin((v2 * PI) / (2 * b)) * p1
        return term1 - term2
    }

    /**
     * 处理列表数据
     */
    fun processList(dataPoints: List<DataPoint>): List<Result> {
        val results = mutableListOf<Result>()

        // 遍历数据，每次取 i 和 i+1 进行联立
        for (i in 0 until dataPoints.size - 1) {
            val d1 = dataPoints[i]
            val d2 = dataPoints[i+1]

            val solution = solvePair(d1.p, d1.v, d2.p, d2.v)

            if (solution != null) {
                results.add(Result(i + 1, solution.first, solution.second))
            } else {
                println("第 ${i+1} 组无法求解")
            }
        }
        return results
    }
}

// Example usage
fun main() {
    // 示例数据
    // 假设真实 A=10, B=100
    // P1 = 10 * sin(20/100 * PI/2) ≈ 10 * sin(0.314) ≈ 3.09
    // P2 = 10 * sin(40/100 * PI/2) ≈ 10 * sin(0.628) ≈ 5.87
    // P3 = 10 * sin(60/100 * PI/2) ≈ 10 * sin(0.942) ≈ 8.09

    val inputs = listOf(
        DataPoint(3.09016994, 20.0),
        DataPoint(5.87785252, 40.0),
        DataPoint(8.09016994, 60.0),
        DataPoint(9.51056516, 80.0)
    )

    val solver = EquationSolver()
    val results = solver.processList(inputs)

    println("求解结果 (An, Bn):")
    results.forEach {
        println("组 ${it.index}: A = %.4f, B = %.4f".format(it.a, it.b))
    }
}