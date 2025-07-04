package com.example.custom.recipe;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.maven.MavenIsoVisitor;
import org.openrewrite.xml.tree.Xml;

public class RemoveSpringBootParent extends Recipe {

    @Override
    public String getDisplayName() {
        return "Remove Spring Boot Parent";
    }

    @Override
    public String getDescription() {
        return "Removes the Spring Boot starter parent from pom.xml";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new MavenIsoVisitor<ExecutionContext>() {
            @Override
            public Xml.Tag visitTag(Xml.Tag tag, ExecutionContext ctx) {
                Xml.Tag t = super.visitTag(tag, ctx);

                // Check if this is the parent tag
                if ("parent".equals(t.getName())) {
                    // Check if it contains Spring Boot starter parent
                    boolean isSpringBootParent = t.getChildren().stream()
                            .filter(child -> child instanceof Xml.Tag)
                            .map(child -> (Xml.Tag) child)
                            .anyMatch(childTag ->
                                    "groupId".equals(childTag.getName()) &&
                                            childTag.getValue().isPresent() &&
                                            "org.springframework.boot".equals(childTag.getValue().get().trim())
                            );

                    if (isSpringBootParent) {
                        // Remove the entire parent tag
                        return null;
                    }
                }

                return t;
            }
        };
    }
}
