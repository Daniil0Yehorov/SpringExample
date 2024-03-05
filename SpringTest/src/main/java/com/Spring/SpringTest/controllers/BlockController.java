package com.Spring.SpringTest.controllers;

//import ch.qos.logback.core.model.Model; not worked addAttribute dont know why xD
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
        Iterable <Post> posts=postRepository.findAll();
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
        postRepository.save(post);//saving object
        return "redirect:/blog";//redirect to another URL address
    }
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value="id") long id, Model model){
        if(postRepository.existsById(id)){
         Optional<Post> post =postRepository.findById(id);//create an object and write the found post there
         ArrayList<Post> res =new ArrayList<>();
         post.ifPresent(res::add);//throw the object into an array to display it in html correctly
         model.addAttribute("post",res);
            return "blog-details";
        }
        else {return "blog-main";}
    }
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
        Post post =postRepository.findById(id).orElseThrow();//orelsethrow exeption
        //If the new attribute data is empty, then the values do not change to avoid problems with misclicks, etc.
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
