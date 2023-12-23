package com.flow.sa46lll.fileshield.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "blocked_extension")
@EntityListeners(AuditingEntityListener.class)
public class BlockedExtensionEntity extends AuditEntity {

    @Id
    @Column(name = "blocked_extension_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extension")
    private String extension;

    @Column(name = "is_fixed")
    private boolean isFixed;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    public BlockedExtensionEntity(final String extension,
                                  final boolean isFixed,
                                  final boolean isBlocked) {
        this.extension = extension;
        this.isFixed = isFixed;
        this.isBlocked = isBlocked;
    }

    protected BlockedExtensionEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
