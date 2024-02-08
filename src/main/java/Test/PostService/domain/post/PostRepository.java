package Test.PostService.domain.post;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }
    public Post findById(Long id) {
        return store.get(id);
    }
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }
    public void update(long postId, Post updateParam) {
        Post findPost = findById(postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
    }
    public void clearStore() {
        store.clear();
    }
}
