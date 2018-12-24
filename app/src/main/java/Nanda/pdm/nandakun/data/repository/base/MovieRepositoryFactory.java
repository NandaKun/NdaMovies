package Nanda.pdm.nandakun.data.repository.base;


import android.content.Context;

import Nanda.pdm.nandakun.data.api.TMDbApiSync;
import Nanda.pdm.nandakun.data.mapper.CursorModelEntitiesDataMapper;
import Nanda.pdm.nandakun.data.mapper.DTOModelEntitiesDataMapper;
import Nanda.pdm.nandakun.data.repository.LocalMovieRepository;
import Nanda.pdm.nandakun.data.repository.TMDbMovieRepository;

/**
 * This class knows how to construct each type of the repositories
 */
public class MovieRepositoryFactory {

    /**
     * Create and return an instance of a TMDbMovieRepository
     * @return
     */
    public static ICloudMovieRepository getCloudRepository() {
        return new TMDbMovieRepository(new TMDbApiSync(), new DTOModelEntitiesDataMapper());
    }

    /**
     * Create and return an instance of a LocalMovieRepository
     * @param ctx
     * @return
     */
    public static ILocalMovieRepository getLocalRepository(Context ctx) {
        return new LocalMovieRepository(ctx, new CursorModelEntitiesDataMapper());
    }

}
