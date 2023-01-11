package com.example.siolab.common.timber

import timber.log.Timber

class TimberDebugTree: Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "${element.fileName} : "
    }
}