package org.iesvdm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of ="id")
@Builder
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @Column(name = "fecha_pedido")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date fechaPedido = new Date();

    @Column(name = "estado")
    private Estado estado;
    @Column(columnDefinition = "set('Puerta','Rapida','ContraRembolso')")
    private String caracteristicas;

    public Set<String> getCaracteristicas(){
        if(caracteristicas == null){
            return Collections.emptySet();
        } else{
            return Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(caracteristicas.split(","))));
        }
    }
    public void setCaracteristicas( Set<String> caractSet){
        if(caractSet == null){
            caracteristicas = null;
        } else{
            caracteristicas = String.join(",", caractSet);
        }
    }
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name = "FK_Cliente"))
    @JsonIgnore
    @ToString.Exclude
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "pedido_producto",
            joinColumns = @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    )
    private Set<Producto> productos;

}
