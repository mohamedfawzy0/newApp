package com.app.torch.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(private var data: ArrayList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int)
    }

    fun setOnItemClickedListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    fun removeItem(model: T) {
        data.remove(model)
        notifyDataSetChanged()
    }

    fun updateAll(data: ArrayList<T>){

        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()

    }

    abstract fun dataBindingUtil(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return dataBindingUtil(parent, viewType)
    }

    override fun getItemCount(): Int {
        return if (data.isEmpty()) 0 else data.size
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}