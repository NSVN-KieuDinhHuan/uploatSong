package com.codegym.service;

import com.codegym.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();

    Song findById(Long id);

    Song create(Song song);

    void removeById(Long id);
}
