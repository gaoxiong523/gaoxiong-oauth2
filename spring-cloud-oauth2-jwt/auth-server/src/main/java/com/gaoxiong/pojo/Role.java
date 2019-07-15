package com.gaoxiong.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "role")
@Data
@Entity
public class Role implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "role_name")
  private String roleName;

  
}