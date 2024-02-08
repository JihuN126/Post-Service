package Test.PostService.domain.post;
import lombok.Data;
@Data
public class Post {
    private long id;
    private String title;
    private String content;
    private String writer;
    public Post() {
    }
    public Post(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
