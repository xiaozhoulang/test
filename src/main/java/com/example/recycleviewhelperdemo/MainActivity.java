package com.example.recycleviewhelperdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String url = "http://i1.letvimg.com/lc09_yunzhuanma/201705/26/15/31/d6b481af5a5b85f14bede6dbd3216dc2_v2_NDcyMzk5MzMw/thumb/2_400_300.jpg";
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {

        List<Bean> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new Bean("张飞" + i, "北京" + i, i));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this, R.layout.item, list);
        recyclerView.setAdapter(mAdapter);
        /**自带动画*/

       // mAdapter.openLoadAnimation();
        //mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM );
        //mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT );
      // mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT  );
        /*是否仅展示一次*/
        mAdapter.isFirstOnly(false);
        /*自定义动画效果*/
        /*mAdapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{
                        ObjectAnimator.ofFloat(view, "scaleX", 0.2f, 1f),
                        ObjectAnimator.ofFloat(view, "scaleY", 0.2f, 1f),
                        ObjectAnimator.ofFloat(view, "translationX", view.getRootView().getWidth(), 0)
                };
            }
        });*/

        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        mAdapter.setUpFetchEnable(true);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                            Toast.makeText(MainActivity.this, "h哈哈", Toast.LENGTH_LONG).show();
                           // mAdapter.loadMoreFail();

                            mAdapter.loadMoreComplete();
                    }

                }, 1000);
            }
        }, recyclerView);


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Toast.makeText(MainActivity.this, "on" + position, Toast.LENGTH_SHORT).show();

                    }
                }
        );

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("111", "onItemChildClick: ");
                switch (view.getId()) {
                    case R.id.tv1:
                        Toast.makeText(MainActivity.this,((TextView)view).getText().toString()+1, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.tv2:
                        Toast.makeText(MainActivity.this, ((TextView)view).getText().toString()+2, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.tv3:
                        Toast.makeText(MainActivity.this, ((TextView)view).getText().toString()+3, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.iv:
                        Toast.makeText(MainActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });
    }

    private class MyAdapter extends BaseQuickAdapter<Bean, BaseViewHolder> {

        private Context mContext;

        public MyAdapter(Context mContext, @LayoutRes int layoutResId, @Nullable List<Bean> data) {
            super(layoutResId, data);
            this.mContext = mContext;
        }

        @Override
        protected void convert(BaseViewHolder helper, Bean item) {
            helper.setText(R.id.tv1, item.getName()).setText(R.id.tv2, item.getAdress()).setText(R.id.tv3, String.valueOf(item.getAge()));
            //helper.setImageResource(R.id.iv,R.mipmap.ic_launcher);
            Picasso.with(mContext).load(url).into((ImageView) helper.getView(R.id.iv));
            helper.addOnClickListener(R.id.tv1).addOnClickListener(R.id.tv2).addOnClickListener(R.id.tv3).addOnClickListener(R.id.iv);
        }
    }
}
