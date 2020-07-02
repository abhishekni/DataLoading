package com.lazyloding;

import com.lazyloding.data.Repository;
import com.lazyloding.ui.fragments.allusers.AllUserViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static java.sql.DriverManager.println;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    private Repository mockRepository;

    private AllUserViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        println("--- --- --- ---");
        viewModel = new AllUserViewModel(mockRepository);
    }

    @After
    public void teardown() {
        println("--- --- --- ---");
    }
}