package Test.PostService.domain.post;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Post {
    private long id;
    private String title;
    private String content;
    private String writer;
    private int commentCount;
    public ArrayList<String> commentContent = new ArrayList<>();
    public ArrayList<String> commentWriter = new ArrayList<>();
    public Post() {
    }
    public Post(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void setCommentContent(String commentContent) {
        this.commentContent.add(commentContent);
    }
    public void setCommentWriter(String commentWriter) {
        this.commentWriter.add(commentWriter);
    }
}
