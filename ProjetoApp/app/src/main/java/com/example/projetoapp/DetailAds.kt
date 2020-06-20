package com.example.projetoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DetailAds : AppCompatActivity() {
    var idAds : Int? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ads)


        val bundle = intent.extras
        bundle?.let{
            idAds = it.getInt("idAds")
        }

        val intentResult = Intent()
        val ttad = findViewById<EditText>(R.id.editTextNameAdTitle)
        val txtad = findViewById<EditText>(R.id.editTextTxtAd)
        val addAds =findViewById<Button>(R.id.btnAddAd)

        addAds.setOnClickListener {
            if (ttad.text.toString() == "" || txtad.text.toString() == "") {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addAds(
                    this@DetailAds,
                    idAds?.plus(1).toString(),
                    ttad.text.toString(),
                    txtad.text.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailAds, Home::class.java)
                        Toast.makeText(
                            this@DetailAds,
                            this@DetailAds.getString(R.string.add_adssuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailAds,
                            this@DetailAds.getString(R.string.add_adsfailed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                setResult(Activity.RESULT_OK, intentResult)
                finish()
            }
        }
    }
}