package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain

class QueryValidator {
    companion object{
        fun validate(query: String) = !query.contains(" ") && query.isNotBlank()
    }
}