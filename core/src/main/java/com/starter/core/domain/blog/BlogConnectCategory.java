package com.starter.core.domain.blog;

import com.starter.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Comment("블로그 회원 연결")
@Getter
@Entity
@Table(name = "blog_connect_category", uniqueConstraints = {
    @UniqueConstraint(name = "UK_blog_connect_category_id", columnNames = { "blog_id", "blog_category_id" })
})
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogConnectCategory extends BaseEntity {

    @EmbeddedId
    private BlogConnectCategoryId id;

    @MapsId("blogId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false, updatable = false)
    private Blog blog;

    public static BlogConnectCategory create(Blog blog, long blogCategoryId) {
        return BlogConnectCategory.builder().id(new BlogConnectCategoryId(blog.getId(), blogCategoryId)).blog(blog).build();
    }

}
