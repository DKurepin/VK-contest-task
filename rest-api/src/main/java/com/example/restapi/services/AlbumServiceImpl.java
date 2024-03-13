package com.example.restapi.services;

import com.example.restapi.dto.AlbumDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService{

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final CacheManager cacheManager;
    @Autowired
    private final RestTemplate restTemplate;

    @Cacheable("albums")
    @Override
    public AlbumDto getAlbumById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/albums/" + id, AlbumDto.class);
    }
    @Cacheable("albums")
    @Override
    public AlbumDto saveAlbum(AlbumDto album) {
        AlbumDto response = restTemplate.postForObject(BASE_URL + "/albums", album, AlbumDto.class);
        return response;
    }
    @Cacheable("albums")
    @Override
    public void deleteByAlbumId(Long id) {
        restTemplate.delete(BASE_URL + "/albums/" + id);
    }

    @Cacheable("albums")
    @Override
    public void updateAlbum(AlbumDto album) {
        restTemplate.put(BASE_URL + "/albums/" + album.getId(), album);
    }

    @CacheEvict(value = {"albums"}, allEntries = true)
    public void clearCache() {
        cacheManager.getCache("albums").clear();
    }
}
