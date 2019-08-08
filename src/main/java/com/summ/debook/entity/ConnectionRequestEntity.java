package com.summ.debook.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@DiscriminatorValue("CONNECTION")
public class ConnectionRequestEntity extends RequestEntity {

    @OneToMany(mappedBy = "request")
    protected List<DebtRequestDataEntity> debtRequestDataList;

    public List<DebtRequestDataEntity> getDebtRequestDataList() {
        return debtRequestDataList;
    }

    public void setDebtRequestDataList(List<DebtRequestDataEntity> debtRequestDataList) {
        this.debtRequestDataList = debtRequestDataList;
    }
}
