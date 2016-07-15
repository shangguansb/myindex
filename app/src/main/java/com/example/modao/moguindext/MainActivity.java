package com.example.modao.moguindext;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.modao.moguindext.adapter.twitRecycleAdapter;
import com.example.modao.moguindext.wedgit.DividerItemDecoration;
import com.example.modao.moguindext.wedgit.MySwipeRefreshLayout;

import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    RecyclerView mRecyclerview;
    RecyclerView.LayoutManager mLayoutManager;
    String[] s = new String[]{"dwda", "dwhnudh", "dwhnudh",
            "dwhnudh", "dwhnudh", "dwhnudh", "dwhnudh", "dwhnudh", "dwhnudh"};
    public twitRecycleAdapter madapter;
    MySwipeRefreshLayout swipeRefreshLayout;
    AppBarLayout appBarLayout;
    ViewPager viewPager;
    private List<ImageView> list = new ArrayList<ImageView>();
    private List<ImageView> pointList = new ArrayList<ImageView>();
    int num = 0;
    private GuidePageAdapter adapter = null;
    private ViewGroup viewGroup = null;
    public Handler myHandler = null;
    private Timer timer = null;
    Button but_focus = null;
    Button but_sug = null;
    Button but_500like = null;
    Button but_200like = null;
    Button but_100like = null;
    RelativeLayout relaoyt_live = null;
    RelativeLayout relaoyt_rank = null;
    RelativeLayout relaoyt_fashion = null;
    RelativeLayout relaoyt_enter = null;
    RelativeLayout relaoyt_makeup = null;
    RelativeLayout relaoyt_find = null;
    String msg = null;
    LinearLayout line_search = null;
    View mIndexSearchBackgroundView;
    Button mIndexFocusBut;
    CoordinatorLayout mCoordnatorLayout;
    HorizontalScrollView mHorizontalScrollView;
    AppBarLayout mAppbarLayout;
    Button mButtonSug;
    LinearLayout mFillterLinearLayout;
    View mSupView;
    Button mButtonAddPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }


    private void initView() {
        mButtonAddPeople = (Button) findViewById(R.id.add_people);
        mSupView = findViewById(R.id.sup_view);
        mFillterLinearLayout = (LinearLayout) findViewById(R.id.fillter_linearlayout);
        mAppbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mButtonSug = (Button) findViewById(R.id.sug);
        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.index_scrollview);
        mCoordnatorLayout = (CoordinatorLayout) findViewById(R.id.index_coodernator);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerview.requestDisallowInterceptTouchEvent(false);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        but_focus = (Button) findViewById(R.id.focus);
        but_500like = (Button) findViewById(R.id.like_fivhun);
        but_200like = (Button) findViewById(R.id.like_twohun);
        but_sug = (Button) findViewById(R.id.sug);
        but_100like = (Button) findViewById(R.id.like_onehun);
        but_sug.setOnClickListener(MainActivity.this);
        but_500like.setOnClickListener(MainActivity.this);
        but_200like.setOnClickListener(MainActivity.this);
        but_100like.setOnClickListener(MainActivity.this);
        relaoyt_live = (RelativeLayout) findViewById(R.id.rel_live);
        relaoyt_rank = (RelativeLayout) findViewById(R.id.rel_rank);
        relaoyt_fashion = (RelativeLayout) findViewById(R.id.rel_fashion);
        relaoyt_enter = (RelativeLayout) findViewById(R.id.rel_enter);
        relaoyt_makeup = (RelativeLayout) findViewById(R.id.rel_makeup);
        relaoyt_find = (RelativeLayout) findViewById(R.id.rel_find);
        relaoyt_live.setOnClickListener(MainActivity.this);
        relaoyt_rank.setOnClickListener(MainActivity.this);
        relaoyt_fashion.setOnClickListener(MainActivity.this);
        relaoyt_enter.setOnClickListener(MainActivity.this);
        relaoyt_makeup.setOnClickListener(MainActivity.this);
        relaoyt_find.setOnClickListener(MainActivity.this);
        mIndexSearchBackgroundView = findViewById(R.id.index_search_bacground_view);
        mIndexFocusBut = (Button) findViewById(R.id.focus);
        mIndexFocusBut.setOnClickListener(this);
        mButtonSug.setOnClickListener(this);
        line_search = (LinearLayout) findViewById(R.id.search_linearlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            line_search.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    if (line_search.getTop() < 50) {
                        View supview = findViewById(R.id.sup_view);
                        supview.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setHasFixedSize(true);
        madapter = new twitRecycleAdapter(this,s);
        mRecyclerview.setAdapter(madapter);
//        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
//                        swipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
//                        swipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });

        viewGroup = (LinearLayout)
                findViewById(R.id.description_layout);
        mCoordnatorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                return false;
            }
        });
        mAppbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                final int[] position = new int[2];
                mHorizontalScrollView.getLocationOnScreen(position);
                float alppa = (float) (1604 - position[1]) / 1310;
                mIndexSearchBackgroundView.setAlpha(alppa);
                if (alppa >= 0.6) {
                    mButtonAddPeople.setBackgroundResource(R.drawable.bjc);
                } else {
                    mButtonAddPeople.setBackgroundResource(R.drawable.bjd);
                }
                mSupView.setAlpha(alppa);
            }
        });
//        swipeRefreshLayout = (MySwipeRefreshLayout) findViewById(R.id.index_swiprefreshlayout);
//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
//                android.R.color.holo_red_light, android.R.color.holo_orange_light,
//                android.R.color.holo_green_light);

