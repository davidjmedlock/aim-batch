package com.aim.aimbatch.model;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A LoadErrorReason.
 */
@Entity
@Table(name = "load_error_reason")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoadErrorReason implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "error_reason_code")
    private String errorReasonCode;

    @Column(name = "error_reason_description")
    private String errorReasonDescription;

    @Column(name = "load_type")
    private String loadType;

    @Column(name = "error_level")
    private String errorLevel;

    @OneToMany(mappedBy = "loadErrorReason")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TmpProviderLoadError> tmpProviderLoadErrors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorReasonCode() {
        return errorReasonCode;
    }

    public LoadErrorReason errorReasonCode(String errorReasonCode) {
        this.errorReasonCode = errorReasonCode;
        return this;
    }

    public void setErrorReasonCode(String errorReasonCode) {
        this.errorReasonCode = errorReasonCode;
    }

    public String getErrorReasonDescription() {
        return errorReasonDescription;
    }

    public LoadErrorReason errorReasonDescription(String errorReasonDescription) {
        this.errorReasonDescription = errorReasonDescription;
        return this;
    }

    public void setErrorReasonDescription(String errorReasonDescription) {
        this.errorReasonDescription = errorReasonDescription;
    }

    public String getLoadType() {
        return loadType;
    }

    public LoadErrorReason loadType(String loadType) {
        this.loadType = loadType;
        return this;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getErrorLevel() {
        return errorLevel;
    }

    public LoadErrorReason errorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
        return this;
    }

    public void setErrorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
    }

    public Set<TmpProviderLoadError> getTmpProviderLoadErrors() {
        return tmpProviderLoadErrors;
    }

    public LoadErrorReason tmpProviderLoadErrors(Set<TmpProviderLoadError> tmpProviderLoadErrors) {
        this.tmpProviderLoadErrors = tmpProviderLoadErrors;
        return this;
    }

    public LoadErrorReason addTmpProviderLoadError(TmpProviderLoadError tmpProviderLoadError) {
        this.tmpProviderLoadErrors.add(tmpProviderLoadError);
        tmpProviderLoadError.setLoadErrorReason(this);
        return this;
    }

    public LoadErrorReason removeTmpProviderLoadError(TmpProviderLoadError tmpProviderLoadError) {
        this.tmpProviderLoadErrors.remove(tmpProviderLoadError);
        tmpProviderLoadError.setLoadErrorReason(null);
        return this;
    }

    public void setTmpProviderLoadErrors(Set<TmpProviderLoadError> tmpProviderLoadErrors) {
        this.tmpProviderLoadErrors = tmpProviderLoadErrors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoadErrorReason)) {
            return false;
        }
        return id != null && id.equals(((LoadErrorReason) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LoadErrorReason{" +
                "id=" + getId() +
                ", errorReasonCode='" + getErrorReasonCode() + "'" +
                ", errorReasonDescription='" + getErrorReasonDescription() + "'" +
                ", loadType='" + getLoadType() + "'" +
                ", errorLevel='" + getErrorLevel() + "'" +
                "}";
    }
}
