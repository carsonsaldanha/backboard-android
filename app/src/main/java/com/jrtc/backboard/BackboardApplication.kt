package com.jrtc.backboard

import android.app.Application
import com.google.android.material.color.DynamicColors

class BackboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

}