package com.lazyloding.ui.home;

import com.lazyloding.data.Repository;
import com.lazyloding.ui.base.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by Abhishekn on 28/06/2020.
 */

public class HomeViewModel extends BaseViewModel {

    private final Repository repository;

    @Inject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

}
