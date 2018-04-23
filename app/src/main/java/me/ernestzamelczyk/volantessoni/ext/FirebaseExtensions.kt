package me.ernestzamelczyk.volantessoni.ext

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import io.reactivex.Single

inline fun <reified T> DocumentReference.getObject(): Single<T> {
    return Single.create {
        val emitter = it
        this.get().addOnSuccessListener {
            try {
                val item = it.toObject(T::class.java)
                emitter.onSuccess(item)
            } catch (t: Throwable) {
                emitter.onError(t)
            }
        }.addOnFailureListener(emitter::onError)
    }
}

inline fun <reified T> CollectionReference.getObjects(): Single<List<T>> {
    return Single.create {
        val emitter = it
        this.get().addOnSuccessListener {
            try {
                val objects = it.toObjects(T::class.java)
                emitter.onSuccess(objects)
            } catch(t: Throwable) {
                emitter.onError(t)
            }
        }.addOnFailureListener(emitter::onError)
    }
}