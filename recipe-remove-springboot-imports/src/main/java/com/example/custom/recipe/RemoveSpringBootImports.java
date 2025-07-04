package com.example.custom.recipe;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

public class RemoveSpringBootImports extends Recipe {

    @Override
    public String getDisplayName() {
        return "Remove Spring Boot imports";
    }

    @Override
    public String getDescription() {
        return "Removes all import statements starting with org.springframework.boot";
    }

    @Override
    public Set<String> getTags() {
        return Collections.singleton("migration");
    }

    @Override
    public Duration getEstimatedEffortPerOccurrence() {
        return Duration.ofMinutes(1);
    }

    @Override
    public JavaIsoVisitor<ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<ExecutionContext>() {
            @Override
            public J.Import visitImport(J.Import _import, ExecutionContext ctx) {
                String imp = _import.getQualid().printTrimmed();
                if (imp.startsWith("org.springframework.boot")) {
                    // Remove the import
                    return null;
                }
                return super.visitImport(_import, ctx);
            }
        };
    }
}
