package Nanda.pdm.nandakun.ui.fragment;

import Nanda.pdm.nandakun.ui.fragment.common.MovieListableFragment;
import Nanda.pdm.nandakun.ui.presenter.SoonMoviesListPresenter;
import Nanda.pdm.nandakun.ui.presenter.base.IPresenter;

/**
 * Soon movies fragment
 */
public class SoonMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new SoonMoviesListPresenter(this);
    }
}
