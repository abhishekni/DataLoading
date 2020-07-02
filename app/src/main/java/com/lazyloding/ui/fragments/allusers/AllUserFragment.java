package com.lazyloding.ui.fragments.allusers;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lazyloding.R;
import com.lazyloding.databinding.FragmentAllUserBinding;
import com.lazyloding.ui.base.BaseFragment;

import com.lazyloding.utils.Constants;


public class AllUserFragment extends BaseFragment<AllUserViewModel, FragmentAllUserBinding> {
    private static final String ARG_PARAM1 = Constants.Utils.param1;
    private String mParam1;
    private LazyLoadingAdapter lazyLoadingAdapter;

    public AllUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public Class<AllUserViewModel> getViewModel() {
        return AllUserViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_all_user;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lazyLoadingAdapter = new LazyLoadingAdapter();
        dataBinding.assignmentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.assignmentRecycler.setHasFixedSize(true);
        dataBinding.assignmentRecycler.setAdapter(lazyLoadingAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initObserver();
        dataBinding.swiperefreshItems.setOnRefreshListener(() -> initObserver());

    }

    private void  initObserver(){
        viewModel.getLazyLodingApi();
        viewModel.getLazyLodingMutableLiveData().observe(this, lazyLoding -> {
            lazyLoadingAdapter.setData(lazyLoding.getRows());
            if(dataBinding.swiperefreshItems.isRefreshing()) {
                dataBinding.swiperefreshItems.setRefreshing(false);
            }
        });
    }
}
