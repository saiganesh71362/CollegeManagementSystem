package com.acruent.college.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Integer id;
    
    @Column(name = "STUDENT_NAME", nullable = false)
    private String name;
    
    @Column(name = "STUDENT_CONTACT", nullable = false)
    private Long contact;
    
    @Column(name = "STUDENT_ADDRESS", nullable = false)
    private String address;
    
    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private StudentBranch branch;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE", insertable = false)
    private LocalDateTime updatedDate;

}
