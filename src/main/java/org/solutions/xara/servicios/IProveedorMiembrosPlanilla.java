package org.solutions.xara.servicios;


import org.solutions.xara.dto.DataPlanillaDTO;

import java.util.List;


/**
 * Interface que define el comportamiento de un proveedor de miembros de planilla
 *
 * @author Camilo Benavides
 */
public interface IProveedorMiembrosPlanilla {

    /**
     * Obtiene la lista de miembros de planilla de un proveedor
     *
     * @return Lista de miembros de planilla
     * @author Camilo Benavides
     */
    List<DataPlanillaDTO> getEmployeesList();
}
