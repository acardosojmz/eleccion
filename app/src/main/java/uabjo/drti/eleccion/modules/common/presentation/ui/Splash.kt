package uabjo.drti.eleccion.modules.common.presentation.ui

import android.content.Intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_splash.*
import uabjo.drti.eleccion.R
import uabjo.drti.eleccion.modules.common.presentation.SplashState
import uabjo.drti.eleccion.modules.common.presentation.SplashVM
import java.util.*


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val fecha = Calendar.getInstance()

        val svm = ViewModelProvider(this)
            .get(SplashVM::class.java)
        tvSplash.text= getString(R.string.splash, fecha.get(Calendar.YEAR).toString())



        svm.liveData.observe(this, Observer {
            when (it) {
                is SplashState.MainActivity -> {
                    goToMainActivity()
                }
            }
        })
    }

    private fun goToMainActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

}
