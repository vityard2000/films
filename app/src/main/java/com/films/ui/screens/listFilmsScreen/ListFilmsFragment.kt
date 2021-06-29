package com.films.ui.screens.listFilmsScreen


import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ListFilmsFragment : Fragment() {

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

        val loadFilmsUC = (requireActivity().application as FilmsApplication)
            .applicationComponent.getLoadFilmsUC()

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListFilmsViewModel(loadFilmsUC) as T
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.fragmentListFilmsRv.adapter = adapter
       // Log.d("mylog", (viewBinding.fragmentListFilmsRv.measuredWidth / 100).toString())

        viewBinding.fragmentListFilmsRv.layoutManager = GridLayoutManager(context, 3)

        lifecycleScope.launch{
            viewModel.films.collectLatest(adapter::submitData)
        }

    }

}