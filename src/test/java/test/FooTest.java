package test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.io.File;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import dasniko.testcontainers.keycloak.KeycloakContainer;

@Testcontainers
public class FooTest {

  static final List<File> dependencies = Maven.resolver()
                                         .loadPomFromFile("./pom.xml")
                                         .resolve("org.keycloak:keycloak-admin-client")
                                         .withoutTransitivity().asList(File.class);
  
  @Container static final KeycloakContainer server = new KeycloakContainer("quay.io/keycloak/keycloak:20.0.1").withContextPath("/auth/").withProviderClassesFrom("target/classes").withProviderLibsFrom(dependencies);

  Keycloak getKeycloak() {
    assertTrue(server.isRunning());
    return server.getKeycloakAdminClient();
  }

  @Test
  public void testIt() throws Exception {
  }

}
