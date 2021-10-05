package com.cmf.redditposts.misc

import android.content.res.Configuration
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.cmf.redditposts.RedditPostsApp
import com.google.android.material.snackbar.Snackbar

fun FragmentActivity?.isScreenLandscape(): Boolean {
    return RedditPostsApp.instance.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

fun Int.isLandscape(): Boolean {
    return this == Configuration.ORIENTATION_LANDSCAPE
}

fun View?.showSnackbar(snackbarText: String, timeLength: Int = Snackbar.LENGTH_SHORT) {
    this?.let {
        Snackbar.make(it, snackbarText, timeLength).show()
    }
}