package com.hx.baselibrary

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.blankj.utilcode.util.DeviceUtils
import com.google.gson.Gson
import com.hx.baselibrary.mmkv.MMKVUtils

/**
 *  一些常量
 */
class Constants {
    companion object {
        // 域名的
        private const val BASE_URL_DEBUG = "http://health.triplemaster.com"

        // iot的地址
//        const val BASE_URL_DEBUG_8097 = "http://112.124.64.250:8097"
        const val BASE_URL_DEBUG_8097 = BASE_URL_DEBUG

        // api的地址
//        const val BASE_URL_DEBUG_8095 = "http://112.124.64.250:8095"
        const val BASE_URL_DEBUG_8095 = BASE_URL_DEBUG
        const val BASE_URL_RELEASE = "http://health.triplemaster.com"

        // 用户协议地址
//        const val YHXY_URL = "http://qdss.triplemaster.com/appdownload/fuwu.html"
        const val YHXY_URL = "http://qdss.triplemaster.com/appdownload/Androidyonghu.html"

        // 隐私政策地址
//        const val YSZC_URL = "http://qdss.triplemaster.com/appdownload/yinsi.html"
        const val YSZC_URL = "http://qdss.triplemaster.com/appdownload/Androidyinsi.html"

        // 会员服务协议
//        const val HYFWXY_URL = "https://health.epolecare.com/privacy/vipagreement.html"
        const val HYFWXY_URL = "http://qdss.triplemaster.com/appdownload/Androidhuiyuan.html"

        //    public final static String BASE_URL = "https://health.epolecare.com";
        /**
         * mqtt上报数据的topic
         */
        var MQTT_REPORT_SPORTDATA_TOPIC =
            "/health/i6kxG0pQ442/${MMKVUtils.getString(Constants.OAID)}/property/post"

        /**
         * 存储OAID(平板唯一标识)的key
         */
        const val OAID = "OAID"

        /**
         * 存储设备的SN(设备唯一标识)的key
         */
        const val DEVICE_SN = "DEVICE_SN"

        /**
         * 存储设备的类型的key
         */
        const val DEVICE_TYPE = "DEVICE_TYPE"

        /**
         * 器械名称
         */
        const val KEY_DEVICEDESC = "KEY_DEVICEDESC"

        /**
         * mqtt地址
         */
        val MQTT_URL = "tcp://112.124.64.250:1883"

        /**
         * Mqtt 用户名
         */
        const val MQTT_USER_NAME = "admin"

        /**
         * Mqtt 密码
         */
        const val MQTT_PASSWORD = "Sanshuo123"

        // MQTT查询用户状态的Topic
        val MQTT_LOGIN_TOPIC =
            "/health/i6kxJ4G38EK/" + DeviceUtils.getAndroidID() + "/service/qr"

        // 用户token
        const val KEY_TOKEN = "KEY_TOKEN"

        // 用户名
        const val KEY_NAME = "KEY_NAME"

        // 用户年龄
        const val KEY_AGE = "KEY_AGE"

        // 用户头像
        const val KEY_AVATAR = "KEY_AVATAR"

        // 重力系数
        const val KEY_WEIGHT_LIST = "KEY_WEIGHT_LIST"

        // 被动模式转换系数
        const val KEY_COE = "KEY_COE"

        // 波浪曲线模式
        const val KEY_FUN = "KEY_FUN"

        // 用户ID
        const val KEY_USER_ID = "KEY_USER_ID"

        // 设备高阈值
        const val KEY_HIGHT = "KET_HEIGHT"

        // 热量系数
        const val KEY_CALIDX = "KEY_CALIDX"

        // 设备低阈值
        const val KEY_LOW = "KET_LOW"

        // 运动目标
        const val KEY_SPORT_TARGET = "KEY_SPORT_TARGET"

        // 开启系统后自动打开服务
        const val KEY_START_SERVICE_AUTO = "KEY_START_SERVICE_AUTO"

        // 设备的大类 液压缸 律动机 伺服电机 ...
        const val KEY_SERIAL_DEVICE_TYPE = "KEY_SERIAL_DEVICE_TYPE"

        // 波特率
        const val KEY_SERIAL_PORT_BAUDRATE = "KEY_SERIAL_PORT_BAUDRATE"

        // 串口号
        const val KEY_SERIAL_PORT_CONFIG = "KEY_SERIAL_PORT_CONFIG"

        // 蓝牙Mac地址
        const val KEY_DEVICE_ADDRESS = "KEY_DEVICE_ADDRESS"

        // 是否是离线模式
        const val KEY_OFFLINE_MODE = "KEY_OFFLINE_MODE"

        fun getUserToken() =
            "Bearer ${MMKVUtils.getString(KEY_TOKEN) ?: ""}"

        // 清除数据
        fun clearData() {
            MMKVUtils.put(KEY_TOKEN, "")
        }

        // 获取设备类型
        fun getSerialDeviceType(): String {
            return MMKVUtils.getString(KEY_SERIAL_DEVICE_TYPE) ?: HYDRAULIC_CYLINDER
        }

        // 设置设备类型
        fun setSerialDeviceType(type: String) {
            MMKVUtils.put(KEY_SERIAL_DEVICE_TYPE, type)
        }

        // 设置离线模式
        fun setOfflineMode(isOffline:Boolean = true) {
            MMKVUtils.put(KEY_OFFLINE_MODE, isOffline)
        }

        // 获取当前模式
        fun getIsOfflineMode():Boolean {
            return MMKVUtils.getBoolean(KEY_OFFLINE_MODE) == true
        }


        // 设备类型
        // 液压缸
        const val HYDRAULIC_CYLINDER = "HYDRAULIC_CYLINDER"
        // 消费平板液压缸
        const val HYDRAULIC_CYLINDER_FOR_CUSTOM = "HYDRAULIC_CYLINDER_FOR_CUSTOM"

        // 电缸
        const val ELECTRIC_CYLINDER = "ELECTRIC_CYLINDER"

        // 律动机
        const val RHYTHMIC_MOTIVATION = "RHYTHMIC_MOTIVATION"

        // 盘式电机
        const val DISC_MOTOR = "DISC_MOTOR"

        // 上下肢主被动
        const val UPPER_AND_LOWER_LIMBS = "UPPER_AND_LOWER_LIMBS"

        // 四肢联动
        const val ALL_FOURS = "ALL_FOURS"

        // 跑步机
        const val TREADMILLSPORT = "TREADMILLSPORT"

        // 电缸器械运动类型菜单配置 电缸设备（力量器械）当前需要展示的运动类型
        const val ELECTRIC_CYLINDER_SPORT_TYPE_MENU = "ELECTRIC_CYLINDER_SPORT_TYPE_MENU"

        // 上控的主板芯片
        val KEY_CPU_CORE = CPU_CORE.LZTEK


        // 字体
        val BEBAS = FontFamily(Font(R.font.bebas))
        val BOLD = FontFamily(Font(R.font.bold))
        val MEDIUM = FontFamily(Font(R.font.medium))
        val REGULAR = FontFamily(Font(R.font.regular))
        val SCORE = FontFamily(Font(R.font.score))
        val SEMIBOLD = FontFamily(Font(R.font.semibold))
        val BEBAS_NEUE = FontFamily(Font(R.font.bebas_neue))
    }
}

/**
 * 上控的主板芯片
 */
enum class CPU_CORE(val type: String) {
    // LZTEK
    LZTEK("LZTEK"),
    // HUIDUTEK
    HUIDUTEK("HUIDUTEK"),
    // DACONG
     DACONG("DACONGTEK"),
    // 其他芯片
    OTHERS("OTHERS")
}