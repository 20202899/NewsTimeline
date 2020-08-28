package com.carlos.news.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.core.domain.Article
import com.carlos.news.R
import com.google.android.material.transition.platform.Hold
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModel()
    private var adapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topHeadLinesLiveData.observe(viewLifecycleOwner) {
            adapter = NewsAdapter(it.articles) { view, article ->
                openArticle(view, article)
            }.apply {
                setHasStableIds(true)
            }
            recyclerview.adapter = adapter
        }

        with(recyclerview) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        if (adapter == null) {
            viewModel.getTopHeadLines()
        }
    }

    private fun openArticle(view: View, article: Article) {
        findNavController().navigate(
            R.id.action_FirstFragment_to_SecondFragment,
            Bundle().apply {
                putSerializable("data", article)
            },
            null,
            FragmentNavigatorExtras(view to getString(R.string.open_article_transition))
        )
    }
}