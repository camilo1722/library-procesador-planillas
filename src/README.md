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

2. **Realizar el empaquetado del proyecto:**

   Navega al directorio raíz del proyecto y ejecuta el siguiente comando para empaquetar el proyecto utilizando Maven:
  ```shell
   mvn clean package
   ```

3. **Instalacion de la liberia:**
    Una vez que el empaquetado haya finalizado con éxito, y se valide la creación de el archivo `planillas-1.0-SNAPSHOT.jar`. se debe realizar la intlación de esta libreria en la maquina, nodo o servidor en donde se requiera:
  ```shell
  mvn install
  ```

4. **Acceder a la Aplicación:**
  Una vez creado la instalación se puede importar la libreria tal y como se suele hacer con cualquier otra libreria maven

4. **Ejecución Test unitarios (Opcional)**
   En la raiz del proyecto se debe ejecutar el siguiente comando para validar la correcta ejecución de los test

 ```shell
        mvn test
  ```

![image](https://github.com/camilo1722/library-procesador-planillas/assets/131456103/5c2a60bd-ee2f-4150-9d8b-917f3e0afbb0)


 
   




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
