package cn.fulushan.fuhttp.fragment.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import cn.fulushan.fuhttp.MainApplication;
import cn.fulushan.fuhttp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

        @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MainApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
