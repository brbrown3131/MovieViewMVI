package com.connriverlines.movieviewmvi

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class Utils {

    fun loadImageGlide(context: Context, imageView: ImageView, Url: String) {
        try {
            val requestOptions = RequestOptions
                .overrideOf(1920, 1080)
            Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(Url)
                .into(imageView)
        } catch (ex : Exception) {
            showToast(context, "Image exception: " + ex.toString())
        }
    }

    fun loadImage(context: Context, imageView: ImageView, Url: String) {
        val mainScope = CoroutineScope(Dispatchers.Main)
        mainScope.launch {
            try {
                val deferredBM = mainScope.async(Dispatchers.IO) {
                    getBM(Url)
                }

                val bmp = deferredBM.await()

                imageView.setImageBitmap(bmp)
            } catch (ex: Exception) {
                showToast(context, "Image exception: " + ex.toString())
            }
        }
    }

    private fun getBM(sUrl: String): Bitmap {
        val stream = URL(sUrl).openStream()
        val bm = BitmapFactory.decodeStream(stream)
        stream.close()
        return bm
    }

    fun showToast(context:Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar(pb: ProgressBar, isVisible: Boolean){
        if (isVisible){
            pb.visibility =  View.VISIBLE
        }
        else{
            pb.visibility = View.INVISIBLE
        }
    }

    // this is just a dummy method for unit testing
    fun add(a:Int, b:Int):Int {
        return a+b
    }
}