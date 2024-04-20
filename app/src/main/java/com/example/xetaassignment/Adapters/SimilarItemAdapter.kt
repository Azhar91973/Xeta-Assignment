package com.example.xetaassignment.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xetaassignment.DataModel.SimilarItem
import com.example.xetaassignment.R
import com.squareup.picasso.Picasso

data class SimilarItemAdapter(val ImageList:Array<Int>,val SimilarItemsList: List<SimilarItem>):
RecyclerView.Adapter<SimilarItemAdapter.ViewHolder>(){


    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val RecipeImage:ImageView
        val ImageTitle:TextView
        init {
            RecipeImage = itemview.findViewById(R.id.Similar_Item_IV)
            ImageTitle = itemview.findViewById(R.id.Similar_Item_TV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemview = LayoutInflater.from(parent.context).inflate(R.layout.similar_item_card_design,parent,false)
        return ViewHolder(itemview)
    }

    override fun getItemCount(): Int {
      return SimilarItemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(ImageList[position]).into(holder.RecipeImage)
        holder.ImageTitle.text = SimilarItemsList[position].name
    }

}
