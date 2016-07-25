package com.example.modao.moguindext;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.modao.moguindext.Utils.ImageLoader.MoguImageJsonLoader;
import com.example.modao.moguindext.Utils.ImageLoader.MoguResponse;
import com.example.modao.moguindext.Utils.MoguRefreshPage;
import com.example.modao.moguindext.adapter.RecyclerAdapter;
import com.example.modao.moguindext.wedgit.MoguLinearLayout;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.modao.moguindext.wedgit.MySwipeRefreshLayout;

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
    public RecyclerAdapter madapter;
    MySwipeRefreshLayout swipeRefreshLayout;

    ViewPager mViewPager;
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
    LinearLayout mLinearLayoutSearch = null;
    View mIndexSearchBackgroundView;
    Button mIndexFocusBut;
    CoordinatorLayout mCoordnatorLayout;
    HorizontalScrollView mHorizontalScrollView;
    AppBarLayout mAppbarLayout;
    Button mButtonSug;
    LinearLayout mFillterLinearLayout;
    View mSupView;
    Button mButtonAddPeople;
    GridLayout mGridLayout;
    float y = 0;
    MoguLinearLayout mLinearLayoutParent;
    View mViewProcess;
    MoguRefreshPage mMoguRefresh;
    static boolean isdown = false;
    GestureDetector mges;
    Button mButtonFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }


    private void initView() {

        mButtonFive = (Button) findViewById(R.id.like_fivhun);
        mViewProcess = findViewById(R.id.reresh_process);
        mLinearLayoutParent = (MoguLinearLayout) findViewById(R.id.linearlayout_parent);
        mGridLayout = (GridLayout) findViewById(R.id.gridlayout);
        mButtonAddPeople = (Button) findViewById(R.id.add_people);
        mSupView = findViewById(R.id.sup_view);
        mFillterLinearLayout = (LinearLayout) findViewById(R.id.fillter_linearlayout);
        mAppbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mButtonSug = (Button) findViewById(R.id.sug);
        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.index_scrollview);
        mCoordnatorLayout = (CoordinatorLayout) findViewById(R.id.index_coodernator);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
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
        mLinearLayoutSearch = (LinearLayout) findViewById(R.id.search_linearlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mLinearLayoutSearch.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    if (mLinearLayoutSearch.getTop() < 50) {
                        View supview = findViewById(R.id.sup_view);
                        supview.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setHasFixedSize(true);
        madapter = new RecyclerAdapter(this, s);
        mRecyclerview.setAdapter(madapter);
//        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
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
                int[] position = new int[2];
                int[] processPosition = new int[2];
                mViewProcess.getLocationOnScreen(processPosition);
                mHorizontalScrollView.getLocationOnScreen(position);

                if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
                    Log.e("action-------------", "down");
//

                }
                VelocityTracker velocityTracker = VelocityTracker.obtain();
                velocityTracker.addMovement(motionEvent);
                velocityTracker.computeCurrentVelocity(10);
                float yVelocity = velocityTracker.getYVelocity();
                mButtonFive.setText(yVelocity + "");
                if (yVelocity > 0 && (processPosition[1] > -27) && (position[1] > 1590)) {
                    if (yVelocity > 80) {
                        yVelocity = 80;
                    }
                    mLinearLayoutParent.scrollBy(0, -(int) yVelocity);
//                    mCoordnatorLayout.stopNestedScroll();
//                    mLinearLayoutSearch.setVisibility(View.INVISIBLE);
                } else if (!(yVelocity > 0) && (processPosition[1] > -25)) {
                    mAppbarLayout.setExpanded(true);
                }
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
//                    mLinearLayoutParent.smoothscrollby(0, 0);

                    if (mLinearLayoutSearch.getVisibility() == View.INVISIBLE) {
                        mLinearLayoutSearch.setVisibility(View.VISIBLE);
                        ObjectAnimator.ofFloat(mLinearLayoutSearch, "alpha", 0f, 1.0f).setDuration(300).start();
                    }
                    if (mMoguRefresh == null) {
                        mMoguRefresh = new MoguRefreshPage(MainActivity.this);
                    }
                    if (mMoguRefresh.isRefreshing() == false) {
                        mMoguRefresh.Refresh(processPosition[1]);
                    } else {
                        mLinearLayoutParent.smoothscrollby(0, -200);
                    }
                    y = Integer.MAX_VALUE;
                }
                if (MotionEvent.ACTION_MOVE == motionEvent.getAction()) {
                    if (processPosition[1] > -26) {
                        mLinearLayoutSearch.setVisibility(View.INVISIBLE);
                        y = motionEvent.getRawY();
                    }

                }
                if (mMoguRefresh != null && mAppbarLayout != null && mMoguRefresh.isRefreshing() == true) {
                    mRecyclerview.scrollToPosition(0);
                    mAppbarLayout.setExpanded(true);

                }
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
                if (position[1] > 1592) {
//                    mLinearLayoutSearch.setVisibility(View.INVISIBLE);
                } else {
//                    mLinearLayoutSearch.setVisibility(View.VISIBLE);
                }
                if (mMoguRefresh != null && mAppbarLayout != null && mMoguRefresh.isRefreshing() == true) {
                    mRecyclerview.scrollToPosition(0);
                }
            }
        });


        mRecyclerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int[] position = new int[2];
                int[] processPosition = new int[2];
                mViewProcess.getLocationOnScreen(processPosition);
                mHorizontalScrollView.getLocationOnScreen(position);
                VelocityTracker velocityTracker = VelocityTracker.obtain();
                ViewConfiguration.get(getApplicationContext()).getScaledMinimumFlingVelocity();
                velocityTracker.addMovement(motionEvent);
                velocityTracker.computeCurrentVelocity(10);
                float yVelocity = velocityTracker.getYVelocity();
                mButtonSug.setText(yVelocity + "");
                if (yVelocity > 0 && (position[1] > 1500)) {
                    LinearLayoutManager lm = (LinearLayoutManager) mRecyclerview.getLayoutManager();
                    if (lm.findViewByPosition(lm.findFirstVisibleItemPosition()).getTop() == 0
                            && lm.findFirstVisibleItemPosition() == 0) {
                        if (yVelocity > 80) {
                            yVelocity = 80;
                        }
                        mLinearLayoutParent.scrollBy(0, -(int) yVelocity);
//                    mCoordnatorLayout.stopNestedScroll();
//                    mLinearLayoutSearch.setVisibility(View.INVISIBLE);
                    }
                }
                if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
                    Log.e("------------------item", "down");

                }

                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
