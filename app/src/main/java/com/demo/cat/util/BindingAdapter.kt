package com.demo.cat.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("url", "placeholder")
        fun loadImageWithPlaceholder(view: ImageView, url: String?, placeholderDrawable: Drawable) {
            url?.let { imageUrl ->
                imageUrl.apply {
                    view.load(this) {
                        placeholder(placeholderDrawable)
                        error(placeholderDrawable)
                    }
                }
            } ?: kotlin.run {
                view.setImageDrawable(placeholderDrawable)
            }
        }
    }
}