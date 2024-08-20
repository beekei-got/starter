package com.starter.core.blog;

import com.starter.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Comment("블로그 회원 연결")
@Getter
@Entity
@Table(name = "blog_connect_user", uniqueConstraints = {
    @UniqueConstraint(name = "UK_blog_connect_user_id", columnNames = { "blog_id", "user_id" })
})
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogConnectUser extends BaseEntity {

    @EmbeddedId
    private BlogConnectUserId id;

    @MapsId("blogId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false, updatable = false)
    private Blog blog;

    public static BlogConnectUser create(Blog blog, long userId) {
        return BlogConnectUser.builder().id(new BlogConnectUserId(blog.getId(), userId)).blog(blog).build();
    }

}
