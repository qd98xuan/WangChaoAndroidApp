package com.hx.wangchao.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hx.wangchao.Dao.ClassRoomDao
import com.hx.wangchao.Entity.ClassRoomEntity
import com.hx.wangchao.application.BaseApplication

/**
 * app的数据库类
 */
@Database(entities = [ClassRoomEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val classRoomDao: ClassRoomDao

    companion object {
        // 使用单例模式确保数据库实例的唯一性
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    BaseApplication.thisActivity,
                    AppDatabase::class.java,
                    "new_station_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}