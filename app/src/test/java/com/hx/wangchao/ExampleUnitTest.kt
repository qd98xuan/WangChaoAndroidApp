package com.hx.wangchao

import com.blankj.utilcode.util.ConvertUtils
import com.hx.wangchao.utils.CRC_8MAXIMUtils
import com.hx.wangchao.utils.HexUtils
import com.hx.wangchao.utils.heigh8
import com.hx.wangchao.utils.hex2bytearray
import com.hx.wangchao.utils.int2hex
import com.hx.wangchao.utils.low8
import org.junit.Test
import tp.xmaihh.serialport.utils.ByteUtil

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testInteger() {
        val unsignedInt: UShort = 65534U
        val signedInt: Short = unsignedInt.toShort()

// 使用位运算符按位取反
//        val result: Int = signedInt.inv()

        println(signedInt.toInt())
    }

    @Test
    fun addition_isCorrect() {
//        var data = 255
//        print("低字节：${data.low8()}\n")
//        print("高字节：${data.heigh8()}\n")

//        var hight = 113
//        var low = 83
//
//
//
//        print(dataBase)
//        print("律动：${RhythmType.RhythmFuncCode.ReadRhythmAmplitude.code}\n")

        val hex = ConvertUtils.int2HexString(5)
        val convertHex = if (hex.length == 1) {
            "0$hex"
        } else {
            hex
        }
        print(convertHex)

    }

    @Test
    fun string2Byte() {
        val s = "F7F8050154011062FD"
        print("我的：")
        s.hex2bytearray().forEach {
            print(it)
        }

        print("他的：")
        ByteUtil.HexToByteArr(s).forEach {
            print(it)
        }

    }

    @Test
    fun testHeight() {
        val originalMin = 0
        val originalMax = 10
        val newMin = 75
        val newMax = -75

        // 使用线性映射将值从原始范围映射到新范围
        for (i in 0..10) {
            print((((i - originalMin) * (newMax - newMin) / (originalMax - originalMin)) + newMin).toFloat())
        }

    }

    @Test
    fun testHeighLowHex() {
        val data10 = 60677
        val low8 = data10.low8()
        val heigh8 = data10.heigh8()
        print("数据的低八位${low8}\n")
        print("数据的高八位${heigh8}\n")
        val data10Convert = HexUtils.combineHexToDecimal(heigh8, low8)
        print("拼接完的${data10Convert}\n")
    }

    /**
     * CRC校验高字节
     */
    fun calculateCRCHigh(data: ByteArray): Int {
        var crc = 0xFFFF // 初始化CRC校验码
        val polynomial = 0xA001 // CRC-16 MODBUS 的多项式

        for (b in data) {
            var temp = b.toInt() and 0xFF
            crc = crc xor temp
            for (i in 0 until 8) {
                if ((crc and 0x0001) != 0) {
                    crc = (crc shr 1) xor polynomial
                } else {
                    crc = crc shr 1
                }
            }
        }
        return crc shr 8 // 返回CRC校验码的高8位
    }

    /**
     * CRC校验低字节
     */
    fun calculateCRCLow(data: ByteArray): Int {
        var crc = 0xFFFF // 初始化CRC校验码
        val polynomial = 0xA001 // CRC-16 MODBUS 的多项式

        for (b in data) {
            var temp = b.toInt() and 0xFF
            crc = crc xor temp
            for (i in 0 until 8) {
                if ((crc and 0x0001) != 0) {
                    crc = (crc shr 1) xor polynomial
                } else {
                    crc = crc shr 1
                }
            }
        }
        return crc and 0xFF // 返回CRC校验码的低8位
    }

    fun combineCRC(crcHigh: Int, crcLow: Int): Int {
        return (crcHigh shl 8) or crcLow // 将CRC高8位左移8位，然后与CRC低8位进行或运算，得到16位CRC校验码
    }

    /**
     * 测试CRC校验
     */
    @Test
    fun testCrc() {
        val dataHexString = "0164FA000000000000"
        val heigh = calculateCRCHigh(dataHexString.hex2bytearray())
        val low = calculateCRCLow(dataHexString.hex2bytearray())
        println("高八位:${heigh}")
        println("低八位:${low}")
        println(low.int2hex() + heigh.int2hex())

    }

    @Test
    fun jsiuanTime() {
        val timestamp = 1710936963 + 3600 * 8
        val secondDay = (timestamp % 86400)
        print(secondDay / 3600)
    }

    @Test
    fun tex() {
        val data = 300
        val data1 = 249
        val str = generateRhythmDeviceData(
            "10",
            "${data.heigh8()}${data.low8()}",
            "${data1.heigh8()}${data1.low8()}",
            "64"
        )
        println("=========data:${str}========")
    }

    fun generateRhythmDeviceData(
        funcCode: String,
        dataCode: String,
        dataCode2: String,
        markCode: String
    ): String {
        // 从机码
        var start = "01"
        // 功能码
        var deviceCode = ""
        // 所有拼接
        var generateData = ""
        // 需要计算校验码的数据
        var needCheckCodeData = ""
        // 字节数
        var byteLength = "04"

        // 校验码
        var checkCode = ""
        when (funcCode) {
            //振幅和频率
            "10" -> {
                if (dataCode2.isNullOrBlank()) {
                    needCheckCodeData =
                        start + funcCode + "00" + markCode + "00" + "02" + byteLength + dataCode + "00" + "00"
                } else {
                    needCheckCodeData =
                        start + funcCode + "00" + markCode + "00" + "02" + byteLength + dataCode + dataCode2
                }

            }
            //开始和结束
            "06" -> {
                needCheckCodeData = start + funcCode + "00" + markCode + dataCode

            }
            //读故障
            "03" -> {
                needCheckCodeData = start + funcCode + "00" + markCode + "00" + "04"

            }

        }
//        val value = calculateCRC(needCheckCodeData.hex2bytearray())
//        val str = "${value.heigh8()}${value.low8()}"
//        checkCode = str
//
//        generateData = needCheckCodeData + checkCode
        return generateData
    }


    @Test
    fun testCRC8() {
        println(CRC_8MAXIMUtils.crc8("016400282800000100".hex2bytearray()).toString(16))
    }

    @Test
    fun testNumber() {
        println(
            "5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555".length
        )

    }
}