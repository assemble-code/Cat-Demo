package com.demo.cat.model.breedlistresponse


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Weight(
    @Json(name = "imperial")
    val imperial: String?,
    @Json(name = "metric")
    val metric: String?
): Parcelable