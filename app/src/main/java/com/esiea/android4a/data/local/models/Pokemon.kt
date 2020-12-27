package com.esiea.android4a.data.local.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @Json(name = "id") val id: Int,
    @Json(name = "num") val num: String,
    @Json(name = "name") val name: String,
    @Json(name = "img") val img: String,
    @Json(name = "type") val type: List<String>,
    @Json(name = "height") val height: String,
    @Json(name = "weight") val weight: String,
    @Json(name = "multipliers") val multipliers: List<Double>?,
    @Json(name = "weaknesses") val weaknesses: List<String>

) : Parcelable