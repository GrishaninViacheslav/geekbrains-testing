package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers

import io.reactivex.Scheduler

interface ISchedulers {
    fun main(): Scheduler
    fun background(): Scheduler
}