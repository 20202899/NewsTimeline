package com.carlos.news.presentation.news

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlos.core.domain.Article
import com.carlos.news.R
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val viewModel: NewsPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.articlePageLiveData.observe(viewLifecycleOwner, {
            webview.loadUrl(it.url)
        })

        viewModel.closePageLiveData.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigateUp()
        }

        (requireArguments().getSerializable("data") as Article?)?.let {
            viewModel.load(it)
        }

        with(webview) {
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            settings.userAgentString = "Android"
        }

        webview.webViewClient = object : WebViewClient() {

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                title_home?.text = view?.title
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                title_home?.text = view?.title
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                title_home?.text = view?.title
            }
        }

        close.setOnClickListener { viewModel.close(true) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webview.destroy()
    }
}