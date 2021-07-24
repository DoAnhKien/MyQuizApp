package com.example.quizapp.until

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("image_url")
fun setImageResource(imageView: ImageView, url: String?) {
    Glide.with(imageView?.context).load(url)
        .thumbnail(0.1f)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("int_to_string")
fun setImageResource(tv: TextView, number: Int) {
    tv.text = number.toString()
}

