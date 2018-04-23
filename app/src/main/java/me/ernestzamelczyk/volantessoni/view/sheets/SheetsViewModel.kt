package me.ernestzamelczyk.volantessoni.view.sheets

import android.databinding.ObservableBoolean
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import me.ernestzamelczyk.volantessoni.data.model.Sheet
import me.ernestzamelczyk.volantessoni.data.repository.SheetsRepository
import javax.inject.Inject

class SheetsViewModel @Inject constructor(
    private val sheetsRepository: SheetsRepository
) {

    val sheetsLoading: ObservableBoolean = ObservableBoolean(true)
    val noSheets: ObservableBoolean = ObservableBoolean(false)
    private val sheets: BehaviorSubject<List<Sheet>> = BehaviorSubject.create()

    init {
        loadSheets()
    }

    private fun loadSheets() {
        sheetsRepository.getSheets().subscribe({
            sheets.onNext(it)
            sheetsLoading.set(false)
        }, {
            sheetsLoading.set(false)
            noSheets.set(true)
        })
    }

    fun getSheets(): Observable<List<Sheet>> {
        return sheets
    }
}