package com.renecode.primeraweb.Complete.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Toda clase que se quiera mapear como una tabla de una base de datos tiene que tener el decorador @Entity.
@Table(name = "tbl_personas")
// Anotaciones de lombok.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para que el id se vaya autoincrementado.
    private Long id;

    private String nombre;
    private int edad;



}
