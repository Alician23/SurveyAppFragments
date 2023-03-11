package com.bignerdranch.android.surveyappfragments


import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceInfo.newInstance
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.surveyappfragments.surveyFragment.Companion.newInstance
import java.lang.reflect.Array.newInstance
import java.net.URLClassLoader.newInstance
import javax.xml.xpath.XPathFactory.newInstance

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.setFragmentResultListener(
            RANDOM_RESULTS,
            this
        ) { requestKey, bundle ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_Container2, surveyFragment.newInstance(), "SURVEY")
                .addToBackStack("SURVEY")
                .commit() // To launch result fragment

        }

        supportFragmentManager.setFragmentResultListener(
            RANDOM_RESULTS,
            this
        ) { requestKey, bundle ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_Container2, resultsFragment.newInstance(), "RESULT")
                .addToBackStack("RESULT")
                .commit() // To launch result fragment

        }
    }
}






