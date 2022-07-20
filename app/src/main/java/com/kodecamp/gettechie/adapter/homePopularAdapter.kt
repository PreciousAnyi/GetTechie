package com.kodecamo.gettechie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.fragments.HomeFragmentDirections
import com.kodecamp.gettechie.fragments.popular_playListFragmentDirections
import com.kodecamp.gettechie.model.Declaration

private lateinit var navCon: NavController
class homePopularAdapter (private var context: Context, private var dataset:List<Declaration>): RecyclerView.Adapter<homePopularAdapter.itemViewHolder>() {
    class itemViewHolder(var view: View): RecyclerView.ViewHolder(view){
        var image: ImageView =view.findViewById(R.id.popularImg)
        var course: TextView =view.findViewById(R.id.course)
        var tutor: TextView =view.findViewById(R.id.tutor)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        var adapterLayout=LayoutInflater.from(parent.context)
            .inflate(R.layout.popularitem, parent,false)
        return itemViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        var item=dataset[position]
        holder.image.setImageResource(item.stringResource)
        holder.course.text=item.stringResource2
        holder.tutor.text=item.stringResource1
        var mylist= item.stringResource

        holder.course.setOnClickListener {
            var letters=holder.adapterPosition.toString()
            var actionn=popular_playListFragmentDirections.actionPopularPlayListFragmentToCourseFragment(letter = letters)

            holder.view.findNavController().navigate(actionn)

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}

