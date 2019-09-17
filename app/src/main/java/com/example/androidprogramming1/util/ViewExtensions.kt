package com.example.androidprogramming1.util

import android.view.View

fun View.toggleVisibility()
{
    visibility = if (visibility == View.VISIBLE) {View.INVISIBLE} else {View.VISIBLE}
}