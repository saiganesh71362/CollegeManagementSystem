package com.acruent.college.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "STUDENT_BRANCH")
public class StudentBranch
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRANCH_ID")
    private Integer id;
    
    @Column(name = "BRANCH_NAME", nullable = false)
    private String branchName;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "COLLEGE_ID", nullable = false)
    private CollegeNames college;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE", insertable = false)
    private LocalDateTime updatedDate;
}
