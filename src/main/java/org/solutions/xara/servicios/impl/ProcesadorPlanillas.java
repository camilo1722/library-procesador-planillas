package org.solutions.xara.servicios.impl;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.solutions.xara.dto.DataPlanillaDTO;
import org.solutions.xara.servicios.IProcesadorPlanillas;
import org.solutions.xara.servicios.IProveedorMiembrosPlanilla;


import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * Clase que implementa la interfaz IProcesadorPlanillas
 * encargada de procesar la planilla de empleados
 *
 * @author Camilo Benavides
 */
public class ProcesadorPlanillas implements IProcesadorPlanillas {

    /**
     * Atributo que representa el proveedor de miembros de la planilla
     *
     * @author Camilo Benavides
     */
    private IProveedorMiembrosPlanilla proveedorMiembrosPlanilla;

    /**
     * Atributo que representa el mapa de montos por empleado
     */
    @Getter
    private HashMap<DataPlanillaDTO, Double> montoEmpleadoMap = new HashMap<>();

    /**
     * Atributo que representa la lista de datos de la planilla
     *
     * @author Camilo Benavides
     */
    private List<DataPlanillaDTO> dataPlanillaDTOList;

    /**
     * Constructor de la clase ProcesadorPlanillas que recibe un proveedor de miembros de la planilla
     *
     * @param proveedorMiembrosPlanilla proveedor de miembros de la planilla
     * @author Camilo Benavides
     */
    public ProcesadorPlanillas(IProveedorMiembrosPlanilla proveedorMiembrosPlanilla) {
        this.proveedorMiembrosPlanilla = proveedorMiembrosPlanilla;
    }

    /**
     * Método que calcula el monto total de la planilla
     *
     * @return monto total de la planilla
     * @author Camilo Benavides
     */
    @Override
    public double calculateTotalAmount() {
        this.dataPlanillaDTOList = proveedorMiembrosPlanilla.getEmployeesList();
        this.validateSheet();
        this.validateSheetData();
        this.separateDataByEmployee();
        return this.calculateAmount();

    }

    /**
     * Método que valida que la lista de datos de la planilla no sea nula o vacía
     *
     * @throws IllegalArgumentException si la lista de datos de la planilla es nula o vacía
     * @author Camilo Benavides
     */
    private void separateDataByEmployee() {
        this.dataPlanillaDTOList.stream()
                .filter(DataPlanillaDTO::isActivo)
                .forEach(x -> this.montoEmpleadoMap.put(x,
                        this.montoEmpleadoMap.getOrDefault(x, 0.0) + x.getMontoMensual()));

    }

    /**
     * Método que valida que la lista de datos de la planilla no sea nula o vacía
     *
     * @throws IllegalArgumentException si la lista de datos de la planilla es nula o vacía
     * @author Camilo Benavides
     */
    private void validateSheet() {
        if (Objects.isNull(this.dataPlanillaDTOList) || this.dataPlanillaDTOList.isEmpty()) {
            throw new IllegalArgumentException("The spreadsheet list is empty");
        }
    }

    /**
     * Método que valida que los datos de la planilla sean válidos
     *
     * @throws IllegalArgumentException si alguno de los siguientes campos es nulo o vacío:
     *                                  ID, nombre o  si monto mensual es menor a cero
     * @author Camilo Benavides
     */
    private void validateSheetData() {
        List<String> errors = this.dataPlanillaDTOList.stream()
                .filter(x -> Objects.isNull(x.getID()) || StringUtils.isEmpty(x.getNombre()) || x.getMontoMensual() < 0)
                .limit(3)
                .map(DataPlanillaDTO::toString).toList();

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("The spreadsheet data is invalid: " + errors);
        }

    }

    /**
     * Método que calcula el monto total de la planilla
     *
     * @return monto total de la planilla
     * @author Camilo Benavides
     */
    private double calculateAmount() {
        return this.dataPlanillaDTOList.stream()
                .filter(DataPlanillaDTO::isActivo)
                .mapToDouble(DataPlanillaDTO::getMontoMensual)
                .sum();
    }


}
