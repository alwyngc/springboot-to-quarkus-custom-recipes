================================================================================
                    SPRING BOOT TO QUARKUS MIGRATION RECIPE
================================================================================

This project provides OpenRewrite recipes to automate the migration from Spring 
Boot applications to Quarkus framework.

OVERVIEW
========

The migration recipe helps automate several common tasks when migrating from 
Spring Boot to Quarkus:

    * Removes Spring Boot specific imports
    * Removes Spring Boot parent dependency from pom.xml
    * Removes Spring Boot starter dependencies
    * Removes Spring Boot Maven plugin
    * Deletes Spring Boot application main class files

PREREQUISITES
=============

    * Java 17 or higher
    * Maven 3.6+
    * OpenRewrite CLI or Maven plugin

PROJECT STRUCTURE
==================

springboot-to-quarkus-custom-recipe/
├── pom.xml
├── README.txt
└── src/
    └── main/
        ├── java/
        │   └── com/example/custom/recipe/
        │       ├── RemoveSpringBootImports.java
        │       └── RemoveSpringBootParent.java
        └── resources/
            └── META-INF/rewrite/
                └── rewrite.yml
