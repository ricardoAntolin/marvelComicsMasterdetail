package com.rantolin.marvelcomics.data.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
import java.text.SimpleDateFormat

/**
 * Created by ricar on 7/4/17.
 */

fun String.ToMD5(): String {
    val m = MessageDigest.getInstance("MD5")
    m.update(this.toByteArray(), 0, this.length)
    return BigInteger(1, m.digest()).toString(16)
}

fun Timestamp.toTimeString(): String {
    return SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(this)
}