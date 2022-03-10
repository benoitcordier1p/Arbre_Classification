package com.example.arbre_classification.util.event

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

object RxEventHandler {

    private val publishEventBus = PublishSubject.create<EventInt>()

    fun publishEvent(o: EventInt) {
        publishEventBus.onNext(o)
    }

    val publishEventObservable: Observable<EventInt>
        get() {
            return publishEventBus
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}