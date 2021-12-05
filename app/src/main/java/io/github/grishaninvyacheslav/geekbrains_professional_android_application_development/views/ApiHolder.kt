package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiHolder {
    val api: IDataSource by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)
    }
}