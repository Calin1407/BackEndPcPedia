package com.BackEndPcPedia.shared.infrastructure.persistence.jpa.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/**
 * SnakeCasePhysicalNamingStrategy
 *
 * This class implements the PhysicalNamingStrategy interface to convert database
 * identifiers to snake_case format. It also pluralizes table names.
 */
public class SnakeCasePhysicalNamingStrategy implements PhysicalNamingStrategy {
    /**
     * Converts a given Identifier to snake_case format for catalog names.
     * @param identifier the original Identifier
     * @param jdbcEnvironment the JDBC environment
     * @return the Identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts a given Identifier to snake_case format for schema names.
     * @param identifier the original Identifier
     * @param jdbcEnvironment the JDBC environment
     * @return the Identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts a given Identifier to snake_case format for table names and pluralizes it.
     * @param identifier the original Identifier
     * @param jdbcEnvironment the JDBC environment
     * @return the pluralized Identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(this.toPlural(identifier));
    }

    /**
     * Converts a given Identifier to snake_case format for sequence names.
     * @param identifier the original Identifier
     * @param jdbcEnvironment the JDBC environment
     * @return the Identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts a given Identifier to snake_case format for column names.
     * @param identifier the original Identifier
     * @param jdbcEnvironment the JDBC environment
     * @return the Identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts a given Identifier to snake_case format.
     * @param identifier the original Identifier
     * @return the Identifier in snake_case format
     */
    private Identifier toSnakeCase(final Identifier identifier) {
        if (identifier == null)   return null;

        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
        return Identifier.toIdentifier(newName);
    }

    /**
     * Converts a given Identifier to its plural form.
     * @param identifier the original Identifier
     * @return the pluralized Identifier
     */
    private Identifier toPlural(final Identifier identifier) {
        final String newName = pluralize(identifier.getText());
        return Identifier.toIdentifier(newName);
    }
}
