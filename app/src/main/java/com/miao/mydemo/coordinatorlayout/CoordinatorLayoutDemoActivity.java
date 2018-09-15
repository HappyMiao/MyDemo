package com.miao.mydemo.coordinatorlayout;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miao.mydemo.R;

import java.util.ArrayList;

/**
 * @description: CoordinatorLayout 一个购物车动画Demo
 * @author: miao
 * @time: 2018年09月15日
 */
public class CoordinatorLayoutDemoActivity extends AppCompatActivity {

    //列表数据源
    private ArrayList<String> mData;
    private RecyclerViewAdapter rva;
    private TabLayout tabLayout;
    //购物车父布局
    private RelativeLayout mShoppingCartRly;
    //购物车图标
    private ImageView mShoppingCartIv;
    //当前消费总额
    private TextView tvMoney;
    //去支付
    private TextView goPay;
    private PathMeasure mPathMeasure;
    //贝塞尔曲线过程点坐标
    private float[] mCurrentPosition = new float[2];
    //模拟添加商品后消费总额
    private int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_demo);
        initView();
        initData();
    }

    private void initView() {
        goPay = findViewById(R.id.go_pay);
        tvMoney = findViewById(R.id.tv_money);
        mShoppingCartIv = findViewById(R.id.iv_shopping_cart);
        mShoppingCartRly = findViewById(R.id.mShoppingCartRly);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rva = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(rva);
    }

    private void initData() {
        mData = new ArrayList<>();
        for(int i = 0;i < 30;i++){
            mData.add("火锅配菜"+i+"号");
        }
        rva.notifyDataSetChanged();
        tabLayout.addTab(tabLayout.newTab().setText("重庆火锅"));
        tabLayout.addTab(tabLayout.newTab().setText("麻辣香锅"));
        tabLayout.addTab(tabLayout.newTab().setText("麻辣干锅"));
        tabLayout.addTab(tabLayout.newTab().setText("炉火串串"));
        tabLayout.addTab(tabLayout.newTab().setText("四川炒菜"));
        tabLayout.addTab(tabLayout.newTab().setText("四川面食"));
    }

    /**
     * @description: RecyclerViewAdapter
     * @author: miao
     * @time: 2018年09月15日
     */
    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.list_item.setText(mData.get(position));
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CoordinatorLayoutDemoActivity.this
                            ,mData.get(position),Toast.LENGTH_SHORT).show();
                    AddAnimation(holder.add);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView list_item;
            private ImageView add;
            ViewHolder(View itemView) {
                super(itemView);
                list_item = itemView.findViewById(R.id.name);
                add = itemView.findViewById(R.id.add);
            }
        }
    }

    private void AddAnimation(ImageView goodsImg) {
        //商品图片,添加到购物车的父布局。
        final ImageView goods = new ImageView(this);
        goods.setImageDrawable(getDrawable(R.mipmap.add));
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(70, 70);
        mShoppingCartRly.addView(goods, params);

        //父布局的起始点坐标用作控制点
        int[] parentLocation = new int[2];
        mShoppingCartRly.getLocationInWindow(parentLocation);

        //得到商品图片的坐标用作起点
        int startLoc[] = new int[2];
        goodsImg.getLocationInWindow(startLoc);

        //得到购物车图片的坐标用作终点
        int endLoc[] = new int[2];
        mShoppingCartIv.getLocationInWindow(endLoc);

        //开始掉落的商品的起始点
        float startX = startLoc[0] - parentLocation[0] + goodsImg.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + goodsImg.getHeight() / 2;

        //商品掉落后的终点坐标
        float toX = endLoc[0] - parentLocation[0] + mShoppingCartIv.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二阶贝塞尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，如果是true，path会形成一个闭环
        mPathMeasure = new PathMeasure(path, false);

        //属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(500);

        //匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //当插值计算进行时，获取中间的每个值，
                //这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                //获取当前点坐标封装到mCurrentPosition
                //boolean getPosTan(float distance, float[] pos, float[] tan) ：
                //传入一个距离distance(0<=distance<=getLength())，然后会计算当前距离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                //mCurrentPosition此时就是中间距离点的坐标值
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                //移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });

        //开始执行动画
        valueAnimator.start();

        //动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //把执行动画的商品图片从父布局中移除
                mShoppingCartRly.removeView(goods);
                price += 23;
                tvMoney.setText("共计"+price+"元");
                goPay.setBackgroundColor(Color.parseColor("#FF4500"));
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
