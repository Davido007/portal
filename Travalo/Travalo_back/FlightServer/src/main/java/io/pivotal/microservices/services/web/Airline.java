package io.pivotal.microservices.services.web;

import javax.persistence.*;

/**
 * Created by DPLICHTA on 6/13/2017.
 */

@Entity
@Table(name = "airline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "iata")
    private String iata;

    @Column(name = "name")
    private String name;




    public Airline() {
        super();
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

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

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", iata='" + iata + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}