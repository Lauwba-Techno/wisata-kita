package com.lauwba.wisatakita.portal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.lauwba.wisatakita.databinding.FragmentPortalBinding


class PortalFragment : Fragment() {

    private var _binding: FragmentPortalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPortalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //instantiate settings untuk webview kita
        binding.portalWeb.settings.javaScriptEnabled = true
        binding.portalWeb.settings.builtInZoomControls = true

        //pasang webchrome client nya
        binding.portalWeb.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                //kasih progress ke progress barnya, biar nambah
                binding.progressBar.progress = newProgress
//                requireActivity().title = "Loading..."
            }
        }

        binding.portalWeb.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //sembunyikan progress bar kalo halamannya udah selesai diload
                binding.progressBar.visibility = View.GONE
//                requireActivity().title = view?.title
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }

        }

        //load urlnya ya, jangan lupa
        // note,
        // kalo bisa web nya udah https supaya tidak ada tambahan lagi di manifestnya
        // kalo belum https, maka harus ditambahkan
        // android:usesCleartextTraffic="false"
        // di AndroidManifest.xml
        binding.portalWeb.loadUrl("https://www.yogyes.com/id/yogyakarta-tourism-object/")
    }

}