package ar.com.matotuonda.radiociega;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ArrayAdapter<String> mRadiosAdapter;
    private final String LOG_TAG = "RadioCiega";
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_main, container, false);

        String [] radios= {
                "http://www.radios-on-line.com.ar/#radio-mitre-790-am-buenos-aires",
                "http://www.radios-on-line.com.ar/#radio-10-710-am"};
        List<String> radioList= new ArrayList<String>(Arrays.asList(radios));
        mRadiosAdapter= new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_radio,
                R.id.list_item_radio,
                radioList
        );
        ListView listView= (ListView) rootView.findViewById(R.id.listview_radios);
        listView.setAdapter(mRadiosAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url= mRadiosAdapter.getItem(position);
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Toast.makeText(getActivity(), "Reproduciendo de " + url, Toast.LENGTH_LONG).show();

                try {
                    Log.v(LOG_TAG, "Antes de mediaPlayer.setDataSource(url)");
                    mediaPlayer.setDataSource(url);
                    Log.v(LOG_TAG, "Antes de mediaPlayer.prepare()");
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    Log.v(LOG_TAG, "Despues de mediaPlayer.prepare()");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.v(LOG_TAG, "Antes de mediaPlayer.start()");
                mediaPlayer.start();
                Log.v(LOG_TAG, "Despues de mediaPlayer.start()");

            }
        });

        return rootView;
    }
}
