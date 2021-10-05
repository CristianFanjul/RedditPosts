package com.cmf.redditposts.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cmf.redditposts.R
import com.cmf.redditposts.misc.ImageLoader
import com.cmf.redditposts.model.Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
