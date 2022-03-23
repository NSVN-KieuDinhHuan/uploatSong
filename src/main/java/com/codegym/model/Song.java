package com.codegym.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String singer;

    private String type;

    private String songfile;

    public Song() {
    }

    public Song(String name, String singer, String type, String songfile) {
        this.name = name;
        this.singer = singer;
        this.type = type;
        this.songfile = songfile;
    }

    public Song(Long id, String name, String singer, String type, String songfile) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.type = type;
        this.songfile = songfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSongfile() {
        return songfile;
    }

    public void setSongfile(String songfile) {
        this.songfile = songfile;
    }
}
