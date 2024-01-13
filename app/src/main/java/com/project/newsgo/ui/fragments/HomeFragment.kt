package com.project.newsgo.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.newsgo.R
import com.project.newsgo.databinding.FragmentHomeBinding
import com.project.newsgo.ui.adapters.NewsRecyclerViewAdapter
import com.project.newsgo.ui.viewmodels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeFragmentViewModel
    private val handler = Handler()
    private var searchRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.progressBar2.visibility = View.VISIBLE
        viewModel.news.observe(viewLifecycleOwner) { listArticle ->
            binding.newsRecyclerView.adapter =
                NewsRecyclerViewAdapter(listArticle, requireContext()){
                    val direction= HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                    Navigation.findNavController(view).navigate(direction)
                }
            binding.progressBar2.visibility = View.GONE
        }



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchRunnable?.let { handler.removeCallbacks(it) }
                searchRunnable = Runnable {
                    if (!newText.isNullOrBlank()) {
                        viewModel.getNews(newText, 1)
                    }
                }.also { handler.postDelayed(it, 1000) }


                return true
            }

        })
        return view
    }


}