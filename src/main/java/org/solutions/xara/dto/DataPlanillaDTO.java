package org.solutions.xara.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * Clase que representa la información retornada por el cliente de la planilla.
 *
 * @author Camilo Benavides
 */
@Data
@AllArgsConstructor
public class DataPlanillaDTO {

    private Integer ID;
    private String nombre;
    private double montoMensual;
    private boolean activo;

    /**
     * Método que retorna la información de la clase en un formato legible.
     *
     * @return String con la información de la clase.
     * @author Camilo Benavides
     */
    @Override
    public String toString() {
        return String.format("Id: %s - name: %s - amount: %s.", ID, nombre, montoMensual);
    }

    /**
     * Método que compara dos objetos de la clase.
     *
     * @param obj Objeto a comparar.
     * @return boolean con el resultado de la comparación.
     * @author Camilo Benavides
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DataPlanillaDTO)) return false;
        DataPlanillaDTO that = (DataPlanillaDTO) obj;
        return getID().equals(that.getID()) && getNombre().equals(that.getNombre());
    }

    /**
     * Método que retorna el hashcode de la clase.
     *
     * @return int con el hashcode de la clase.
     * @author Camilo Benavides
     */
    @Override
    public int hashCode() {
        return Objects.hash(getID(), getNombre());
    }
}
