# **Borrador de README.md** 

Borrar antes de enviar al TB2...

Y mejorarlo

att: Jorge

# Requisitos del proyecto

## Dependencias:

### Principales (previo al proyect)

- Lombok
- Spring Boot Dev Tools
- Spring Web
- Spring Data JPA
- My SQL Driver

### Secundarias (pom.xml del proyect)

        <dependency> 
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <dependency>
            <groupId>io.github.encryptorcode</groupId>
            <artifactId>pluralize</artifactId>
            <version>1.0.0</version>
        </dependency>

        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.8.13</version>
        </dependency>

## Otros:

- Tipo de proyecto: Maven
- Se uso Spring Boot: 3.5.7
- Packaging: Jar
- Configuration: Properties
- Java: 21
- MySQL Workbench: 8.0

# Pasos del proyecto

## 1. Strategy

### 1.1. SnakeCasePhysicalNamingStrategy

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    private Identifier toSnakeCase(Identifier identifier) {
        if (identifier == null) return null;

        final String regex = "([a-z])([A-Z])*";
        final String replacement = "$1_$2";
        final String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();

        return Identifier.toIdentifier(newName);
    }

    private Identifier toPlural(final Identifier identifier) {
        final String newName = pluralize(identifier.getText());

        return Identifier.toIdentifier(newName);
    }

En primer lugar, es un buen patron para la interaccion de nuestro codigo y nuestros datos SQL

Por un lado, los primeros metodos public se encargaran de convertir nuestros datos java (clases, atributos, etc.) a datos SQL (catalogo, schema, table, secuencia, columna), respectivamente.

Por otro lado, los metodos private se encargaran de convertir el camelCase de nuestros datos java a snake_case y convertir los nombres al plural, respectivamente.  

