# Proyecto Administrador de Tiendita


## Instituto Tecnológico de Oaxaca
## Tópicos Avanzados de Programación
[mapa mental](https://www.goconqr.com/en/mindmap/38670150/librerias)

### Pasos para instalación en netbeans


1. Clonar el repositorio con SSH
```
git clone git@github.com:raul-itoax/administrador-tiendita.git
```
o en su lugar con HTTPS
```
git clone https://github.com/raul-itoax/administrador-tiendita.git
```
1. Moverse al directorio
```
cd administrador-tiendita
```

1. Copiar archivo **.env-example** ubicado en *src/main/resources/* a **.env**
```
cp src/main/resources/env-example src/main/resources/.env
```
1. Poner credenciales de la base de datos en el archivo .env
```
nano src/main/resources/.env
```
1. Ejecutar el script **tablas.sql**, primero entrar a la consola de mysql
```
mysql -u root -p
```
Dentro de la consola ejecutar:  
```
source tablas.sql;
```
1. Abrir el proyecto en netbeans y hacer build para descargar dependencias

