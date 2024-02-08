package Test.PostService.web.basic;

import Test.PostService.domain.post.Post;
import Test.PostService.domain.post.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/posts")
@RequiredArgsConstructor
public class BasicPostController {
    private final PostRepository postRepository;
    @GetMapping
    public String posts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "basic/posts";
    }
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }
    @PostMapping("/add")
    public String save(@RequestParam String postTitle,
                       @RequestParam String postContent,
                       @RequestParam String postWriter,
                       Model model) {
        Post post = new Post(postTitle,postContent,postWriter);
        postRepository.save(post);
        model.addAttribute("post",post);
        return "redirect:/basic/posts";
    }
    @PostConstruct
    public void init() {
        postRepository.save(new Post("안녕하세요", "asasasasasasas", "yjh"));
        postRepository.save(new Post("안녕하세요222", "asasasasasasa232323s", "yjhdsd"));
    }
}
