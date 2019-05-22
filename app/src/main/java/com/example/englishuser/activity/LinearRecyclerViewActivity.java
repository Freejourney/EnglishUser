package com.example.englishuser.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshView.SimpleXRefreshListener;
import com.andview.refreshview.XRefreshViewFooter;
import com.example.englishuser.Adapter.NewsAdapter;
import com.example.englishuser.Adapter.SimpleAdapter;
import com.example.englishuser.Bean.NewsModel;
import com.example.englishuser.R;
import com.example.englishuser.util.Config;
import com.example.englishuser.util.RequsetInterface;
import com.zwy.xlog.XLog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LinearRecyclerViewActivity extends Activity {
    NavigationView navigationView;
    RecyclerView recyclerView, nvrecycleview;
    SimpleAdapter adapter;
    NewsAdapter newsadapter;
    List<String> datalist = new ArrayList<>();
    List<NewsModel.Results> resultsList = new ArrayList<>();
    XRefreshView xRefreshView, nvxRefreshView;
    int lastVisibleItem = 0;
    //    GridLayoutManager layoutManager;
    LinearLayoutManager layoutManager, layoutManager2;
    private boolean isBottom = false;
    private int mLoadCount = 0, newsLoadCount = 0;
    private NewsModel news;

    private boolean isList = true;//false 为grid布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview2);
        initData();
        initView();
    }

    private void initView() {
        navigationView = findViewById(R.id.nv_news);
        xRefreshView = (XRefreshView) findViewById(R.id.xrefreshview);
        nvxRefreshView = navigationView.findViewById(R.id.nv_xrefreshview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_test_rv);
        nvrecycleview = navigationView.findViewById(R.id.nv_recycler_view);
        recyclerView.setHasFixedSize(true);
        nvrecycleview.setHasFixedSize(true);
        adapter = new SimpleAdapter(datalist, this);
        newsadapter = new NewsAdapter(resultsList, this);
        // 设置静默加载模式
//        xRefreshView1.setSilenceLoadMore();
        layoutManager = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        nvrecycleview.setLayoutManager(layoutManager2);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(adapter);
        nvrecycleview.setAdapter(newsadapter);

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);

        nvxRefreshView.setPinnedTime(1000);
        nvxRefreshView.setMoveForHorizontal(true);
        nvxRefreshView.setPullLoadEnable(true);
        nvxRefreshView.setAutoLoadMore(false);
        newsadapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        nvxRefreshView.enableReleaseToLoadMore(true);
        nvxRefreshView.enableRecyclerViewPullUp(true);
        nvxRefreshView.enablePullUpWhenLoadCompleted(true);

        //设置静默加载时提前加载的item个数
//        xefreshView1.setPreLoadCount(4);

        //设置Recyclerview的滑动监听
        xRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        xRefreshView.setXRefreshViewListener(new SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRefreshView.stopRefresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        for (int i = 0; i < 6; i++) {
//                            recyclerviewAdapter.insert(new Person("More ", mLoadCount + "21"),
//                                    recyclerviewAdapter.getAdapterItemCount());
//                        }
                        mLoadCount++;
                        if (mLoadCount >= 3) {//模拟没有更多数据的情况
                            xRefreshView.setLoadComplete(true);
                        } else {
                            // 刷新完成必须调用此方法停止加载
                            xRefreshView.stopLoadMore(false);
                            //当数据加载失败 不需要隐藏footerview时，可以调用以下方法，传入false，不传默认为true
                            // 同时在Footerview的onStateFinish(boolean hideFooter)，可以在hideFooter为false时，显示数据加载失败的ui
//                            xRefreshView1.stopLoadMore(false);
                        }
                    }
                }, 1000);
            }
        });

        nvxRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        nvxRefreshView.setXRefreshViewListener(new SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nvxRefreshView.stopRefresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        for (int i = 0; i < 6; i++) {
//                            recyclerviewAdapter.insert(new Person("More ", mLoadCount + "21"),
//                                    recyclerviewAdapter.getAdapterItemCount());
//                        }
                        newsLoadCount++;
                        if (newsLoadCount >= 3) {//模拟没有更多数据的情况
                            nvxRefreshView.setLoadComplete(true);
                        } else {
                            // 刷新完成必须调用此方法停止加载
                            nvxRefreshView.stopLoadMore(false);
                            //当数据加载失败 不需要隐藏footerview时，可以调用以下方法，传入false，不传默认为true
                            // 同时在Footerview的onStateFinish(boolean hideFooter)，可以在hideFooter为false时，显示数据加载失败的ui
//                            xRefreshView1.stopLoadMore(false);
                        }
                    }
                }, 1000);
            }
        });

//		// 实现Recyclerview的滚动监听，在这里可以自己处理到达底部加载更多的操作，可以不实现onLoadMore方法，更加自由
//		xRefreshView1.setOnRecyclerViewScrollListener(new OnScrollListener() {
//			@Override
//			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//				super.onScrolled(recyclerView, dx, dy);
//				lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//			}
//
//			public void onScrollStateChanged(RecyclerView recyclerView,
//											 int newState) {
//				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//					isBottom = recyclerviewAdapter.getItemCount() - 1 == lastVisibleItem;
//				}
//			}
//		});
    }

    private void initData() {
        String txt = Config.txt;
        String[] items = txt.split("\n");
        for (int i = 0; i < items.length; i++) {
            XLog.i("items", items[i]);
            datalist.add((i+1)+". "+items[i]);
        }
        Request();
    }

    private void Request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://content.guardianapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequsetInterface requestinterface =retrofit.create(RequsetInterface.class);
        Call<NewsModel> newsModelCall = requestinterface.getNewsCall();
        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    XLog.i("Response", response.body().toString());
                    news = response.body();
                    for (int i = 0; i < news.getResponse().getResults().size(); i++) {
                        resultsList.add(news.getResponse().getResults().get(i));
                    }
                    newsadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            case R.id.menu_clear:
                mLoadCount = 0;
                xRefreshView.setLoadComplete(false);
                //切换布局
                isList = !isList;

                if (isList) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                }
                //当切换layoutManager时，需调用此方法
                xRefreshView.notifyLayoutManagerChanged();
                break;
            case R.id.menu_refresh:
                xRefreshView.startRefresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}