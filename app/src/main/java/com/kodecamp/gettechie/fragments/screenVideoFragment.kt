package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kodecamo.gettechie.adapter.courseAdapter
import com.kodecamo.gettechie.adapter.screenVideoAdapter
import com.kodecamp.gettechie.DataSource
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentCourseBinding
import com.kodecamp.gettechie.databinding.FragmentScreenVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import kotlin.properties.Delegates

class screenVideoFragment : Fragment() {
    private  var _binding: FragmentScreenVideoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navCon: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewtwo: RecyclerView
    private var letterId by Delegates.notNull<Int>()
    var myDataSource= DataSource().loadDelarationsfour()
    var myDataSourcetwo= DataSource().loadDelarationsfive()
    var youtubeId=DataSource().youtubeId()


    companion object{
        //        private const val code: Int = 0
        const val LETTER="letter"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScreenVideoBinding.inflate(inflater, container, false)
        navCon = NavHostFragment.findNavController(this)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            letterId=it.getInt(LETTER)
        }
//        Log.v("bloob",youtubeId.toString())
//
        val videoId=youtubeId.get(letterId).stringResource

        val video=binding.player
        video.enableAutomaticInitialization=false

        val listener:YouTubePlayerListener=object:AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val defaultuiController=DefaultPlayerUiController(video,youTubePlayer)
                defaultuiController.showFullscreenButton(true)

                defaultuiController.setFullScreenButtonClickListener(){
                    if(video.isFullScreen()){
                        video.exitFullScreen()
                        activity?.window?.decorView!!.systemUiVisibility=View.SYSTEM_UI_FLAG_VISIBLE

                        if(activity?.actionBar !=null){
                            activity?.actionBar!!.show()
                        }

                    }else{
                        video.enterFullScreen()
                        activity?.window?.decorView!!.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN
                        if(activity?.actionBar !=null){
                            activity!!.actionBar!!.hide()
                        }
                    }
                }
                video.setCustomPlayerUi(defaultuiController.rootView)
                youTubePlayer.cueVideo(videoId,0f)

            }
        }

        val options:IFramePlayerOptions=IFramePlayerOptions.Builder().controls(0).build()
        video.initialize(listener
        ,options)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        //        recyclerView Deceorator
        recyclerView.adapter= screenVideoAdapter(requireContext(),myDataSource)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(), StaggeredGridLayoutManager.VERTICAL
            )
        )
        recyclerViewtwo = binding.jrec
        recyclerViewtwo.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)

        recyclerViewtwo.adapter= screenVideoAdapter(requireContext(),myDataSourcetwo)


        //        recyclerView Deceorator

        recyclerViewtwo.addItemDecoration(
            DividerItemDecoration(
                requireContext(), StaggeredGridLayoutManager.VERTICAL
            )
        )
    }
}