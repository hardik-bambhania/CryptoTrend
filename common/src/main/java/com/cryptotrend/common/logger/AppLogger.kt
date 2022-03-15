package com.cryptotrend.common.logger

import android.content.Context
import android.util.Log
import java.io.File

/**
 * AppLogger is logging class for application
 */
object AppLogger {
    private const val DIR_NAME = "log"
    private const val FILE_NAME = "cryptoTrend.log"
    lateinit var mLogDir: File
    lateinit var mLogFile: File

    fun initLogger(context: Context) {
        mLogDir = File(context.filesDir, DIR_NAME)
        mLogDir.mkdir()
        mLogFile = File(mLogDir.path, FILE_NAME)
        if (mLogFile.exists().not()) mLogFile.createNewFile()
    }

    fun info(tag: String, message: String) {
        val log = "\n"
            .plus(tag)
            .plus(" => ")
            .plus(message)
        mLogFile.appendText(log)
        Log.i(tag, message)
    }

    fun error(tag: String, message: String) {
        val log = "\n"
            .plus(tag)
            .plus(" => ")
            .plus(message)
        mLogFile.appendText(log)
        Log.e(tag, message)
    }

    fun getLogFile(): File {
        return mLogFile
    }
}