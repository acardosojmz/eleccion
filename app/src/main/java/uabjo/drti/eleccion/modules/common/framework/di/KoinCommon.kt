package uabjo.drti.eleccion.modules.common.framework.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uabjo.drti.eleccion.modules.common.Constants
import uabjo.drti.eleccion.modules.common.framework.database.ElectionDB
import java.util.concurrent.TimeUnit


fun providesHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient().newBuilder()
        .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS).addInterceptor(interceptor)
        .addInterceptor {
            val request = it.request()
            val builder = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
            it.proceed(builder.build())
        }.build()
}

fun providesRetrofit(httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val coreModule = module {
    single { providesHttpClient() }
    single { providesRetrofit(get()) }
    single { ElectionDB.create(androidContext()) }
}