package com.feicui.listview_checkbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/7/26 18:01
 * Version 1.0
 * Params:
 * Description:    ListView 中含有全选、反选、CheckBox、删除
*/

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private List<MapBean> list;
    private MyBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        //  对lv进行数据绑定
        list = getLoadData();

        adapter = new MyBaseAdapter(list);
        lv.setAdapter(adapter);
    }


    /**
     * 给listview填充所需要的数据
     */
    public List<MapBean> getLoadData() {

        List<MapBean> list = new ArrayList<MapBean>();

        for (int i = 0; i < 10; i++) {
            MapBean bean = new MapBean("Uname_" + i, false);
            list.add(bean);
        }

        return list;
    }

    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel://取消按钮
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setStatus(false);
                }
                //notifyDataSetChanged意思是不用刷新listview
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_selectall://全选按钮
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setStatus(true);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_reverse://反选按钮
                for (int i = 0; i < list.size(); i++) {
                    boolean flag = list.get(i).getStatus();
                    if (flag) {
                        list.get(i).setStatus(false);
                    } else {
                        list.get(i).setStatus(true);
                    }
                }
                adapter.RefreashAdapter();
                break;
            case R.id.btn_delete:  //删除按钮
                // 定义临时集合，用于存储选中的数据
                List<MapBean> templist = new ArrayList<MapBean>();

                // 遍历原始数据集合，取出所有选中的数据，然后存储到临时集合templist中
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getStatus()) {
                        templist.add(list.get(i));
                    }
                }

                // 遍历临时集合templist，如果该集合不为空，就说明需要删除的数据，这个时候，直接从原始集合中把临时集合templist全部移除即可
                // 否则，就提示选择要删除的数据
                if (templist != null && templist.size() > 0) {
                    list.removeAll(templist);
                }else{
                    Toast.makeText(MainActivity.this , "请选择要删除的数据" , Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter.notifyDataSetChanged();
                break;
        }
    }
}
