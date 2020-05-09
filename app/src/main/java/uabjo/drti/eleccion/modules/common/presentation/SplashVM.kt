package uabjo.drti.eleccion.modules.common.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashVM : ViewModel() {
    private val mutableLiveData = MutableLiveData<SplashState>()
    val liveData: LiveData<SplashState> get() = mutableLiveData

    init {
        GlobalScope.launch {
            delay(3000)
            mutableLiveData.postValue(SplashState.MainActivity())
        }
    }
}

sealed class SplashState {
    class MainActivity : SplashState()
}