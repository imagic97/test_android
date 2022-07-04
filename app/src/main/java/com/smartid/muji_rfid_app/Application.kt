package com.smartid.muji_rfid_app

import android.app.Application
import android.content.Context
import com.tencent.bugly.crashreport.CrashReport

class Application : Application() {
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        appContext = this
        // Bugly
        CrashReport.initCrashReport(this);
    }

    companion object {
        private var appContext: Application? = null

        fun getContext(): Context {
            return appContext!!
        }
    }
}
