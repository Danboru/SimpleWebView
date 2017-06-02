package id.eightstudio.danboru.jogjaorganik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.InetAddress;

public class MainActivity extends Activity {

    //View yang akan di gunakan
    private WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Merubah kedalam keadaan fullscreen tanpa menubar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Get window screensize dan merubahnya kedalam keadaan fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Set View yang akan di gunakan
        setContentView(R.layout.activity_main);

        //Enable Cookie
        CookieManager.getInstance().setAcceptCookie(true);

        view = (WebView) this.findViewById(R.id.webViewJogjaOrganic);

        //Memeriksa ketersediaan internet
        if (isInternetAvailable()){

            Toast.makeText(this, "No Connection", Toast.LENGTH_SHORT).show();

        } else {

            WebSettings settings = view.getSettings();

            //Set Local Storage enable
            settings.setDomStorageEnabled(true);

            //Can Running javascript
            view.getSettings().setJavaScriptEnabled(true);
            view.setWebViewClient(new MyBrowser());
            view.loadUrl("http://google.com"); //Site yang akan di tampilkan
            view.setWebChromeClient(new WebChromeClient()); // adding js alert support

        }
    }

    /***
     * Fungsi ini adalah fungsi untuk menangani saat link di clikc
     * idak membuka defaulth bwrowser
     * */
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /***
     * Fungsi ini akan mengembalikan true = ada koneksi, false = tidak ada koneksi
     * */
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("http://google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }

    //
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //Ketika disentuh tombol back
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack(); //method goback() dieksekusi untuk kembali pada halaman sebelumnya
            return true;
        }
        // Jika tidak ada history (Halaman yang sebelumnya dibuka)
        // maka akan keluar dari activity
        return super.onKeyDown(keyCode, event);
    }


}
