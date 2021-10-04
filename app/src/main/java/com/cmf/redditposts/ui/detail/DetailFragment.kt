package com.cmf.redditposts.ui.detail

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cmf.redditposts.R
import com.cmf.redditposts.misc.ImageLoader
import com.cmf.redditposts.model.Article
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : DaggerFragment(R.layout.fragment_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val detailViewModel by viewModels<DetailViewModel> {
        viewModelFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        val article = arguments?.getParcelable<Article>("article")!!
        updateFragment(article)
    }

    private fun setupObservers() {
        detailViewModel.text.observe(viewLifecycleOwner, Observer {
            txt_vw_home.text = it
        })
    }

    private fun updateFragment(item: Article) {
        txt_vw_home.isGone = true

        val views = arrayOf(
            txt_vw_article_user_detail,
            txt_vw_article_title_detail,
            img_vw_article_image_detail
        )
        // Make views visible
        views.forEach { it.isVisible = true }

        txt_vw_article_user_detail.text = item.author ?: ""
        txt_vw_article_title_detail.text = item.title ?: ""
        item.thumbnail?.let { ImageLoader.loadImage(it, img_vw_article_image_detail) }
    }

}
