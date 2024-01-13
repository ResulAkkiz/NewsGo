package com.project.newsgo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.project.newsgo.R
import com.project.newsgo.databinding.FragmentDetailBinding
import com.project.newsgo.databinding.FragmentFavoriteBinding
import com.project.newsgo.ui.viewmodels.DetailFragmentViewModel
import com.project.newsgo.ui.viewmodels.FavoriteFragmentViewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailFragmentViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


}