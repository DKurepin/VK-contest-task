package com.example.restapi.controllers;

import com.example.restapi.dto.AlbumDto;
import com.example.restapi.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/albums/{id}")
    @PreAuthorize("hasAuthority('read')")
    public AlbumDto getAlbumById(@PathVariable("id") Long id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping("/albums")
    @PreAuthorize("hasAuthority('update')")
    public AlbumDto saveAlbum(@RequestBody AlbumDto album) {
        return albumService.saveAlbum(album);
    }

    @PutMapping("/albums/{id}")
    @PreAuthorize("hasAuthority('update')")
    public void updateAlbum(@RequestBody AlbumDto album) {
        albumService.updateAlbum(album);
    }

    @DeleteMapping("/albums/{id}")
    @PreAuthorize("hasAuthority('update')")
    public void deleteAlbum(@PathVariable("id") Long id) {
        albumService.deleteByAlbumId(id);
    }


}
