package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import com.codegym.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {
    @Autowired
    private ISongService songService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/list")
    public ModelAndView showListSong() {
        ModelAndView modelAndView = new ModelAndView("/song/list");
        List<Song> songList = songService.findAll();
        modelAndView.addObject("songList", songList);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/song/delete");
        Song song = songService.findById(id);
        modelAndView.addObject("song", song);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        Song song=songService.findById(id);
        File file = new File(uploadPath + song.getName());
        if (file.exists()){
            file.delete();
        }
        songService.removeById(id);
        return new ModelAndView("redirect:/song/list");
    }

    @GetMapping("/create")
    public ModelAndView showCreateSong() {
        ModelAndView modelAndView = new ModelAndView("/song/create");
        modelAndView.addObject("songForm", new SongForm());//Gửi 1 đối tượng song rỗng sang file view để tạo mới
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute SongForm songForm) {
        String fileName = songForm.getSongfile().getOriginalFilename();
        long currentTime = System.currentTimeMillis(); //Xử lý lấy thời gian hiện tại
        fileName = currentTime + fileName;
        try {
            FileCopyUtils.copy(songForm.getSongfile().getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Song song = new Song(songForm.getId(), songForm.getName(),songForm.getSinger(), songForm.getType(), fileName);
        songService.create(song);
        return new ModelAndView("redirect:/song/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/song/edit");
        Song song = songService.findById(id);
        modelAndView.addObject("song", song);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id, @ModelAttribute SongForm songForm) {
        MultipartFile songfile=songForm.getSongfile();
        Song oldSong = songService.findById(id);
        if (songfile.getSize() != 0) {
            String fileName = songForm.getSongfile().getOriginalFilename();
            long currentTime = System.currentTimeMillis(); //Xử lý lấy thời gian hiện tại
            fileName = currentTime + fileName;
            oldSong.setSongfile(fileName);
            try {
                FileCopyUtils.copy(songfile.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        oldSong.setName(songForm.getName());
        oldSong.setSinger(songForm.getSinger());
        oldSong.setType(songForm.getType());
        songService.create(oldSong);
        return new ModelAndView("redirect:/song/list");
    }

    @GetMapping("/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id) {
        Song song = songService.findById(id);
        return new ModelAndView("/song/view", "song", song);
    }
}
