package com.blazingtech.githubbrowser.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blazingtech.githubbrowser.di.scope.ScreenScope
import com.blazingtech.githubbrowser.di.viewmodel.AppViewModelFactory
import com.blazingtech.githubbrowser.home.databinding.ScreenHomeBinding
import com.blazingtech.githubbrowser.home.list.HomeRepoAdapter
import javax.inject.Inject

@ScreenScope
class HomeFragment : Fragment() {
    @Inject lateinit var appViewModelFactory: AppViewModelFactory

    private val homeViewModel : HomeViewModel by lazy {
        ViewModelProvider(this, appViewModelFactory)[HomeViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ScreenHomeBinding.inflate(inflater,container,false)

        binding.repoList.apply {
            adapter = HomeRepoAdapter()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,DividerItemDecoration.VERTICAL
                )
            )
        }

        homeViewModel.viewStateUpdate.observe(viewLifecycleOwner, Observer {state ->
            when(state){
                is HomeLoadingState -> handleLoadingState(binding)
                is HomeLoadedState -> handleLoadedState(state, binding)
                is HomeLoadingErrorState -> handleErrorState(state, binding)
            }
        })
        return binding.root
    }

    private fun handleErrorState(state: HomeLoadingErrorState, binding: ScreenHomeBinding) {
        binding.repoList.visibility = GONE
        binding.loadingIndicator.visibility = GONE


        binding.errorTextView.visibility = VISIBLE
        binding.errorTextView.text = state.message

    }

    private fun handleLoadedState(state: HomeLoadedState, binding: ScreenHomeBinding) {
        binding.errorTextView.visibility = GONE
        binding.loadingIndicator.visibility = GONE

        binding.repoList.visibility = VISIBLE
        (binding.repoList.adapter as HomeRepoAdapter).setRepoItems(state.repos)
    }

    private fun handleLoadingState(binding: ScreenHomeBinding) {
        binding.repoList.visibility = GONE
        binding.errorTextView.visibility = GONE
        binding.loadingIndicator.visibility = VISIBLE
    }
}