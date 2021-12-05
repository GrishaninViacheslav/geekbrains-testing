package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DictionaryWordDto(
    @Expose val word: String,
    @Expose val phonetic: String,
    @Expose val meanings: List<MeaningsDto>
) : Parcelable

@Parcelize
data class MeaningsDto(
    @Expose val partOfSpeech: String,
    @Expose val definitions: List<DefinitionDto>
) : Parcelable

@Parcelize
data class DefinitionDto(
    @Expose val definition: String,
    @Expose val example: String
) : Parcelable