//                    mLinearLayoutParent.smoothscrollby(0, 0);
                    if (mLinearLayoutSearch.getVisibility() == View.INVISIBLE) {
                        mLinearLayoutSearch.setVisibility(View.VISIBLE);
                        ObjectAnimator.ofFloat(mLinearLayoutSearch, "alpha", 0f, 1.0f).setDuration(300).start();
                    }
                    if (mMoguRefresh == null) {
                        mMoguRefresh = new MoguRefreshPage(MainActivity.this);
                    }
                    if (mMoguRefresh.isRefreshing() == false) {
                        mMoguRefresh.Refresh(processPosition[1]);
                    } else {
                        mLinearLayoutParent.smoothscrollby(0, -200);
                    }
                    if (yVelocity > 0) {
                        LinearLayoutManager lm = (LinearLayoutManager) mRecyclerview.getLayoutManager();
                        if (lm.findViewByPosition(lm.findFirstVisibleItemPosition()).getTop() == 0
                                && lm.findFirstVisibleItemPosition() == 0) {


                        }
                    }

//                    appBarLayout.setExpanded(isdown);
//                    y = Integer.MAX_VALUE;
                }
                if (MotionEvent.ACTION_MOVE == motionEvent.getAction()) {
                    if (position[1] > 1596) {
                        mLinearLayoutSearch.setVisibility(View.INVISIBLE);
//                        appBarLayout.setExpanded(true);
//                        if (motionEvent.getRawY() > y) {
//                            isdown = true;
//                        }
                    }

                }
                if (mMoguRefresh != null && mAppbarLayout != null && mMoguRefresh.isRefreshing() == true) {
                    mRecyclerview.scrollToPosition(0);
                    mAppbarLayout.setExpanded(true);

                }
                return false;
            }
        });

        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int[] position = new int[2];


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mHorizontalScrollView.getLocationOnScreen(position);
                if (position[1] > 400) {
                    mRecyclerview.scrollToPosition(0);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }

    private void initData() {
        MoguImageJsonLoader imageJsonLoader = MoguImageJsonLoader.build(MainActivity.this);
        list.clear();
        ImageView imageView0 = new ImageView(MainActivity.this);
        imageJsonLoader.
                loadJson("http://apis.baidu.com/heweather/weather/free", new MoguResponse.Listener() {
                    @Override
                    public void onSuccess(String json) {
                        mButtonSug.setText(json);
                    }

                    @Override
                    public void onFail() {
                        mButtonSug.setText("失败");
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest("http://apis.baidu.com/heweather/weather/free", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                mButtonSug.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        Glide.with(this).load("http://i12.tietuku.cn/b6db401cce66841e.jpg").centerCrop().into(imageView0);
        ImageView imageView1 = new ImageView(this);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageJsonLoader.bindBitmap("http://i12.tietuku.cn/74d28c89f2857852.jpg", imageView1);
//        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/74d28c89f2857852.jpg").centerCrop().
//                into(imageView1);
        ImageView imageView2 = new ImageView(this);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageJsonLoader.bindBitmap("http://i2.buimg.com/029168290e434623.jpg", imageView2);
//        Glide.with(MainActivity.this).load("http://i2.buimg.com/029168290e434623.jpg").centerCrop().
//                into(imageView2);
        ImageView imageView3 = new ImageView(this);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageJsonLoader.bindBitmap("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg", imageView3);
//        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
//                centerCrop().into(imageView3);
        ImageView imageView4 = new ImageView(this);
        imageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageJsonLoader.bindBitmap("http://i12.tietuku.cn/74d28c89f2857852.jpg", imageView4);
//        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
//                centerCrop().into(imageView4);
        ImageView imageView5 = new ImageView(this);
        imageView5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageJsonLoader.bindBitmap("http://i12.tietuku.cn/74d28c89f2857852.jpg", imageView5);
//        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
//                centerCrop().into(imageView5);
        ImageView imageView6 = new ImageView(this);
        imageView6.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageJsonLoader.bindBitmap("http://i12.tietuku.cn/74d28c89f2857852.jpg", imageView6, 200, 400);
//        Glide.with(MainActivity.this).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
//                centerCrop().into(imageView6);
        list.add(imageView0);
        list.add(imageView1);
        list.add(imageView2);
        list.add(imageView3);
        list.add(imageView4);
        list.add(imageView5);
        list.add(imageView6);
        mViewPager.setOnPageChangeListener(MainActivity.this);
        adapter = new GuidePageAdapter();
        mViewPager.setAdapter(adapter);
        pointList.clear();
        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(21, 21);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            margin.setMargins(2,
                    10, 10, 10);

            ImageView yuanimgview = new ImageView(MainActivity.this);
            yuanimgview.setLayoutParams(margin);
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
                    if (mViewPager != null && list != null) {
                        mViewPager.setCurrentItem(num);
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
            timer.schedule(task, 0, 2000);
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
                mAppbarLayout.setEnabled(true);

                break;
            case R.id.sug:
                break;
        }


    }

    public void openactivity() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ActIndicator.class);
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

            mViewPager.addView(view);
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

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //继承了Activity的onTouchEvent方法，直接监听点击事件
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            //当手指按下的时候
//            x1 = event.getX();
//            y1 = event.getY();
//        }
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            //当手指离开的时候
//            x2 = event.getX();
//            y2 = event.getY();
//            if (y1 - y2 > 50) {
//                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
//            } else if (y2 - y1 > 50) {
//                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
//            } else if (x1 - x2 > 50) {
//                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
//            } else if (x2 - x1 > 50) {
//                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
//            }
//        }
//        return true;
//    }

}
