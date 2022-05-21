package com.example.dermanalyze_bangkit_capstone

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Articles(
    var titleArticles: String,
    var readmorearticle: String,
    var photo: Int
):Parcelable