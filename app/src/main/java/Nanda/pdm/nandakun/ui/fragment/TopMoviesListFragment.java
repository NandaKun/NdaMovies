package Nanda.pdm.nandakun.ui.fragment;

import Nanda.pdm.nandakun.ui.fragment.common.MovieListableFragment;
import Nanda.pdm.nandakun.ui.presenter.TopMoviesListPresenter;
import Nanda.pdm.nandakun.ui.presenter.base.IPresenter;

/**
 * Top movies fragment
 */
public class TopMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new TopMoviesListPresenter(this);
    }
}
