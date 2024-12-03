package me.ustory.api.paper.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ustory.api.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ImageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    public static ImageEntity of(String url) {
        return new ImageEntity(url);
    }

    private ImageEntity(String url) {
        this.url = url;
    }

}
