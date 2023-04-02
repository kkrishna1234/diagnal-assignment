package com.android.home.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.Content
import com.android.home.databinding.ItemMovieBinding

class SearchRecyclerViewAdapter(
    private var query: String,
    private var items: List<Content>
) : RecyclerView.Adapter<SearchRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.bindData(query, items[position])
    }

    override fun getItemCount() = items.size

    fun updateData(query:String,items: List<Content>){
        this.items = items
        this.query = query
    }
}

