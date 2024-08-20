package com.starter.core.domain.blog;

import com.starter.core.domain.blog.BlogCategory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlogCategoryTest {

    @Test
    void 카테고리_생성() {
        String name = "카테고리1";
        BlogCategory blogCategory = BlogCategory.createCategory(name);

        assertThat(blogCategory.getName()).endsWith(name);
    }

}