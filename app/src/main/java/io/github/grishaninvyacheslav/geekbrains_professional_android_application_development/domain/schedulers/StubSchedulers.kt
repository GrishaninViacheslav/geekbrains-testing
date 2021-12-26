package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object StubSchedulers : ISchedulers {
    override fun main(): Scheduler =  Schedulers.trampoline()
    override fun background(): Scheduler = Schedulers.trampoline()
}