//        swipeRefreshLayout.setOnRefreshListener(new MySwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (MainActivity.this!= null) {
//                            SuperToast.create(MainActivity.this, "加载完成", SuperToast.Duration.MEDIUM,
//                                    Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
//                            swipeRefreshLayout.setRefreshing(false);
//                        }
//
//
//                    }
//                }, 5000);
//
//
//            }
//        });
//        swipeRefreshLayout.setBottomColor(android.R.color.holo_blue_light,
//                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
//        swipeRefreshLayout.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
//            @Override
//            public void onLoad() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (MainActivity.this != null) {
//                            SuperToast.create(MainActivity.this, "加载完成", SuperToast.Duration.MEDIUM,
//                                    Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
//                            swipeRefreshLayout.setLoading(false);
//
//                        }
//                    }
//                }, 5000);
//
//            }
//        });
    }

    private void initData() {
        list.clear();
        ImageView imageView0 = new ImageView(MainActivity.this);
        Glide.with(this).load("http://i12.tietuku.cn/b6db401cce66841e.jpg").centerCrop().into(imageView0);
        ImageView imageView1 = new ImageView(this);
        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/74d28c89f2857852.jpg").centerCrop().
                into(imageView1);
        ImageView imageView2 = new ImageView(this);
        Glide.with(MainActivity.this).load("http://i2.buimg.com/029168290e434623.jpg").centerCrop().
                into(imageView2);

        ImageView imageView3 = new ImageView(this);
        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
                centerCrop().into(imageView3);
        ImageView imageView4 = new ImageView(this);
        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
                centerCrop().into(imageView4);
        ImageView imageView5 = new ImageView(this);
        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
                centerCrop().into(imageView5);
        ImageView imageView6 = new ImageView(this);
        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
                centerCrop().into(imageView6);
        list.add(imageView0);
        list.add(imageView1);
        list.add(imageView2);
        list.add(imageView3);
        list.add(imageView4);
        list.add(imageView5);
        list.add(imageView6);
        viewPager.setOnPageChangeListener(MainActivity.this);
        adapter = new GuidePageAdapter();
        viewPager.setAdapter(adapter);
        pointList.clear();
        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                    21,
                    21);
            DisplayMetrics dm = getResources().getDisplayMetrics();

//            int width = dm.widthPixels;
//            margin.setMargins((width / 2) - list.size() * 50 / 2 + 50 * i - 40,
//                    10, 20, 10);

            margin.setMargins(2,
                    10, 10, 10);

            ImageView yuanimgview = new ImageView(MainActivity.this);

            yuanimgview.setLayoutParams(margin);

            // yuanimageViews = new ImageView[list.size()];

            pointList.add(yuanimgview);
            if (i == 0) {

                pointList.get(i).setBackgroundResource(R.drawable.abu);
            } else {
                pointList.get(i).setBackgroundResource(R.drawable.point);
                pointList.get(i).setAlpha(1F);
            }

            viewGroup.addView(pointList.get(i), margin);

        }
        if (myHandler == null) {
            myHandler = new Handler() {

                public void handleMessage(Message msg) {
                    if (viewPager != null && list != null) {
                        viewPager.setCurrentItem(num);
                        if (num == list.size() - 1) {
                            num = 0;
                        } else {
                            num++;
                        }
                    }
                }
            };
        }
        mytask task = new mytask();
        if (timer == null) {
            timer = new Timer();
            timer.schedule(task, 0, 10000);
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        if (list != null) {
            num = arg0;
            for (int i = 0; i < list.size(); i++) {
                pointList.get(i).setBackgroundResource(R.drawable.abu);

                if (arg0 % list.size() != i) {
                    pointList.get(i).setBackgroundResource(R.drawable.point);

                }
            }
        }


        // // yuanimgview.setBackgroundResource(R.drawable.yuan_white);
        // yuanlist.get(3).setBackgroundResource(R.drawable.yuan);
        // yuanlist.get(3).setAlpha(1);
        // // this.();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        msg = null;
        switch (view.getId()) {
            case R.id.rel_live:
                msg = "这是直播界面";
                openactivity();
                break;
            case R.id.rel_rank:
                msg = "这是红人榜界面";
                openactivity();
                break;
            case R.id.rel_fashion:
                msg = "这是时尚搭配界面";
                openactivity();
                break;
            case R.id.rel_enter:
                msg = "这是MUGU娱乐界面";
                openactivity();
                break;
            case R.id.rel_makeup:

                msg = "这是妆容界面";
                openactivity();
                break;
            case R.id.rel_find:
                msg = "这是发现好物界面";
                openactivity();
                break;
            case R.id.focus:
                mIndexFocusBut.setText("focus");
                ObjectAnimator.ofFloat(mIndexSearchBackgroundView, "Alpha", 0.0F, 1.0F).setDuration(500)
                        .start();
                break;
            case R.id.sug:

                mIndexFocusBut.setText("sug");
                break;
        }


    }

    public void openactivity() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, actIndicator.class);
        intent.putExtra("msg", msg);
        startActivity(intent);
    }

    class mytask extends TimerTask {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (myHandler != null) {
                myHandler.sendEmptyMessageDelayed(0, 0);
            }

        }

    }

    class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10000;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            // ((ViewPager) arg0).addView(list.get(arg1));
            // return list.get(arg1);
            arg1 %= list.size();
            if (arg1 < 0) {
                arg1 = list.size() + arg1;
            }
            ImageView view = list.get(arg1);

            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }

            viewPager.addView(view);
            // add listeners here if necessary
            return view;
        }

    }

    @Override
    protected void onDestroy() {
        list.clear();

//        rects = null;
        list = null;
        pointList.clear();
        pointList = null;
        timer.cancel();
        super.onDestroy();
    }
}
