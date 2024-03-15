/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author ASCENT
 */
@Data
@Entity
@Subselect("SELECT ubp.id, u.id AS user_id, ubp.name AS company_name FROM commons.user_business_profiles ubp\n"
        + "INNER JOIN commons.users u ON u.business_profile_id = ubp.id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserIdByCompanyEntity {

    @Id
    int id;

    @Column(name = "user_id")
    int userId;

    @Column(name = "company_name")
    String companyName;

}
