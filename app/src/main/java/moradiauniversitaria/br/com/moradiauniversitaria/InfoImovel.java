package moradiauniversitaria.br.com.moradiauniversitaria;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoImovel extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_info_imovel, container, false);

        ViewPager viewPager = view.findViewById(R.id.fotosSlider);
        SliderAdapter adapter = new SliderAdapter(view.getContext());
        viewPager.setAdapter(adapter);

        return view;
    };
}
