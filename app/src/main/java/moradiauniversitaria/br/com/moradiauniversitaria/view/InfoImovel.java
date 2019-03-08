package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moradiauniversitaria.br.com.moradiauniversitaria.R;

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
