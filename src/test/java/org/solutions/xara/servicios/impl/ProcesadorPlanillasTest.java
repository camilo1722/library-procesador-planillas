package org.solutions.xara.servicios.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.solutions.xara.dto.DataPlanillaDTO;
import org.solutions.xara.servicios.IProveedorMiembrosPlanilla;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author camil
 */
class ProcesadorPlanillasTest {

    @Mock
    private IProveedorMiembrosPlanilla proveedorMiembrosPlanilla;


    @Mock
    private ProcesadorPlanillas procesadorPlanillas;


    @BeforeEach
    public void setUp() {
        proveedorMiembrosPlanilla = Mockito.mock(IProveedorMiembrosPlanilla.class);
        procesadorPlanillas = new ProcesadorPlanillas(proveedorMiembrosPlanilla);
    }


    @Test
    public void testCalculateTotalAmountWithEmptyList() {
        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(new ArrayList<>());
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> procesadorPlanillas.calculateTotalAmount());
        String expectedErrorMessage = "The spreadsheet list is empty";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void testCalculateTotalAmountWithNullList() {
        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.calculateTotalAmount();
        });

        assertEquals("The spreadsheet list is empty", exception.getMessage());
    }


    @Test
    public void testCalculatetWithIdNull() {

        List<DataPlanillaDTO> invalidSpreadsheetDataList = new ArrayList<>();

        invalidSpreadsheetDataList.add(new DataPlanillaDTO(1, "Employee1", 1000, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(null, "Employee2", 100, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(null, "Employee3", 170, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(4, "Employee4", 1000, true));

        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(invalidSpreadsheetDataList);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.calculateTotalAmount();
        });
        String expectedErrorMessage = "The spreadsheet data is invalid: [Id: null - name: Employee2 - amount: 100.0., Id: null - name: Employee3 - amount: 170.0.]";

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void testCalculatetWithNegativeAmount() {
        List<DataPlanillaDTO> invalidSpreadsheetDataList = new ArrayList<>();

        invalidSpreadsheetDataList.add(new DataPlanillaDTO(1, "Employee1", 1000, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(4, "Employee4", -1000, true));

        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(invalidSpreadsheetDataList);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.calculateTotalAmount();
        });
        String expectedErrorMessage = "The spreadsheet data is invalid: [Id: 4 - name: Employee4 - amount: -1000.0.]";

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void testCalculatetWithInvalidNames() {
        List<DataPlanillaDTO> invalidSpreadsheetDataList = new ArrayList<>();

        invalidSpreadsheetDataList.add(new DataPlanillaDTO(1, "Employee1", 1000, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(3, null, 150, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(4, "", 800, true));

        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(invalidSpreadsheetDataList);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.calculateTotalAmount();
        });
        String expectedErrorMessage = "The spreadsheet data is invalid: [Id: 3 - name: null - amount: 150.0., Id: 4 - name:  - amount: 800.0.]";

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void testCalculatetWithMultiplesTypesOfErrors() {
        List<DataPlanillaDTO> invalidSpreadsheetDataList = new ArrayList<>();

        invalidSpreadsheetDataList.add(new DataPlanillaDTO(1, "Employee1", 177, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(2, "Employee2", 1000, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(null, "EmployeeNull", 1000, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(3, "Employee4", -800, true));

        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(invalidSpreadsheetDataList);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.calculateTotalAmount();
        });
        String expectedErrorMessage = "The spreadsheet data is invalid: [Id: null - name: EmployeeNull - amount: 1000.0., Id: 3 - name: Employee4 - amount: -800.0.]";

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void testCalculatetWithUsersInactives() {
        List<DataPlanillaDTO> invalidSpreadsheetDataList = new ArrayList<>();

        invalidSpreadsheetDataList.add(new DataPlanillaDTO(1, "Employee1", 177, false));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(2, "Employee2", 1000, false));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(3, "Employee3", 1000, false));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(4, "Employee4", 800, false));

        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(invalidSpreadsheetDataList);

        Double totalAmount = procesadorPlanillas.calculateTotalAmount();

        assertEquals(0.0, totalAmount);
    }

    @Test
    public void testCalculatetValidData() {
        List<DataPlanillaDTO> invalidSpreadsheetDataList = new ArrayList<>();

        invalidSpreadsheetDataList.add(new DataPlanillaDTO(1, "Employee1", 177, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(2, "Employee2", 1000, false));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(3, "Employee3", 1600, true));
        invalidSpreadsheetDataList.add(new DataPlanillaDTO(4, "Employee4", 6000, true));

        when(proveedorMiembrosPlanilla.getEmployeesList()).thenReturn(invalidSpreadsheetDataList);

        Double totalAmount = procesadorPlanillas.calculateTotalAmount();

        assertEquals(7777.0, totalAmount);
    }


}