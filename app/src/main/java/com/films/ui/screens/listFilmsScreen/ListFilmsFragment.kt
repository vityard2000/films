package com.films.ui.screens.listFilmsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.films.FilmsApplication
import com.films.databinding.FragmentListFilmsBinding
import com.films.ui.adapters.FilmsAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest


class ListFilmsFragment : Fragment() {
   // val navHostFragment =
        //Navigation.findNavController(requireActivity(), R.id.nav_graph)
    //val navController = navHostFragment
    private var _viewBinding: FragmentListFilmsBinding? = null
    private val viewBinding get() = _viewBinding!!
    private lateinit var viewModel: ListFilmsViewModel

    private val adapter by lazy(LazyThreadSafetyMode.NONE){
        FilmsAdapter(requireContext())
    }


    companion object {
        fun newInstance() = ListFilmsFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkFilmsRepositoryService = (requireActivity().application as FilmsApplication)
            .applicationComponent.getNetworkFilmsRepositoryService()

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ListFilmsViewModel(networkFilmsRepositoryService) as T
            }
        }).get(ListFilmsViewModel::class.java)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentListFilmsBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.onClickItem = {

        }

        viewBinding.fragmentListFilmsRv.layoutManager = GridLayoutManager(context, 3)
        viewBinding.fragmentListFilmsRv.adapter = adapter
        lifecycleScope.launchWhenStarted{
            viewModel.films.collectLatest(adapter::submitData)
           //viewModel.isLoading.collect {
           //    viewBinding.progress.isIndeterminate = it
           //}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}