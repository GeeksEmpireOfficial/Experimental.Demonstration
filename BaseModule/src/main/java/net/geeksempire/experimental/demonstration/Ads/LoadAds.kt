package net.geeksempire.experimental.demonstration.Ads

import android.app.Activity
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.ads_views.*
import net.geeksempire.experimental.demonstration.R

class LoadAds : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ads_views)
        MobileAds.initialize(applicationContext, getString(R.string.ADMOB_APP_ID))

        val adRequestBuilderInterstitialAd = AdRequest.Builder()
            .addTestDevice("65B5827710CBE90F4A99CE63099E524C")
            .addTestDevice("DD428143B4772EC7AA87D1E2F9DA787C")
        adRequestBuilderInterstitialAd.addKeyword("style")
        adRequestBuilderInterstitialAd.addKeyword("fashion")
        adRequestBuilderInterstitialAd.addKeyword("clothes")
        adRequestBuilderInterstitialAd.addKeyword("dress")
        adRequestBuilderInterstitialAd.addKeyword("celebrities")
        adRequestBuilderInterstitialAd.addKeyword("models")
        val interstitialAd = InterstitialAd(applicationContext)
        interstitialAd.adUnitId = getString(R.string.AdUnitInterstitialOne)
        interstitialAd.loadAd(adRequestBuilderInterstitialAd.build())
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                interstitialAd.show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                interstitialAd.loadAd(adRequestBuilderInterstitialAd.build())
            }

            override fun onAdOpened() {
            }

            override fun onAdClicked() {

            }

            override fun onAdLeftApplication() {

            }

            override fun onAdClosed() {

            }
        }
    }

    override fun onResume() {
        super.onResume()

        val adRequestBuilderOne = AdRequest.Builder()
            .addTestDevice("65B5827710CBE90F4A99CE63099E524C")
            .addTestDevice("DD428143B4772EC7AA87D1E2F9DA787C")
        adRequestBuilderOne.addKeyword("style")
        adRequestBuilderOne.addKeyword("fashion")
        adRequestBuilderOne.addKeyword("clothes")
        adRequestBuilderOne.addKeyword("dress")
        adRequestBuilderOne.addKeyword("celebrities")
        adRequestBuilderOne.addKeyword("models")
        adRequestBuilderOne.addKeyword("models")
        adRequestBuilderOne.setLocation(/*Location("gps")*/Location(LocationManager.GPS_PROVIDER))

        val bundle = Bundle()
        bundle.putInt("cordova", 1)
        adRequestBuilderOne.addNetworkExtrasBundle(AdMobAdapter::class.java, bundle)

        adViewBannerOne.loadAd(adRequestBuilderOne.build())
        adViewBannerOne.adListener = object : AdListener() {
            override fun onAdLoaded() {

            }

            override fun onAdFailedToLoad(errorCode: Int) {
                adViewBannerOne.loadAd(adRequestBuilderOne.build())
            }

            override fun onAdOpened() {
            }

            override fun onAdClicked() {

            }

            override fun onAdLeftApplication() {

            }

            override fun onAdClosed() {

            }
        }

        val adRequestBuilderTwo = AdRequest.Builder()
            .addTestDevice("65B5827710CBE90F4A99CE63099E524C")
            .addTestDevice("DD428143B4772EC7AA87D1E2F9DA787C")
        adRequestBuilderTwo.addKeyword("play station")
        adRequestBuilderTwo.addKeyword("game")
        adRequestBuilderTwo.addKeyword("sony")
        adRequestBuilderTwo.addKeyword("xbox")
        adRequestBuilderTwo.addKeyword("online")
        adRequestBuilderTwo.addKeyword("microsoft")
        adViewBannerTwo.loadAd(adRequestBuilderTwo.build())
        adViewBannerTwo.adListener = object : AdListener() {
            override fun onAdLoaded() {

            }

            override fun onAdFailedToLoad(errorCode: Int) {
                adViewBannerTwo.loadAd(adRequestBuilderTwo.build())
            }

            override fun onAdOpened() {
            }

            override fun onAdClicked() {

            }

            override fun onAdLeftApplication() {

            }

            override fun onAdClosed() {

            }
        }

        val adRequestBuilderThree = AdRequest.Builder()
            .addTestDevice("65B5827710CBE90F4A99CE63099E524C")
            .addTestDevice("DD428143B4772EC7AA87D1E2F9DA787C")
        adRequestBuilderThree.addKeyword("bmw")
        adRequestBuilderThree.addKeyword("benz")
        adRequestBuilderThree.addKeyword("toyota")
        adRequestBuilderThree.addKeyword("car")
        adRequestBuilderThree.addKeyword("autos")
        adViewBannerThree.loadAd(adRequestBuilderThree.build())
        adViewBannerThree.adListener = object : AdListener() {
            override fun onAdLoaded() {

            }

            override fun onAdFailedToLoad(errorCode: Int) {
                adViewBannerThree.loadAd(adRequestBuilderThree.build())
            }

            override fun onAdOpened() {
            }

            override fun onAdClicked() {

            }

            override fun onAdLeftApplication() {

            }

            override fun onAdClosed() {

            }
        }
    }
}