package com.example.restapi.services;

import com.example.restapi.dto.AlbumDto;

public interface AlbumService {

        void deleteByAlbumId(Long id);
        void updateAlbum(AlbumDto album);
        AlbumDto getAlbumById(Long id);
        AlbumDto saveAlbum(AlbumDto album);
}
