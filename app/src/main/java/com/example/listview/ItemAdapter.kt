package com.example.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Button

class StringListAdapter(
    private val items: MutableList<String>,
    private val inflater: LayoutInflater,
    private val onDataChaged: () -> Unit
) : BaseAdapter() {

    // Simple holder to avoid repeated findViewById calls
    private data class ViewHolder(val title: TextView, val remove: Button)

    override fun getCount(): Int = items.size


    override fun getItem(position: Int): String = items[position]

    // Provide a stable-ish id by hashing the string (safe for simple lists)
    override fun getItemId(position: Int): Long = getItem(position).hashCode().toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView: View
        val holder: ViewHolder

        if (convertView == null) {
            rowView = inflater.inflate(R.layout.list_item, parent, false)
            holder = ViewHolder(
                rowView.findViewById(R.id.tvTitle),
                rowView.findViewById(R.id.btnRemove)
            )
            rowView.tag = holder
        } else {
            rowView = convertView
            holder = rowView.tag as ViewHolder
        }

        val text = getItem(position)
        holder.title.text = text

        holder.remove.setOnClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
            onDataChaged()
        }

        return rowView
    }

}
