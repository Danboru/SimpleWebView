package id.eightstudio.danboru.jogjaorganik.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import id.eightstudio.danboru.jogjaorganik.MainActivity;
import id.eightstudio.danboru.jogjaorganik.R;

public class FlashScreen extends Activity {

    private static final String TAG = "SplashScreen";

    /**
     * Kelas onCreate, Kelas yang di jalankan setelah konstruktor
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Merubah kedalam keadaan fullscreen tanpa menubar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Get window screensize dan merubahnya kedalam keadaan fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        //Membuat thead untuk menjalankan activity beberap second
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: Berhasil Set Timer Sleep");
                    Thread.sleep(1000); //ini bagian set duration lamanya splashScreen
                } catch (Exception e) {
                    Log.d(TAG, "run: Terjasi kesalahan saat Set Duration Sleep");
                    e.printStackTrace();
                }
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                //Menjalankan activity
                startActivity(i);
                finish();//Harus di fnish untuk mengilangkan activity di dalam process background
            }
        });

        Log.d(TAG, "onCreate: Mulai menjalankan Thread");
        thread.start();

    }
}
