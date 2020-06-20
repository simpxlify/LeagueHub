package com.example.projetoapp

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset


class MyStringRequest : StringRequest {

    private val TAG: String = com.example.projetoapp.MyStringRequest::class.java.getName()

    private var content: String? = null

    constructor(
        method: Int,
        url: String?,
        content : String,
        listener: Response.Listener<String>?,
        errorListener: Response.ErrorListener?
    ) : super(method, url, listener, errorListener) {
        this.content = content

    }

    @Throws(AuthFailureError::class)
    override fun getHeaders(): MutableMap<String, String> {
        return super.getHeaders()
    }

    @Throws(AuthFailureError::class)
    override fun getBody(): ByteArray {
        var body = ByteArray(0)
        body = content?.toByteArray(Charset.forName("UTF-8")) ?:  ByteArray(0)
        return body
    }

    override fun getBodyContentType(): String {
        return "application/json"
    }


}