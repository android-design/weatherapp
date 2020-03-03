package com.fedorov.weatherapp.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("id", "errorImage")
fun loadImage(view: ImageView, id: Int, errorImage: Drawable) {
    Picasso.get().load(id).into(view)
}