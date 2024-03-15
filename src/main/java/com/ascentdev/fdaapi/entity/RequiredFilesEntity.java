/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author ASCENT
 */
@Entity
@Data
@Table(name = "required_files", schema = "inspections")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RequiredFilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "filepath")
    String filePath;

    @Column(name = "filename")
    String fileName;

    @Column(name = "file_type")
    String fileType;
    
    @Column(name = "signatory_type")
    String signatoryType;

    @Column(name = "remarks")
    String remarks;

    @Column(name = "document_id")
    int documentId;

    @Column(name = "inspection_schedule_id")
    int inspectionSchedId;

    @Column(name = "created_at")
    Timestamp createdAt;

}
