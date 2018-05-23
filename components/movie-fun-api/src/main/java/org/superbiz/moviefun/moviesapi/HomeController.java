package org.superbiz.moviefun.moviesapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.moviesapi.albums.AlbumFixtures;
import org.superbiz.moviefun.moviesapi.albums.AlbumInfo;
import org.superbiz.moviefun.moviesapi.albums.AlbumsClient;
import org.superbiz.moviefun.moviesapi.movies.MovieFixtures;
import org.superbiz.moviefun.moviesapi.movies.MovieInfo;
import org.superbiz.moviefun.moviesapi.movies.MoviesClient;

import java.util.Map;

@Controller
public class HomeController {

    private final MoviesClient moviesRepository;
    private final AlbumsClient albumsClient;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

    public HomeController(MoviesClient moviesRepository, AlbumsClient albumsClient, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesRepository = moviesRepository;
        this.albumsClient = albumsClient;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesRepository.addMovie(movie);
        }

        for (AlbumInfo album : albumFixtures.load()) {
            albumsClient.addAlbum(album);
        }

        model.put("movies", moviesRepository.getMovies());
        model.put("albums", albumsClient.getAlbums());

        return "setup";
    }
}
