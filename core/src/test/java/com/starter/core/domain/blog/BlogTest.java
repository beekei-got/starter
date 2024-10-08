package com.starter.core.domain.blog;

import com.starter.core.domain.blog.Blog;
import com.starter.core.domain.blog.BlogStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlogTest {

    @Test
    void 블로그_생성() {
        String name = "내 맘대로 블로그";
        Blog blog = Blog.createBlog(name);

        assertThat(blog.getName()).isEqualTo(name);
        assertThat(blog.getStatus()).isEqualTo(BlogStatus.OPEN);
    }

}