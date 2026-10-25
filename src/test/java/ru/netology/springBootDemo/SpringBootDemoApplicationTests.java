package ru.netology.springBootDemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class SpringBootDemoApplicationTests {
    static GenericContainer<?> devContainer =
            new GenericContainer<>(DockerImageName.parse("devapp:latest"))
                    .withExposedPorts(8080);

    static GenericContainer<?> prodContainer =
            new GenericContainer<>(DockerImageName.parse("prodapp:latest"))
                    .withExposedPorts(8081);
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        devContainer.start();
        prodContainer.start();
    }

    @Test
    void devProfileTest() {
        String url = "http://localhost:"
                + devContainer.getMappedPort(8080)
                + "/profile";

        ResponseEntity<String> response =
                restTemplate.getForEntity(url, String.class);

        assertEquals(
                "Current profile is dev",
                response.getBody()
        );
    }

    @Test
    void prodProfileTest() {
        String url = "http://localhost:"
                + prodContainer.getMappedPort(8081)
                + "/profile";

        ResponseEntity<String> response =
                restTemplate.getForEntity(url, String.class);

        assertEquals(
                "Current profile is production",
                response.getBody()
        );
    }
}
