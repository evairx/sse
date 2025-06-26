INSTRUCCIONES CODIGO:
Para descargar el código mediante GitHub, debe usar "git clone https://github.com/evairx/sse" en cualquier directorio de su pc usando la terminal de Windows.
Ahora, debe abrir MySQL Workbench y crear dos DATABASE para el perfil de Dev y Test, llamadas "edutech_dev" y "edutech_test" con "CREATE DATABASE EDUTECH_DEV" y "CREATE DATABASE EDUTECH_TEST".

Una vez realizado, si su conexión MySQL posee contraseña vaya al directorio donde está el código, "resources" y en todos los "application.properties", y, donde dice "spring.datasource.password=" agregue la contraseña de su conexión después del "=", sino, dejé el espacio vacío.

Es importante que, en el "application.properties", donde dice "spring.profiles.active=dev", después del "=", debe escribir con cuál perfil va a ejecutar el codigo, si con Dev o con Test, y lo mismo en el "DataLoader" en "@Profile("dev")" (por defecto, ambos se encuentran en el perfil dev).

Con todo eso realizado, ejecute el main del código para crear las tablas, columnas, y hacer la inserción de los datos falsos en la base de datos.

Una vez realizado esto, abra Postman o use la extensión Thunder Client en Visual Studio Code para usar las rutas.

Algunas rutas:
http://127.0.0.1:8080/api/v1/usuario
http://127.0.0.1:8080/api/v1/cursos
http://127.0.0.1:8080/api/v1/usuario/crear
http://127.0.0.1:8080/api/v1/cursos/crear
http://127.0.0.1:8080/api/v1/usuario/eliminar/{aquí la id del usuario que desea eliminar, sin los "{}"}
http://127.0.0.1:8080/api/v1/cursos/eliminar/{aquí también la id del curso sin los "{}"}

Swagger:
http://localhost:8080/doc/swagger-ui/index.html

Se encuentran más métodos disponibles y con más clases, pero esos son algunos básicos.

<center>
	<img src="https://media1.tenor.com/m/Ww2rTFH4VwIAAAAd/sad-hamster-sadhamster.gif"/>
	<h3>ta muito dificil</h3>
</center>
