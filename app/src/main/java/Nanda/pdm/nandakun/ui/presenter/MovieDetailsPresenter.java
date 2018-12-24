package Nanda.pdm.nandakun.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import Nanda.pdm.nandakun.data.exception.FailedGettingDataException;
import Nanda.pdm.nandakun.data.repository.base.ICloudMovieRepository;
import Nanda.pdm.nandakun.data.repository.base.ILocalMovieRepository;
import Nanda.pdm.nandakun.data.repository.base.MovieRepositoryFactory;
import Nanda.pdm.nandakun.model.MovieDetails;
import Nanda.pdm.nandakun.ui.contract.ILoadDataView;
import Nanda.pdm.nandakun.ui.presenter.base.Presenter;

/**
 * Movie view details presenter
 */
public class MovieDetailsPresenter extends Presenter<MovieDetails> {

    private int id;

    public MovieDetailsPresenter(final ILoadDataView<MovieDetails> view) {
        super(view);
    }


    public void setMovieId(int id) {
        this.id = id;
    }


    public void storeFollow(boolean follow) {
        new StoreDataTask().execute(follow);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute(id);
    }

    /**
     * Load movie details in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Integer, Void, MovieDetails> {

        @Override
        protected MovieDetails doInBackground(Integer... params) {
            Log.d(TAG, "doInBackground: Getting movie from the api");
            ICloudMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository();
            ILocalMovieRepository localRepo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            try {
                MovieDetails movie = cloudRepo.getMovieById(params[0]);
                movie.setBeingFollowed(localRepo.isBeingFollowed(movie.getId()));
                return movie;
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(MovieDetails movie) {
            super.onPostExecute(movie);

            if(movie != null)
                view.setData(movie);
            else
                view.showNoConnection();
        }
    }

    /**
     * Store movie follow state in a worker thread using an AsyncTask
     */
    private class StoreDataTask extends AsyncTask<Boolean, Void, Void> {

        @Override
        protected Void doInBackground(Boolean... params) {
            Log.d(TAG, "doInBackground: Storing movie follow state");
            ILocalMovieRepository repo = MovieRepositoryFactory.getLocalRepository(view.getViewContext());

            //if not following, begin to follow
            if(params[0]){
                repo.followMovie(id);
            } else {
                repo.unfollowMovie(id);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
