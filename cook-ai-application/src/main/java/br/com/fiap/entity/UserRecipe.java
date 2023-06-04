package br.com.fiap.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;

@Entity
@Table(name = "TAB_USER_RECIPE")
@SequenceGenerator(name="userRecipe", sequenceName = "SQ_TB_USER_RECIPE", allocationSize = 1)
public class UserRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRecipe")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar creationDate;
}