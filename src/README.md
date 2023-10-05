# Ejercicio Programado - Procesador de Planillas

Este proyecto implementa un Procesador de Planillas que calcula el monto total a pagar para una planilla de empleados. La clase `ProcesadorPlanillas` depende de un `ProveedorMiembrosPlanilla` externo para obtener la lista de empleados. El procesador suma el pago mensual de cada empleado activo y devuelve el total como resultado.

## Estructura del Empleado

Cada empleado tiene los siguientes atributos:
- ID (integer)
- Nombre (string)
- MontoMensual (float)
- Activo (bool)

## Dependencia Externa - ProveedorMiembrosPlanilla

El `ProveedorMiembrosPlanilla` es abstracto en este proyecto, lo que significa que su implementación concreta no forma parte de este ejercicio. Esto permite tener diferentes implementaciones del proveedor de planilla sin necesidad de modificar el código del `ProcesadorPlanillas`.

## Pruebas Unitarias

Se han incluido pruebas unitarias que cubren varios escenarios, incluyendo los siguientes casos de excepción:
- Monto mensual negativo
- ID igual a 0
- Nombre vacío

## Repositorio en GitHub

Puede encontrar la solución completa en el repositorio de GitHub en el siguiente enlace: [Repositorio GitHub del proyecto](https://github.com/camilo1722/library-procesador-planillas)

## Compilacion e instalación

# Proyecto Spring Boot - Pasos de Instalación

Este proyecto utiliza Spring Boot, un framework de desarrollo de aplicaciones Java. Aquí se detallan los pasos para configurar y ejecutar la aplicación en tu entorno local.

# Requisitos Previos

Asegúrate de tener instalados los siguientes requisitos previos en tu sistema:

1. **Java**: Debes tener Java instalado en tu máquina.

2. **Maven**: Se utiliza Maven como sistema de gestión de proyectos. Puedes descargarlo desde [Apache Maven](https://maven.apache.org/download.cgi).

3. **Git (opcional)**: Si deseas clonar el repositorio del proyecto desde GitHub, asegúrate de tener Git instalado.

# Pasos de Instalación

Sigue estos pasos para configurar y ejecutar el proyecto en tu entorno local:

1. **Clonar el Repositorio (opcional)**:

   Si tienes Git instalado, puedes clonar el repositorio del proyecto en tu máquina local utilizando el siguiente comando:

   ```shell
   git clone https://github.com/camilo1722/library-procesador-planillas

2. **Compilar el proyecto:**

   Navega al directorio raíz del proyecto y ejecuta el siguiente comando para compilar el proyecto utilizando Maven:
  ```shell
   mvn clean install
   ```

4. **Ejecutar la Aplicación:**
    Una vez que la compilación haya finalizado con éxito, puedes ejecutar la aplicación con el siguiente comando:
  ```shell
  java -jar target/tu-proyecto-1.0.0.jar
  ```

4. **Acceder a la Aplicación:**
  La aplicación estará disponible en http://localhost:8081. Abre tu navegador web y navega a esta dirección para interactuar con la aplicación.

5. **Servicio expuestos**
  Pudes acceder a el catalogo de servicio con el siguiente enlace http://localhost:8081/swagger-ui/index.html




## Cómo Cambiar el Proveedor de Planilla

Para cambiar la implementación del `ProveedorMiembrosPlanilla` sin modificar el código del `ProcesadorPlanillas`, siga estos pasos:

1. Cree una nueva clase que implemente la interfaz `IProveedorMiembrosPlantilla`.

```java
public interface IProveedorMiembrosPlanilla {

    /**
     * Obtiene la lista de miembros de planilla de un proveedor
     *
     * @return Lista de miembros de planilla
     * @author Camilo Benavides
     */
    List<DataPlanillaDTO> getEmployeesList();
```



2. Debido a que la clase ProcesadorPlanillas tiene implementada y hace uso de esta interfaz, se realizara la implementacion y uso del metodo creado en su implementacion.


```java 
    public ProcesadorPlanillas(IProveedorMiembrosPlanilla proveedorMiembrosPlanilla) {
        this.proveedorMiembrosPlanilla = proveedorMiembrosPlanilla;
    }
    ....
 @Override
    public double calculateTotalAmount() {
        this.dataPlanillaDTOList = proveedorMiembrosPlanilla.getEmployeesList();
      ....

    
```
