package org.superbiz.moviefun.moviesapi.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }



    public void addAlbum(AlbumInfo albumInfo) {
        logger.info("AlbumInfo "+albumInfo.getId()+" "+albumInfo.getTitle());

        logger.info("albums url "+albumsUrl);
        ResponseEntity response=restOperations.postForEntity(albumsUrl, albumInfo, AlbumInfo.class);
        logger.info("ResponseEntity "+response.toString());
    }

    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumsUrl + "/" + id,AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {

        return restOperations.exchange(albumsUrl, GET, null, albumListType).getBody();
    }

    public void deleteAlbum(AlbumInfo album) {
        restOperations.delete(albumsUrl + "/" + album.getId());
    }

    public void updateAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl,album,AlbumInfo.class);
    }
}
