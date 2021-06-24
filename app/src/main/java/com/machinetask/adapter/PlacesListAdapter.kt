package com.machinetask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machinetask.R
import com.machinetask.modals.PlacesModal
import com.machinetask.utils.loadImage
import kotlinx.android.synthetic.main.places_item.view.*

class PlacesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_LOADING = 0;
    private val VIEW_TYPE_NORMAL = 1;
    private var isLoaderVisible = false
    private val entries = ArrayList<PlacesModal>()

    fun setList(dataSet: List<PlacesModal>?, isClear: Boolean) {
        if (isClear) {
            entries.clear()
        }
        entries.addAll(dataSet ?: ArrayList())
        notifyDataSetChanged()
    }

    fun showLoading() {
        isLoaderVisible = true
        entries.add(PlacesModal())
        notifyItemInserted(entries.size - 1)
    }

    fun hideLoading() {
        isLoaderVisible = false
        val itemPosition = entries.size - 1
        entries.removeAt(itemPosition)
        notifyItemRemoved(itemPosition)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NORMAL) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.places_item, parent, false)
            ItemViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.loading_view, parent, false)
            ProgressViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is ItemViewHolder -> viewHolder.bindItems(entries[position])
            else -> {
                (viewHolder as ProgressViewHolder).bindItems(entries[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible && position == entries.size - 1) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(dataBean: PlacesModal) {
            itemView.ivPlace.loadImage(dataBean.downloadUrl)
        }
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(dataBean: PlacesModal) {

        }
    }
}