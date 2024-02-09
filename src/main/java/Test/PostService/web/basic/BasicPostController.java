package Test.PostService.web.basic;
import Test.PostService.domain.post.Post;
import Test.PostService.domain.post.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/basic/posts")
@RequiredArgsConstructor
@Slf4j
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
    public String addPost(@RequestParam String postTitle,
                       @RequestParam String postContent,
                       @RequestParam String postWriter,
                       Model model) {
        Post post = new Post(postTitle,postContent,postWriter);
        postRepository.save(post);
        model.addAttribute("post",post);
        return "redirect:/basic/posts";
    }
    @GetMapping("/{postId}")
    public String post(@PathVariable(name="postId") long postId, Model model) {
        Post post = postRepository.findById(postId);
        System.out.println("BasicPostController.post");
        model.addAttribute("post",post);
        return "basic/post";
    }
    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable(name="postId") long postId, Model model) {
        System.out.println("BasicPostController.editForm");
        Post post = postRepository.findById(postId);
        model.addAttribute("post",post);
        return "basic/editForm";
    }
    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable(name="postId") long postId,
                       @RequestParam String postTitle,
                       @RequestParam String postContent,
                       Model model) {
        Post post = new Post(postTitle, postContent);
        System.out.println("BasicPostController.edit");
        postRepository.update(postId,post);
        model.addAttribute("post",post);
        return "redirect:/basic/posts/{postId}";
    }
    @GetMapping("/{postId}/commentslist")
    public String commentList(@PathVariable(name="postId") long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post",post);
        System.out.println("BasicPostController.commentList");
        return "basic/commentList";
    }
    @GetMapping("/{postId}/comments")
    public String commentForm(@PathVariable(name="postId") long postId, Model model) {
        Post post = postRepository.findById(postId);
        System.out.println("BasicPostController.commentForm");
        model.addAttribute("post",post);
        return "basic/commentForm";
    }
    @PostMapping("/{postId}/comments")
    public String commentWrite(@PathVariable(name="postId") long postId,
                          @RequestParam String commentContent,
                          @RequestParam String commentWriter,
                          Model model) {
        System.out.println("BasicPostController.comment");
        Post post = postRepository.findById(postId);
        post.setCommentContent(commentContent);
        post.setCommentWriter(commentWriter);
        model.addAttribute("post",post);
        System.out.println(post.commentContent.size());
        System.out.println(post.commentWriter.size());
//        for(int i=0;i<post.commentContent.size();i++) {
//            System.out.println(post.commentContent.get(i));
//        }
        return "redirect:/basic/posts/{postId}/commentslist";
    }
    @PostConstruct
    public void init() {
        postRepository.save(new Post("Title1", "Content1", "Writer1"));
        postRepository.save(new Post("Title2", "Content2", "Writer2"));
    }
}
