package com.starter.core.domain.blog;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BlogConnectCategoryId implements Serializable {

    @Comment("블로그 고유번호")
    @Column(name = "blog_id", nullable = false, updatable = false)
    private Long blogId;

    @Comment("카테고리 고유번호")
    @Column(name = "blog_category_id", nullable = false, updatable = false)
    private Long categoryId;

}
