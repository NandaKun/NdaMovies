package Nanda.pdm.nandakun.data.repository;

import java.io.IOException;
import java.util.List;

import Nanda.pdm.nandakun.data.api.TMDbApiSync;
import Nanda.pdm.nandakun.data.api.entity.CreditsListingDTO;
import Nanda.pdm.nandakun.data.api.entity.MovieDTO;
import Nanda.pdm.nandakun.data.api.entity.MovieListingDTO;
import Nanda.pdm.nandakun.data.exception.FailedGettingDataException;
import Nanda.pdm.nandakun.data.mapper.DTOModelEntitiesDataMapper;
import Nanda.pdm.nandakun.data.repository.base.ICloudMovieRepository;
import Nanda.pdm.nandakun.model.Movie;
import Nanda.pdm.nandakun.model.MovieCredits;
import Nanda.pdm.nandakun.model.MovieDetails;

/**
 * Movies Repository, fetch list of movies and movies details
 * This class  uses an api and a mapper to retrieve the data and convert to a model entity
 * synchronously
 */
public class TMDbMovieRepository implements ICloudMovieRepository {

    protected final String TAG = "DEBUG_" + getClass().getSimpleName();

    private final TMDbApiSync api;
    private final DTOModelEntitiesDataMapper mapper;

    public TMDbMovieRepository(TMDbApiSync api,
                               DTOModelEntitiesDataMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    /**
     * Get movies list in theaters synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<Movie> getTheatersMovies(int page) throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getTheatersMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<Movie> getSoonMovies(int page) throws FailedGettingDataException {

        try {
            MovieListingDTO data = this.api.getSoonMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<Movie> getTopMovies(int page) throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getTopMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movies list for a search query synchronously
     * And convert to a model entity
     *
     * @param search search query
     * @param page   api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<Movie> getMovieSearch(String search, int page)
            throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getMoviesSearch(search, page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public MovieDetails getMovieById(int id) throws FailedGettingDataException {
        try {
            MovieDTO data = this.api.getMovie(id);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movie credits synchronously
     * And convert to a model entity
     *
     * @param id
     * @return
     * @throws FailedGettingDataException
     */
    @Override
    public MovieCredits getCreditsOfMovie(int id) throws FailedGettingDataException {
        try {
            CreditsListingDTO data = this.api.getMovieCredits(id);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }
}
