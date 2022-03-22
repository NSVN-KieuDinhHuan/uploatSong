package com.codegym.service;

import com.codegym.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService implements ISongService {
    private List<Song> songs = new ArrayList<>();

    @Override
    public List<Song> findAll() {
        return songs;
    }

    public int findProductById(int id) {
        int index = -1;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Song findById(int id) {
        int index = findProductById(id);
        if (index != -1) {
            return songs.get(index);
        }
        return null;
    }

    @Override
    public void create(Song song) {
        songs.add(song);
    }

    @Override
    public void updateById(int id, Song song) {
        int index = findProductById(id);
        songs.set(index, song);
    }

    @Override
    public void removeById(int id) {
        int index = findProductById(id);
        songs.remove(index);
    }
}
