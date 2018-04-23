package me.ernestzamelczyk.volantessoni.view.sheets

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_sheet.view.*
import me.ernestzamelczyk.volantessoni.R
import me.ernestzamelczyk.volantessoni.data.model.Sheet

class SheetsAdapter: RecyclerView.Adapter<SheetsAdapter.SheetsViewHolder>() {

    private val items = mutableListOf<Sheet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheetsViewHolder {
        return SheetsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sheet, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SheetsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: List<Sheet>) {
        val callback = DiffUtilCallback(items, newItems)
        val result = DiffUtil.calculateDiff(callback)
        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    class SheetsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(sheet: Sheet) {
            itemView.sheetsName.text = sheet.song.name
        }

    }

}