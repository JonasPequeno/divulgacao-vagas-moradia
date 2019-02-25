package moradiauniversitaria.br.com.moradiauniversitaria;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SliderAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImageIds = new int[]{
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.ic_menu_send,
            R.drawable.ic_menu_share
    };

    public SliderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        ImageView imageView = new ImageView(this.mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

}
