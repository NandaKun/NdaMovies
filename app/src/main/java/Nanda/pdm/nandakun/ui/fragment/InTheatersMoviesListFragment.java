package Nanda.pdm.nandakun.ui.fragment;

import Nanda.pdm.nandakun.ui.fragment.common.MovieListableFragment;
import Nanda.pdm.nandakun.ui.presenter.InTheatersMoviesListPresenter;
import Nanda.pdm.nandakun.ui.presenter.base.IPresenter;

/**
 * Fragment representing the movies list in theaters
 */
public class InTheatersMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new InTheatersMoviesListPresenter(this);
    }
}
