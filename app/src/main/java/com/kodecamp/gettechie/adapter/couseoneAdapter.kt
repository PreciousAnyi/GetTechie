package com.kodecamo.gettechie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.model.Course


class couseoneAdapter (private var context: Context, private var dataset:List<Course>): RecyclerView.Adapter<couseoneAdapter.itemViewHolder>() {


    class itemViewHolder(var view: View): RecyclerView.ViewHolder(view){
        var course: TextView =view.findViewById(R.id.course)
        var tutor: TextView =view.findViewById(R.id.tutor)
        var vidButton: ImageView =view.findViewById(R.id.vid)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        var adapterLayout=LayoutInflater.from(parent.context)
            .inflate(R.layout.courseitem, parent,false)
        return itemViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        var item=dataset[position]
        holder.course.text=item.stringResource

        holder.course.setOnClickListener {
            holder.vidButton.setImageResource(R.drawable.ticksquare)
            var letters=holder.adapterPosition.toString()

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}

