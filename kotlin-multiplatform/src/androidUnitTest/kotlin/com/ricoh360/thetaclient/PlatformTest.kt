package com.ricoh360.thetaclient

actual fun getEnv(name: String): String? = System.getenv(name)
