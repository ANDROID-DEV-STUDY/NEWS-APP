package com.kevin.newsapp.util.databinding

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kevin.newsapp.util.extensions.gone
import com.kevin.newsapp.util.glide.RoundedCornersTransformation
import com.kevin.newsapp.util.other.log
import java.util.*

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String?){
    url?.takeIf { it.isNotBlank() && !it.startsWith("/") }
            ?.let {
                Glide.with(context)
                        .load(it)
                        .apply(RequestOptions.centerCropTransform())
                        .listener(object: RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                log(message = "onLoadFailed")
                                gone() // not properly working
                                return true
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                return false
                            }
                        })
                        .into(this)
            }
            ?:gone()
}

// UNUSED
@BindingAdapter("setRoundedImageUrl")
fun ImageView.setRoundedImageUrl(url: String?) {
    url?.let {
        Glide.with(context)
                .load(it)
                .apply(RequestOptions.bitmapTransform(
                        MultiTransformation<Bitmap>(CenterCrop(), RoundedCornersTransformation())
                ))
                .into(this)
    }
}

@BindingAdapter("setDate")
fun TextView.setDate(date: Date?) {
    val calendar = Calendar.getInstance().apply { time = date }
    val actualMonth = calendar.get(Calendar.MONTH) + 1
    val date = calendar.get(Calendar.DATE)

    text = "${actualMonth}월 ${date}일"
}

@BindingAdapter("setSource")
fun TextView.setSource(name: String?) {
    name?.let {
        val source =
                when(it) {
                    "Chosun.com" -> "조선일보"
                    "Joins.com" -> "중앙일보"
                    "Donga.com" -> "동아일보"
                    "Hani.co.kr" -> "한겨레"
                    "Ytn.co.kr" -> "YTN"
                    "Sbs.co.kr" -> "SBS"
                    "Kbs.co.kr" -> "KBS"
                    "Mbc.co.kr" -> "MBC"
                    "Mk.co.kr" -> "매일경제"
                    "Yna.co.kr" -> "연합뉴스"
                    "Voakorea.com" -> "VOA"
                    "Mt.co.kr" -> "머니투데이"
                    "Heraldcorp.com" -> "HERALD"
                    "Zdnet.co.kr" -> "ZDNET"
                    "Pressian.com" -> "프레시안"
                    "Jejusori.net" -> "제주의 소리"
                    "Newstomato.com" -> "뉴스토마토"
                    "Bulkyo21.com" -> "불교닷컴"
                    "Khan.co.kr" -> "스포츠경향"
                    "News1.kr" -> "뉴스1"
                    "Mediatoday.co.kr" -> "미디어투데이"
                    "Kmib.co.kr" -> "국민일보"
                    "Huffingtonpost.kr" -> "HUFFPOST"
                    else -> it.substring(0, it.indexOf(".")).toUpperCase()
                }

        text = source
    }
    ?: gone()
}

@BindingAdapter("setDescription")
fun TextView.setDescription(description: String?) {
    description?.let {
        val finalDescription = if(it.endsWith("...")) it else "$it ..."
        text = finalDescription
    } ?: gone()
}