/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author ASCENT
 */
@Data
@Entity
@Table(name = "inspection_schedules", schema = "inspections")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InspectionSchedulesEntity {

    @Id
    int id;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "inspection_date")
    Date inspectionSched;

    @Column(name = "inspector_id")
    int inspectorId;

    @Column(name = "reference_number")
    String referenceNo;

    @Column(name = "remarks")
    String remarks;

    @Column(name = "status")
    String status;

    @Column(name = "updated_at")
    Timestamp updatedAt;

    @Column(name = "application_item_id")
    int applicationItemId;
    
    @Column(name = "document_id")
    int documentId  ;

    @Column(name = "application_item_type")
    String applicationItemType;

}
