package com.codegym.service;

import com.codegym.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();

    Song findById(int id);

    void create(Song song);

    void updateById(int id, Song song);

    void removeById(int id);
}
