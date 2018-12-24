package Nanda.pdm.nandakun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Nanda.pdm.nandakun.ui.fragment.common.MovieListableFragment;
import Nanda.pdm.nandakun.ui.presenter.SearchMovieViewPresenter;
import Nanda.pdm.nandakun.ui.presenter.base.IPresenter;

/**
 * Fragment representing the movies list after search
 */
public class SearchMoviesListFragment extends MovieListableFragment {

    private SearchMovieViewPresenter searchPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        this.searchPresenter = (SearchMovieViewPresenter) this.presenter;

        if(!getArguments().isEmpty()) {
            String query = getArguments().getString("query");
            this.searchPresenter.setQuery(query);
            this.searchPresenter.execute();
        }

        return viewContainer;
    }

    @Override
    protected IPresenter createPresenter() {
        return new SearchMovieViewPresenter(this);
    }
}
