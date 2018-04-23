package me.ernestzamelczyk.volantessoni.data.repository

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Single
import me.ernestzamelczyk.volantessoni.data.model.Sheet
import me.ernestzamelczyk.volantessoni.ext.getObjects
import javax.inject.Inject

class SheetsRepository @Inject constructor(
        private val db: FirebaseFirestore
) {

    fun getSheets(): Single<List<Sheet>> {
        db.collection("sheets").get().addOnSuccessListener {
            it.documents.forEach {
                val song = it.get("song") as DocumentReference
                val storageUrl = it.get("storage_url") as String
                song.get().addOnSuccessListener {

                }
            }
        }
        return db.collection("sheets").getObjects()
    }

    fun createSheet(sheet: Sheet): Completable {
        return Completable.create {emitter ->
            db.collection("sheets").add(sheet)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }
        }
    }

}