package userService.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by DPLICHTA on 7/12/2017.
 */
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}