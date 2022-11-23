package com.leticia.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    var name: String,
    var spent: String,
    var debt: String,
    var description: String,
): Parcelable