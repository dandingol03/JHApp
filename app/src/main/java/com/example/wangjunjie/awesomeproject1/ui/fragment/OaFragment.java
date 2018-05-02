package com.example.wangjunjie.awesomeproject1.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangjunjie.awesomeproject1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private BaseAdapter mAdapter;
    private List<String> mList=new ArrayList<>();
    private static int refreshCount;
    private Handler mHandler=new Handler();


    private OnFragmentInteractionListener mListener;

    public OaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OaFragment newInstance(String param1, String param2) {
        OaFragment fragment = new OaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //generated
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oa, container, false);
    }

    @Override
    public void onViewCreated(View pView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(pView, savedInstanceState);
        mSwipeRefreshLayout=(SwipeRefreshLayout)pView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);


        final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView=(RecyclerView)pView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter=new BaseAdapter(mList,getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //do something here
            }
        });


        SwipeRefreshLayout.OnRefreshListener listener=new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try{
                    mList.clear();
                    refreshCount+=10;
                    for(int i=0;i<10;i++)
                    {
                        mList.add((i+refreshCount)+"");
                    }
                    mAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };

        mSwipeRefreshLayout.setOnRefreshListener(listener);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                listener.onRefresh();
            }
        });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private static class MyHolder extends  RecyclerView.ViewHolder{
        public TextView textView;
        //实现的方法
        public MyHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    private static class BaseAdapter extends  RecyclerView.Adapter<MyHolder>
    {

        private List mList;
        private Context mContext;

        public BaseAdapter(List mList, Context mContext) {
            this.mList = mList;
            this.mContext = mContext;
        }


        @Override
        public MyHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            String item =(String) mList.get(position);
            holder.textView.setText(item);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
