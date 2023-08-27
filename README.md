# Proyecto Administrador de Tiendita


## Instituto Tecnológico de Oaxaca
## Tópicos Avanzados de Programación

### Pasos para instalación en netbeans


1. Clonar el repositorio con SSH
```
git clone git@github.com:carlosupreme/tiendita-java-full.git
```
o en su lugar con HTTPS
```
git clone https://github.com/carlosupreme/tiendita-java-full.git
```
2. Moverse al directorio
```
cd tiendita-java-full
```

3. Copiar archivo **.env-example** ubicado en *src/main/resources/* a **.env**
```
cp src/main/resources/.env-example src/main/resources/.env
```
4. Poner credenciales de la base de datos en el archivo .env
```
nano src/main/resources/.env
```
5. Ejecutar el script **tablas.sql**, primero entrar a la consola de mysql
```
mysql -u root -p
```
Dentro de la consola ejecutar:  
```
source tablas.sql;
```
6. Abrir el proyecto en netbeans y hacer build para descargar dependencias

