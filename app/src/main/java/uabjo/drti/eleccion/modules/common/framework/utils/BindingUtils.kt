package uabjo.drti.eleccion.modules.common.framework.utils

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object UiUtil {
    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    @JvmStatic
    fun loadImageInCircle(view: ImageView, imageUrl: String?, placeholder: Drawable? = null) {
        Log.d("IMAGEN","cargando imagen: " + imageUrl)
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .error(placeholder)
            .into(view)
    }
}