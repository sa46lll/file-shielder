package com.flow.sa46lll.fileshield.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blocked_extension")
public class BlockedExtentionEntity {

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

    public BlockedExtentionEntity(final Long id,
                                  final String extension,
                                  final boolean isFixed,
                                  final boolean isBlocked) {
        this.id = id;
        this.extension = extension;
        this.isFixed = isFixed;
        this.isBlocked = isBlocked;
    }

    protected BlockedExtentionEntity() {
    }
}
