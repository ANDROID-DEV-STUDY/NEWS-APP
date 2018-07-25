package com.kevin.newsapp.util.glide

import android.graphics.*
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

class RoundedCornersTransformation: BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(RoundedCornersTransformation::class.java.simpleName.toByteArray(Key.CHARSET))
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val width = toTransform.width
        val height = toTransform.height

        val bitmap = pool
                .get(width, height, Bitmap.Config.ARGB_8888)
                .apply { setHasAlpha(true) }

        val canvas = Canvas(bitmap)

        val paint = Paint()
                .apply {
                    isAntiAlias = true
                    shader = BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                }

        canvas.drawRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), 10f, 10f, paint)

        return bitmap
    }
}