package com.starter.core.domain.blog;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Comment("블로그")
@Getter
@Entity
@Table(name = "blog")
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    @Column(name = "id")
    private Long id;

    @Comment("블로그명")
    @Column(name = "blog_name", length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Comment("블로그 상태")
    @Column(name = "blog_status", length = 50, nullable = false)
    private BlogStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BlogConnectUser> connectedUserList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BlogConnectCategory> connectCategoryList;

    public static Blog createBlog(String name) {
        return Blog.builder()
            .name(name)
            .status(BlogStatus.OPEN)
            .build();
    }

}
