package com.hx.wangchao.utils

class CRCUtils {
    companion object {
        /**
         * 计算给定数据的CRC（循环冗余校验）值。
         *
         * @param data 要计算CRC值的字节数组。
         * @return 返回计算得到的CRC值，为一个16位的无符号整数。
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
    }
}