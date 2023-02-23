package org.iesvdm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
@Entity
public class Producto {
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Long cantidad;

    private Double precio;

    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Pedido> pedidos;
}
