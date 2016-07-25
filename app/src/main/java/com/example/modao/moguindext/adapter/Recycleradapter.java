package com.example.modao.moguindext.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.modao.moguindext.MainActivity;
import com.example.modao.moguindext.R;
import com.example.modao.moguindext.Utils.MoguUtils;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by modao on 16/7/25.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public String[] str;
    public ArrayList contentID = new ArrayList<>();
    List<RelativeLayout> rela_list = null;
    public View mView;
    PopupWindow mWindow;
    public Context context;
    public int currentPage = 0;
    public ViewPager mmViewpager;

    public RecyclerAdapter(Context context, String[] datas) {
        str = datas;
        this.context = context;
        initPopWindow();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_active_item, parent, false);
        RecyclerView.ViewHolder hoder = new MyViewHolder1(v);
        return hoder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder1 mholder = (MyViewHolder1) holder;
    }

    @Override
    public int getItemCount() {
        return str.length;
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        public TextView text;
        public TextView text_title;
        public View view;
        public ViewPager mViewPager;
        public View mPopView;
        RelativeLayout mReal;
        LinearLayout mItemParentLinearLayout;
        List<ImageView> mImageViews = new ArrayList<ImageView>();
        int bitmapHeight1 = 0;
        int bitmapHeight2 = 0;
        int bitmapHeight3 = 0;

        int[] heights = new int[50];


        public MyViewHolder1(View itemView) {
            super(itemView);
            mView = itemView;
            view = itemView;
            int mvpagerWidth = 0;
            mItemParentLinearLayout = (LinearLayout) mView.findViewById(R.id.item_parent_linearlayout);
            mItemParentLinearLayout.setBackgroundResource(R.drawable.item_radius_background);
            mItemParentLinearLayout.getLayoutParams();
            text = (TextView) itemView.findViewById(R.id.username_text);
            mPopView = itemView.findViewById(R.id.popwinbut);
            mReal = (RelativeLayout) mView.findViewById(R.id.item_top_rel);
            text_title = (TextView) itemView.findViewById(R.id.user_time_text);
            mViewPager = (ViewPager) itemView.findViewById(R.id.item_viewpager);
            rela_list = new ArrayList<>();
            RelativeLayout rel1 = new RelativeLayout(itemView.getContext());
            RelativeLayout rel2 = new RelativeLayout(itemView.getContext());
            RelativeLayout rel3 = new RelativeLayout(itemView.getContext());
            RelativeLayout rel4 = new RelativeLayout(itemView.getContext());
            ImageView img1 = new ImageView(itemView.getContext());
            ImageView img2 = new ImageView(itemView.getContext());
            ImageView img3 = new ImageView(itemView.getContext());
            ImageView img4 = new ImageView(itemView.getContext());
            mImageViews.add(img1);
            mImageViews.add(img2);
            mImageViews.add(img3);
            mImageViews.add(img4);
            img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img3.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img4.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img1.setBackgroundResource(R.drawable.avator);
            img2.setBackgroundResource(R.drawable.viewpager);
            img3.setBackgroundResource(R.drawable.threepage);
            img4.setBackgroundResource(R.drawable.page4);
            Bitmap bitmap1 = BitmapFactory.decodeResource(mView.getContext().getResources(), R.drawable.avator);
            Bitmap bitmap2 = BitmapFactory.decodeResource(mView.getContext().getResources(), R.drawable.viewpager);
            Bitmap bitmap3 = BitmapFactory.decodeResource(mView.getContext().getResources(), R.drawable.threepage);
            Bitmap bitmap4 = BitmapFactory.decodeResource(mView.getContext().getResources(), R.drawable.page4);
            bitmapHeight1 = bitmap1.getHeight();
            bitmapHeight2 = bitmap2.getHeight();
            bitmapHeight3 = bitmap3.getHeight();
            int bitmapHeight4 = bitmap4.getHeight();
            mItemParentLinearLayout.measure(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            mvpagerWidth = mItemParentLinearLayout.getMeasuredWidth();
            heights[0] = bitmapHeight1 * mvpagerWidth / bitmap1.getWidth();
            heights[1] = bitmapHeight2 * mvpagerWidth / bitmap2.getWidth();
            heights[2] = bitmapHeight3 * mvpagerWidth / bitmap3.getWidth();
            heights[3] = bitmapHeight4 * mvpagerWidth / bitmap4.getWidth();
            img1.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            img2.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            img3.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            img4.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            LinearLayout.LayoutParams vpagerParems
                    = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heights[0]);
            mViewPager.setLayoutParams(vpagerParems);
            rel1.addView(img1);
            rel2.addView(img2);
            rel3.addView(img3);
            rel4.addView(img4);
            CircleImageView point = new CircleImageView(itemView.getContext());
            point.setBorderWidth(MoguUtils.DipToPixels(itemView.getContext(), 1));
            point.setBorderColor(Color.WHITE);
            point.setImageResource(R.drawable.amq);
            int marginlaft = MoguUtils.DipToPixels(itemView.getContext(), 230);
            int margintop = MoguUtils.DipToPixels(itemView.getContext(), 230);
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(MoguUtils.DipToPixels(itemView.getContext(), 11)
                    , MoguUtils.DipToPixels(itemView.getContext(), 11));
//            p.width =;
//            p.height = 30;
            p.leftMargin = marginlaft;
            p.topMargin = margintop;
            point.setLayoutParams(p);
            rela_list.add(rel1);
            rel1.addView(point);
            rela_list.add(rel2);
            rela_list.add(rel3);
            rela_list.add(rel4);
            RecyclerViewItemViewpagerAdapter adapter = new RecyclerViewItemViewpagerAdapter(rela_list);
            mViewPager.setAdapter(adapter);
//            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (mViewPager.getChildAt(1) != null) {
//                                                           ((RelativeLayout) mViewPager.getChildAt(1)).getChildAt(0).getHeight();
                        LinearLayout.LayoutParams ls = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
                        ls.height = (int) (heights[position] - (Math.abs(positionOffset) * (heights[position]
                                - heights[position + 1])));
                        mViewPager.setLayoutParams(ls);
                        float sc = positionOffset * ((float) heights[position + 1] / (float) heights[position] - 1) + 1;

                        if (position < mImageViews.size() - 1) {
                            if (heights[position] < heights[position + 1]) {
                                float scale = ((float) ls.height) / heights[position];
                                mImageViews.get(position).setScaleX(scale);
                                mImageViews.get(position).setScaleY(1);
                                scale = heights[position + 1] / (float) ls.height;
                                mImageViews.get(position + 1).setScaleX(1);
                                mImageViews.get(position + 1).setScaleY(scale);
//                                mImageViews.get(position + 1).getDrawable().setBounds(new Rect(0,top,ls.width,bottom));
                            } else {
                                float scale = ((float) ls.height) / heights[position + 1];
                                mImageViews.get(position + 1).setScaleX(scale);
                                mImageViews.get(position + 1).setScaleY(1);
                                scale = heights[position] / (float) ls.height;
                                mImageViews.get(position).setScaleX(1);
                                mImageViews.get(position).setScaleY(scale);

//                                mImageViews.get(position).getDrawable().setBounds(new Rect(0,top,ls.width,bottom));
                            }
                        }
                        Log.e("-------------viewpager", position + "--------" + sc);
//
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };
            mViewPager.setOnPageChangeListener(listener);
            mPopView.setOnClickListener(new View.OnClickListener()

                                        {
                                            @Override
                                            public void onClick(View view) {
                                                // 在底部显示
                                                mWindow.showAtLocation(((MainActivity) context).findViewById(R.id.index_scrollview),
                                                        Gravity.BOTTOM, 0, 0);
                                                WindowManager.LayoutParams params = ((MainActivity) context).getWindow().getAttributes();
                                                params.alpha = 0.7f;
                                                ((MainActivity) context).getWindow().setAttributes(params);

                                            }


                                        }

            );

        }

        public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
            public void transformPage(View view, float position) {
//                int pageWidth = view.getWidth();
//                int pageHeight = view.getHeight();
//                Log.e("------------height", position + "");
                if (position <= 1) { // [-1,1]

                    if (position < 0) {
                        int currentPos = mViewPager.indexOfChild(view);
                        if (currentPos != 2) {
                            LinearLayout.LayoutParams ls = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
                            if (mViewPager.getCurrentItem() < 3 && heights[mViewPager.getCurrentItem()] != 0) {
                                ls.height = (int) (((RelativeLayout) view).getChildAt(0).getHeight() - (Math.abs(position))
                                        * (((RelativeLayout) view).getChildAt(0).getHeight()
                                        - heights[currentPos + 1]));
                                mViewPager.setLayoutParams(ls);
                            }
                        }


                    } else if (0 == position) {
//                        LinearLayout.LayoutParams ls = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
//                        ls.height = (int) (mImageViews.get(mViewPager.getCurrentItem()).getHeight());
//                        mViewPager.setLayoutParams(ls);

                    }

                }
                return;
            }
        }
    }


    public void initPopWindow() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mPopView = inflater.inflate(R.layout.pop_window, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        mWindow = new PopupWindow(mPopView,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        mWindow.setFocusable(true);

// 这里检验popWindow里的button是否可以点击
        Button first = (Button) mPopView.findViewById(R.id.first);
        Button cancle = (Button) mPopView.findViewById(R.id.second);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWindow.dismiss();
            }
        });
        first.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SuperToast.create(context, "取消关注", SuperToast.Duration.MEDIUM,
                        Style.getStyle(Style.RED, SuperToast.Animations.FLYIN)).show();
                mWindow.dismiss();
            }
        });
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mWindow.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        mWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        //popWindow消失监听方法
        mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                mWindow.dismiss();
                WindowManager.LayoutParams params = ((MainActivity) context).getWindow().getAttributes();
                params.alpha = 1f;
                ((MainActivity) context).getWindow().setAttributes(params);
                System.out.println("popWindow消失");
            }
        });
    }


}


