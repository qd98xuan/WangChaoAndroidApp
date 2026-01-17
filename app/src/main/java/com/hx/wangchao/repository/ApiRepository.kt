package com.hx.wangchao.repository

import com.hx.baselibrary.Constants
import com.hx.baselibrary.mmkv.MMKVUtils
import com.hx.baselibrary.network.RetrofitFlowWrapper
import com.hx.wangchao.Entity.ActiveRequestParam
import com.hx.wangchao.Entity.AddPerformanceEntity
import com.hx.wangchao.Entity.AttendanceSubmitEntity
import com.hx.wangchao.Entity.LoginRequest
import com.hx.wangchao.api.BaseApiService
import com.hx.wangchao.api.ClassTableApiService
import com.hx.wangchao.api.LessonApiService
import com.hx.wangchao.api.PermissionApiService
import com.hx.wangchao.api.TodoApiService
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * api的仓库
 */
object ApiRepository {
    private val retrofitFlowWrapper = RetrofitFlowWrapper.getInstance()
    private val permissionApiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, PermissionApiService::class.java)

    private val todoApiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, TodoApiService::class.java)

    private val classTableApiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, ClassTableApiService::class.java)

    private val baseApiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, BaseApiService::class.java)

    private val lessonApiService =
        retrofitFlowWrapper.create(Constants.BASE_URL, LessonApiService::class.java)


    // 登录
    suspend fun login(
        loginRequest: LoginRequest
    ) = retrofitFlowWrapper.makeApiRequest(
        permissionApiService.login(
            "22d6d05b-f9fb-4dec-a8ee-363a29d6cdbc",
            loginRequest
        )
    )

    // 获取今日课程安排
    suspend fun getTodayLessons() =
        retrofitFlowWrapper.makeApiRequest(
            todoApiService.getTodayLessons(
                Constants.getUserToken()
            )
        )


    // 获取上周课程安排
    suspend fun getTaskWeeklyLessons() =
        retrofitFlowWrapper.makeApiRequest(
            todoApiService.getTaskWeeklyLessons(
                Constants.getUserToken()
            )
        )

    // 获取周课程安排
    suspend fun getWeeklyLessons() =
        retrofitFlowWrapper.makeApiRequest(
            classTableApiService.getWeeklyLessons(
                Constants.getUserToken()
            )
        )

    // 获取教师列表
    suspend fun getTeacherList() =
        retrofitFlowWrapper.makeApiRequest(
            baseApiService.getTeacherList(
                Constants.getUserToken()
            )
        )

    // 获取场地列表
    suspend fun getSpaceList() =
        retrofitFlowWrapper.makeApiRequest(
            baseApiService.getSpaceList(
                Constants.getUserToken()
            )
        )

    // 激活课程
    suspend fun activateLesson(
        param: ActiveRequestParam
    ) = retrofitFlowWrapper.makeApiRequest(
        todoApiService.activateLesson(
            Constants.getUserToken(),
            param
        )
    )

    // 推迟课程
    suspend fun postponeLesson(
        params: Map<String, String>
    ) = retrofitFlowWrapper.makeApiRequest(
        todoApiService.postponeLesson(
            Constants.getUserToken(),
            params
        )
    )

    // 状态完成
    suspend fun completeLesson(
        params: Map<String, String>
    ) = retrofitFlowWrapper.makeApiRequest(
        todoApiService.completeLesson(
            Constants.getUserToken(),
            params
        )
    )

    // 出勤点名列表
    suspend fun getAttendanceList(
        params: Map<String, String>
    ) = retrofitFlowWrapper.makeApiRequest(
        todoApiService.getAttendanceList(
            Constants.getUserToken(),
            params
        )
    )

    // 出勤点名
    suspend fun callAttendance(
       data: AttendanceSubmitEntity
    ) = retrofitFlowWrapper.makeApiRequest(
        todoApiService.callAttendance(
            Constants.getUserToken(),
            data
        )
    )

    // Lesson上课表现列表
    suspend fun getLessonPerformanceList() =
        retrofitFlowWrapper.makeApiRequest(
            lessonApiService.getLessonPerformanceList(Constants.getUserToken())
        )

    // Lesson上课表现增加
    suspend fun addLessonPerformance(data: AddPerformanceEntity) =
        retrofitFlowWrapper.makeApiRequest(
            lessonApiService.addLessonPerformance(Constants.getUserToken(),data)
        )

    // 布置作业
    suspend fun submitHomework(body: Map<String, String>) =
        retrofitFlowWrapper.makeApiRequest(
            lessonApiService.submitHomework(
                Constants.getUserToken(),
                body
            )
        )

    // 获取作业明细
    suspend fun getHomeworkDetail(lessonId: String) =
        retrofitFlowWrapper.makeApiRequest(
            lessonApiService.getHomeworkDetail(
                Constants.getUserToken(),
                lessonId
            )
        )

    // 课堂检测列表
    suspend fun getLessonTestPaperList(lessonId: String) =
        retrofitFlowWrapper.makeApiRequest(
            lessonApiService.getLessonTestPaperList(
                Constants.getUserToken(),
                lessonId
            )
        )


}