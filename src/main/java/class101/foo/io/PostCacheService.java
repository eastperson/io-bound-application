package class101.foo.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PostCacheService {

    @Autowired
    PostRepository postRepository;

    private Page<Post> firstPostPage;

    // 1초에 1번 페이지를 갱신하고 있는 것이다.
    @Scheduled(cron = "* * * * * *")
    public void updateFirstPostPage() {
        firstPostPage = postRepository.findAll(
                PageRequest.of(0,20, Sort.by("id").descending())
        );
    }

    public Page<Post> getFirstPostPage(){
        return this.firstPostPage;
    }
}
