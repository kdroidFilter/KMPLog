package com.kdroid.kmplog

import com.kdroid.kmplog.core.*
import com.kdroid.kmplog.websocket.WebSocketService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Log {

    init {
        GlobalScope.launch {  WebSocketService.initWebSocketConnection() }
        initializeCrashHandler()
    }

    private var logLevel = DEBUG
    private var isDevelopmentMode = false
    private var isBroadcastingMode = false

    fun isLoggable(tag: String, level: Int): Boolean {
        return isDevelopmentMode && level >= logLevel
    }

    fun setLogLevel(level: Int) {
        logLevel = level
    }

    fun setDevelopmentMode(isDevelopment: Boolean) {
        isDevelopmentMode = isDevelopment
    }

    fun enableBroadcastingMode(ip: String? = null, port: Int = DEFAULT_SERVICE_PORT) {
        isBroadcastingMode = true
        WebSocketService.setCustomConnectionParams(ip, port)
    }

}




fun printAndGetLocalLog(priority: Int, tag: String, message: String) : LogMessage {
    val timestamp = getCurrentDateTime()
    val priorityChar = getPriorityChar(priority)
    val formattedTag = formatTag(tag)
    val formattedMsg = formatMessage(message)
    val formatedLogMessage = "${getCurrentDateTime()}  $formattedTag  $priorityChar  $formattedMsg"
    val color = getColor(priority)
    println("$color$formatedLogMessage$RESET")
    return LogMessage(timestamp = timestamp, priority = priority, tag = tag, message = message)
}

expect fun printAndSendLog(priority: Int, tag: String, msg: String)


expect fun Log.v(tag: String, msg: String)
expect fun Log.d(tag: String, msg: String)
expect fun Log.i(tag: String, msg: String)
expect fun Log.w(tag: String, msg: String)
expect fun Log.e(tag: String, msg: String, throwable: Throwable? = null)
expect fun Log.wtf(tag: String, msg: String)
expect fun Log.println(priority: Int, tag: String, msg: String)


