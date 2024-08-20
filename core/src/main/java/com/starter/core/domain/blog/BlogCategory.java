package com.starter.core.blog;

import com.starter.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Comment("블로그 카테고리")
@Getter
@Entity
@Table(name = "blog_category", uniqueConstraints = {
    @UniqueConstraint(name = "UK_blog_category_name", columnNames = { "category_name" })
})
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    @Column(name = "id")
    private Long id;

    @Comment("카테고리명")
    @Column(name = "category_name", length = 50, nullable = false)
    private String name;

    public static BlogCategory createCategory(String name) {
        return BlogCategory.builder().name(name).build();
    }

}
