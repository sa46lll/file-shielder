package com.flow.sa46lll.fileshield.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "extension_type")
    private ExtensionTypeEntity extensionType;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    public BlockedExtensionEntity(final String extension,
                                  final ExtensionTypeEntity extensionType,
                                  final boolean isBlocked) {
        this.extension = extension;
        this.extensionType = extensionType;
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

    public ExtensionTypeEntity getExtensionType() {
        return extensionType;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void block() {
        isBlocked = true;
    }

    public void unblock() {
        isBlocked = false;
    }
}
