package com.kodecamo.gettechie.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.model.Course
import com.kodecamp.gettechie.model.Declaration


class screenVideoAdapter (private var context: Context, private var dataset:List<Declaration>): RecyclerView.Adapter<screenVideoAdapter.itemViewHolder>() {


    class itemViewHolder(var view: View): RecyclerView.ViewHolder(view){
        var course: TextView =view.findViewById(R.id.course)
        var tutor: TextView =view.findViewById(R.id.tutor)
        var vidButton: ImageView =view.findViewById(R.id.popularImg)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        var adapterLayout=LayoutInflater.from(parent.context)
            .inflate(R.layout.screenvideoitem, parent,false)
        return itemViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        var item=dataset[position]
        holder.course.text=item.stringResource2
        holder.tutor.text=item.stringResource1
        holder.vidButton.setImageResource(item.stringResource)
        var letters:String

//        holder.vidButton.setOnClickListener {
//            holder.vidButton.setImageResource(R.drawable.ticksquare)
//            letters=holder.adapterPosition.toString()
//
//        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}

