//package com.jerryyin.kotlindemo.fragment;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.greentech.ansibledt.materialdemo.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by JerryYin on 9/21/16.
// */
//public class PageFourFragment extends Fragment {
//
//
//    /**
//     * Constants
//     */
//    private View mContentView;
//    private Activity mSelf;
//
//
//    private RecyclerView mRecyclerView;
//
//
//    private RecyclerViewAdapter mRecyclerAdapter;
//    private LinearLayoutManager mLayoutManager;
//    private List<String> mDataList;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (null != mContentView) {
//            ViewGroup vg = (ViewGroup) mContentView.getParent();
//            if (null != vg) {
//                vg.removeView(mContentView);
//            }
//        } else {
//            mSelf = getActivity();
//            mContentView = inflater.inflate(R.layout.layout_fragment_four, null);
//            mSelf = getActivity();
//            initData();
//            setupViews();
//        }
//        return mContentView;
//
//    }
//
//    private void initData() {
//        mDataList = new ArrayList<>();
//        for (int i=0; i<30; i++){
//            mDataList.add(new String("RecyclerView item"+i));
//        }
//    }
//
//    private void setupViews() {
//        mRecyclerView = (RecyclerView) mContentView.findViewById(R.id.recycler_view);
//        mLayoutManager = new LinearLayoutManager(mSelf);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerAdapter = new RecyclerViewAdapter(mSelf, mDataList);
//        mRecyclerView.setAdapter(mRecyclerAdapter);
//
//    }
//
//
//
//
//    /**
//     * RecyclerView 适配器
//     */
//    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
//
//        private Context mContext;
//        private List<String> mDataList;
//
//        public RecyclerViewAdapter(Context mContext, List<String> list) {
//            this.mContext = mContext;
//            this.mDataList = list;
//        }
//
//        @Override
//        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
//            CustomViewHolder holder = new CustomViewHolder(view);
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(CustomViewHolder viewHolder, int position) {
//            String data = mDataList.get(position);
//            viewHolder.text.setText(String.valueOf(data));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDataList.size();
//        }
//
//        //CustomViewHolder，用于缓存，提高效率
//        public class CustomViewHolder extends RecyclerView.ViewHolder {
//            private TextView text;
//
//            public CustomViewHolder(View itemView) {
//                super(itemView);
//                text = (TextView) itemView.findViewById(R.id.text_view);
//            }
//        }
//    }
//}
