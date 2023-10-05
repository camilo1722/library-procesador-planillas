package org.solutions.xara.servicios;

/**
 * Interface que representa el contrato para el procesamiento de planillas
 * de empleados
 *
 * @author Camilo Benavides
 */
public interface IProcesadorPlanillas {


    /**
     * Metodo que calcula el total de la planilla de empleados
     *
     * @return double con el total de la planilla
     * @author Camilo Benavides
     */
    double calculateTotalAmount();
}
