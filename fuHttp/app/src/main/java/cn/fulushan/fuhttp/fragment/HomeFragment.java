package cn.fulushan.fuhttp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.fulushan.fuhttp.R;
import cn.fulushan.fuhttp.activity.ReTrofitActivity;
import cn.fulushan.fuhttp.activity.ReTrofitRxJavaActivity;
import cn.fulushan.fuhttp.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    Unbinder unbinder;

    @BindView(R.id.listView)
    ListView listView;


    public static final String[] datas = new String[]{
            "ReTrofit基本使用","ReTrofit+Rxjava"};
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        toolbarTitle.setText(R.string.app_name);
        listView.setAdapter(new MainAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                doClick(position);
            }
        });
        return view;

    }


    private void doClick(int position) {


        switch (position) {
            /* Fragment fuHttp */
            case 0:// 项目中请使用常量替代
                startActivity(new Intent(getActivity(), ReTrofitActivity.class));
                break;
            case 1:// 项目中请使用常量替代
                startActivity(new Intent(getActivity(), ReTrofitRxJavaActivity.class));
                break;

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public Object getItem(int position) {
            return datas[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder;
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                View mView = getLayoutInflater().inflate(R.layout.listview_main, parent, false);
                mViewHolder.mTextView = (TextView) mView.findViewById(R.id.content);
                mView.setTag(mViewHolder);
                convertView = mView;
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }

            mViewHolder.mTextView.setText(datas[position]);
            return convertView;
        }


    }

    class ViewHolder {
        TextView mTextView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
