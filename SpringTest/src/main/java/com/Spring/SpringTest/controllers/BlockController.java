package com.Spring.SpringTest.controllers;

//import ch.qos.logback.core.model.Model; не работает addAttribute з почему
import org.springframework.ui.Model;
import com.Spring.SpringTest.Repos.PostRepository;
import com.Spring.SpringTest.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlockController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/blog")
    //гет при переходе на адрес
    public String blogMain(Model model){
        Iterable <Post> posts=postRepository.findAll();//масив данных из полученной таблички с бд
        model.addAttribute("posts",posts);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model){

        return "blog-add";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,
                              @RequestParam String full_text,Model model) {
        Post post=new Post(title,anons,full_text);
        postRepository.save(post);//сохранение обьекта
        return "redirect:/blog";//переадресовка на друго юрл адресс
    }
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value="id") long id, Model model){
        if(postRepository.existsById(id)){
         Optional<Post> post =postRepository.findById(id);//создаем обьект и записуем туда найденный пост
         ArrayList<Post> res =new ArrayList<>();
         post.ifPresent(res::add);//закидываем обьект в масив, чтобы вывести его в html правильно
         model.addAttribute("post",res);
            return "blog-details";
        }
        else {return "blog-main";}
    }
    /*
    //удаление это работает тоже
    @GetMapping("/blog/{id}/delete")
    public String blogdelete(@PathVariable(value = "id")long id,Model model){
        Optional<Post> postToDel=postRepository.findById(id);
        postToDel.ifPresent(post->postRepository.delete(post));
        return "redirect:/blog";
    }*/
    @PostMapping("/blog/{id}/delete")
    public String blogdelete(@PathVariable(value = "id")long id,Model model){
        Post postToDel=postRepository.findById(id).orElseThrow();
        postRepository.delete(postToDel);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogedit(@PathVariable(value = "id")long id,Model model){
        if(!postRepository.existsById(id)){return "redirect:/blog";}
        Optional<Post> post =postRepository.findById(id);
        ArrayList<Post> res =new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogUpdate(@PathVariable(value = "id")long id,@RequestParam String title,@RequestParam String anons,
                           @RequestParam String full_text,Model model){
        Post post =postRepository.findById(id).orElseThrow();//orelsethrow исключение, елси нету поста
        //Если новые данные атрибутов пустые, то значения не меняются во избежания с проблемами мискликов и т.д
        if (title != null && !title.isEmpty()) {
            post.setTitle(title);
        }
        if (anons != null && !anons.isEmpty()) {
            post.setAnons(anons);
        }
        if (full_text != null && !full_text.isEmpty()) {
            post.setFull_text(full_text);
        }
        postRepository.save(post);
        return "redirect:/blog";
    }
}
