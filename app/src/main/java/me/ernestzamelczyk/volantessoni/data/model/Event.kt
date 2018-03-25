package me.ernestzamelczyk.volantessoni.data.model

import java.util.*

data class Event(
        val date: Date,
        val place: String,
        val sheets: List<Sheet>? = null,
        val repertoire: Repertoire? = null
)