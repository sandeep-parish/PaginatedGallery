package com.machinetask

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

class MachineApp : MultiDexApplication() {


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}