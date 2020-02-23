package com.honar.walletcash

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.DecimalFormat

class ItemRVA(context:Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val context = context

    private lateinit var onItemClickListner: OnItemClickListner

    fun onImageClickListener(onItemClickListner: OnItemClickListner) {
        this.onItemClickListner = onItemClickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return showItemData.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is MyViewHolder -> {
                holder.binde(showItemData[position], position)
            }
        }
    }


    inner class MyViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun binde(cash: Cash, position: Int) {

            itemView.mainCardView.setOnClickListener {
                onItemClickListner.onItemClicked(cash,position)
            }
            itemView.mainCardView.setOnLongClickListener{
                onItemClickListner.onItemClicked(cash,123456)
                true
            }

            itemView.itemName.text = cash.name
            itemView.itemDescription.text = cash.description

            val date = android.text.format.DateFormat.format("yyyy-MM-dd", cash.date)
            itemView.itemDate.text = date

            itemView.itemImage.setImageResource(getImageIDByTAG(cash.tag))

            itemView.itemCost.text = DecimalFormat("#,###.#").format( cash.cash).toString() + " IQD"
            if (cash.cash >= 0){
                itemView.itemCost.setTextColor(ContextCompat.getColor(context, R.color.gre))
            }else{
                itemView.itemCost.setTextColor(ContextCompat.getColor(context, R.color.red))

            }

        }
    }

}