# FabricacionMecanica
Programa para gestionar fabricaciones mecánicas de la empresa creado mediante Spring Boot, java, Oracle.

Tendremos diferentes pestañas, en las que encontraremos las solicitudes creadas, los talleres con los que trabajaremos, los usuarios que utilizarán el programa, dos gráficas donde se representarán los gastos en cada taller y otra con los gastos por proyecto, traductor, registro de nuevos usuarios y la persona que está en la sesión pudiendo cerrarla.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Cabecera.PNG)


Comenzamos explicando la pestaña de los talleres, donde podemos crear, editar o eliminar los talleres con los que trabajaremos.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Talleres.PNG)

Formulario para crear un taller.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Crear%20taller.png)

Al crear un taller te redigirá a la página principal avisando que el taller se ha creado con éxito.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Taller%20creado.PNG)

Formulario para editar un taller.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Editar%20taller.PNG)

En la pestaña de usuarios, la cual sólo tendrán acceso los administradores del programa, se podrán gestionar los usuarios que podrán usar el gestor, pudiendo eliminar un usuario, bloquearlo, otorgar permisos de administrador, o quitar permisos. El usuario por defecto que se creará automáticamente nada más iniciar el programa será el de administraor (usuario: administrador, contraseña: administrador), con el que se podrán crear nuevos usuarios, una vez generados con rol administrador se podrá eliminar este usuario por seguridad.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Usuarios.PNG)

Como vemos en la imagen de abajo, el usuario Fran, cuyo rol es de usuario no tiene permisos en la pestaña de gestión de usuarios.

![alt text](https://user-images.githubusercontent.com/76131095/153752760-31f29da6-87a4-4edb-870d-9499ed395a49.png)

En la pestaña con el dibujo de gráfica de barras obtendremos un resumen de gastos visual mediante barras, podremos ver los gastos por talleres y los gastos por proyecto.

Gráfica de talleres

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Grafica%20taller.PNG)

Gráfica por proyecto

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Grafica%20proyecto.PNG)

En la página principal tendremos las solicitudes generadas en una dataTable,aquí podremos crear las solicitudes que queramos en el botón crear solicitud. También se podrán eliminar todas las solicitudes, editar o eliminar cada una de ellas.

![alt text](https://user-images.githubusercontent.com/76131095/154316307-37a8938c-a06d-45e9-a9fb-c62604e84755.png)

Formulario para crear solicitudes.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/NuevaSol.PNG)

Formulario para editar solicitudes.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Editar.PNG)

Se deberá crear 1º las carpetas donde vamos a alojar la documentación referente a esa solicitud. Una vez creada saldrá un listado con las carpetas generadas en el desplegable y al seleccionar una de ellas todos los archivos que adjuntemos se encontrarán en dicha carpeta, habrá que seleccionar el taller al que habremos contratado para la fabricación, las fechas son obligatorias, se debe poner fecha de pedido y un plazo de entrega para que en la página principal calcule los días que el taller se retrase, saltando un aviso si el taller a sobrepasado el plazo. Importante, para que se cargue la solicitud, ésta deberá de tener el mismo nombre en pdf alojado dentro de la carpeta, es decir, si la carpeta se llama SOLICITUD_1, deberá contener el pdf dentro con el nombre SOLICITUD_1.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Obligatorio.PNG)

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Plazos.PNG)

Una vez aceptado el aviso no volverá a salir más. Si no, saldría el aviso cada vez que iniciaramos sesión con cada solicitud, por lo que es simplemente para que el programa nos avise del plazo de cada solicitud que haya sido sobrepasado y reclamar al taller.

En la página principal nos encontramos con un filtro donde podremos realizar búsquedas de los documentos que necesitemos, pudiendo filtrar por solicitudes, proyectos, estado de la fabricación (En curso, la columna estará en color blanco; recibida, la columna aparacerá en verde; pendiente de validación, la columna aparecerá en amarillo; Sin especificar), o por taller. 

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Filtro.PNG)


Se pueden filtrar las columnas de la datatable según los datos que necesitemos saber, y generar un pdf, excel o copia de las columnas filtradas. Al final de la datatable tendremos un resumen del coste de cada proyecto, si filtramos por taller o por proyecto se actualizarán los precios.

Filtrado por proyecto y lo que lleva facturado.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Filtrado%20proyecto.PNG)

Filtrado por columnas.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Filtrar%20columnas.PNG)

Aquí se han filtrado columnas según los datos que necesito.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Columnas%20filtradas.PNG)

Si pulsamos sobre el botón de pdf generará un pdf con los datos que aparezcan en la tabla, en ése caso no he filtrado ninguna columna, por lo que aparecen todos los datos.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Pdf.PNG)

También se puede generar un excel.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Excel.PNG)

El botón de copiar, copiará los  datos en portapapeles para utilizarlos donde convenga.

En la tabla general, como se puede observar en las imágenes, los archivos que se han adjuntado en los formularios aparecen en azul, al hacer click sobre ellos abrirá los archivos correspondientes sin descargarlos, esta opción ha sido creada así para que cada vez que se quiera consultar un archivo no se llene el ordenador de descargas. Una vez abiertos en el navegador se podrán descargar y guardarlos donde se desee. Si se elimina de la carpeta el archivo se mantendrá en la tabla el nombre, pero al intentar abrirlo dará un error avisando que no existe el archivo.

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Error.PNG)

En éste caso he clicado sobre SOLICITUD_1, abriendo la correspondiente solicitud (como ejemplo un pdf en blanco).

![alt text](https://github.com/edube85/FabricacionMecanica/blob/master/Capturas/Descarga.PNG)
