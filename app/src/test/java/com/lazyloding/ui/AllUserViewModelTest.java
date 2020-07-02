package com.lazyloding.ui;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.lazyloding.data.Repository;
import com.lazyloding.ui.fragments.allusers.AllUserViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static java.sql.DriverManager.println;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class AllUserViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private Repository repository;
    private AllUserViewModel viewModel;

    @Before
    public void setup() {
        println("--- --- --- ---");
        repository = mock(Repository.class);
        viewModel = new AllUserViewModel(repository);
    }

    @After
    public void teardown() {
        println("--- --- --- ---");
    }

    @Test
    public void testNull() {
        assertThat(viewModel.getLazyLodingMutableLiveData(), notNullValue());
        verify(repository, never()).lazyLodingApi();
    }

    @Test
    public void testAPI() {
        viewModel.getLazyLodingMutableLiveData().observeForever(it -> {
                assertNotNull(it);
                assertThat(it.getRows().isEmpty(), is(false));
                assertTrue(it.getRows().size() > 0);
                println("GetRows returned:"+it.getRows().size());
        });
    }
}
