package me.ernestzamelczyk.volantessoni.view.sheets

import android.support.v7.util.DiffUtil
import me.ernestzamelczyk.volantessoni.data.model.Sheet

class DiffUtilCallback(
        private val oldItems: List<Sheet>,
        private val newItems: List<Sheet>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].storageUrl == newItems[newItemPosition].storageUrl
    }

}