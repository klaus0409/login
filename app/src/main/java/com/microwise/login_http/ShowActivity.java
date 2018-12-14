package com.microwise.login_http;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.microwise.login_http.utils.ToastUtils;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    private ArrayList<String> datas = new ArrayList<>();

    private GridView gridView;
    private TextView tvEnd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();

        initData();
    }


    private void initView() {

        gridView = findViewById(R.id.gridview);
        tvEnd = findViewById(R.id.tv_end);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ToastUtils.showShort(ShowActivity.this, datas.get(position) + "点击了第" + position + "个");
            }
        });
    }

    private void initData() {

        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        //照片录入  查询  同步 盘点  位置查找 构建查找 校验  上传 设置
        datas.add("照片录入");
        datas.add("查询");
        datas.add("同步");
        datas.add("盘点");
        datas.add("位置查找");
        datas.add("构建查找");
        datas.add("校验");
        datas.add("上传");
        datas.add("设置");

        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int postion, View convertView, ViewGroup viewGroup) {


                View view;
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    view = View.inflate(ShowActivity.this, R.layout.item_show_gridview, null);
                    holder.tv_name = view.findViewById(R.id.tv_name);

                    view.setTag(holder);
                } else {

                    view = convertView;
                    holder = (ViewHolder) view.getTag();
                }

                //日期
                holder.tv_name.setText(datas.get(postion));


                return view;
            }
        });
    }


    private class ViewHolder {


        public TextView tv_name;

    }
}
