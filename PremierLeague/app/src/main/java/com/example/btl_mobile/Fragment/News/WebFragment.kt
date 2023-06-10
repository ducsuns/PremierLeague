package com.example.btl_mobile.Fragment.News

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.btl_mobile.R


class WebFragment(var link :String) : Fragment() {

    lateinit var v:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v =  inflater.inflate(R.layout.fragment_web, container, false)
        var webView:WebView = v.findViewById(R.id.webView)
        webView.loadUrl(link)
        return v
    }

}