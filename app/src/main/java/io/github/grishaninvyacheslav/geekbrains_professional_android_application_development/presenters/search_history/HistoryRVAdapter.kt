package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.R

class HistoryRVAdapter(
    var history: List<String>,
    private val onClickEvent: (word: String) -> Unit
) :
    RecyclerView.Adapter<HistoryRVAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_search_history, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.textView) {
            text = history[position]
            setOnClickListener { onClickEvent(history[position]) }
        }
    }

    override fun getItemCount() = history.size
}