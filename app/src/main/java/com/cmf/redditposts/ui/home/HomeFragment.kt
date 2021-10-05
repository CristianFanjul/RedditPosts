package com.cmf.redditposts.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmf.redditposts.R
import com.cmf.redditposts.misc.showSnackbar
import com.cmf.redditposts.model.Article
import com.cmf.redditposts.ui.main.ArticlesAdapter
import com.cmf.redditposts.ui.main.IOnArticleListener
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), IOnArticleListener {

    private val viewModel: HomeViewModel by viewModels()

    private val adapter = ArticlesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupAdapter()
        setupObservers()

        if (adapter.getItems().isNullOrEmpty()) {
            viewModel.loadInitialArticles()
        }
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        vw_recycler.layoutManager = layoutManager
        vw_recycler.adapter = adapter
        vw_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isRecyclerBottom(dy, layoutManager)) viewModel.loadMoreArticles()
            }
        })
        vw_recycler.itemAnimator = SlideInLeftAnimator()
    }

    private fun setupObservers() {
        viewModel.dataLoading.observe(viewLifecycleOwner, {
            vw_refresh_articles.isRefreshing = it
        })

        viewModel.snackbarText.observe(viewLifecycleOwner, { message ->
            view.showSnackbar(message)
        })

        viewModel.items.observe(viewLifecycleOwner, { articles ->
            adapter.setItems(articles)
        })

        viewModel.loadMoreItems.observe(viewLifecycleOwner, { articles ->
            adapter.addItems(articles)
        })

        vw_refresh_articles.setOnRefreshListener {
            viewModel.refreshArticles()
        }

        btn_dismiss_all.setOnClickListener {
            val size = adapter.getItems().size
            adapter.getItems().clear()
            adapter.notifyItemRangeRemoved(0, size)
        }
    }

    override fun onArticleClicked(item: Article, position: Int) {
        viewModel.onArticleClicked(item)
        adapter.notifyItemChanged(position)

        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
        view?.findNavController()?.navigate(action)
    }

    override fun onArticleDismissed(item: Article, position: Int) {
        viewModel.onArticleDismissed(item)
        adapter.notifyItemRemoved(position)
    }

    fun isRecyclerBottom(dy: Int, layoutManager: LinearLayoutManager): Boolean {
        var isEndless = false
        if (dy > 0) {
            if (layoutManager.childCount + layoutManager.findFirstVisibleItemPosition() >= layoutManager.itemCount) {
                isEndless = true
            }
        }
        return isEndless
    }
}