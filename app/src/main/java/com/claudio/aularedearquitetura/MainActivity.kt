package com.claudio.aularedearquitetura

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val TAG = "NetworkInfo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val currentNetwork = connectionManager.activeNetwork

//        println("Codigo:: $currentNetwork")
//        println("CAPS:: $caps")
//        val linkProperties = connectionManager.getLinkProperties(currentNetwork)
//        println("LINKPROPRIERTIES:: $linkProperties")

//        connectionManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//                Log.e(TAG, "NetworkInfo::: $network")
//            }
//
//            override fun onCapabilitiesChanged(
//                network: Network,
//                networkCapabilities: NetworkCapabilities
//            ) {
//                super.onCapabilitiesChanged(network, networkCapabilities)
//                Log.e(TAG, "onCapabilitiesChanged::: $network")
//                Log.e(TAG, "onCapabilitiesChanged::: $networkCapabilities")
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                Log.e(TAG, "onLost::: $network")
//            }
//        })

        val caps = connectionManager.getNetworkCapabilities(currentNetwork)

        if(caps != null) {
            println("DOWN KBPS ${caps.linkDownstreamBandwidthKbps}")
            println("UP KBPS ${caps.linkUpstreamBandwidthKbps}")
            when {
                caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    println("Conectado a Rede WI-FI")
                }
                caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    println("Conectado a Rede Móvel")
                } else -> {
                    println("Conexao desconhecida")
                }
            }
        } else {
            println("O dispositivo não está conectado")
        }
    }
}