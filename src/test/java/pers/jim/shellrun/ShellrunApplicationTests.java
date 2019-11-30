package pers.jim.shellrun;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShellrunApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
		ArgumentModel argumentModel = new ArgumentModel("windows","dir");
		webClient.post()
				.uri("/run")
				.body(Mono.just(argumentModel),ArgumentModel.class)
				.header("Content-Type","application/json")
				.exchange()
				.expectBody()
				.consumeWith(response -> {
					byte[] body = response.getResponseBody();
					System.out.println(new String(body));
				});

	}

}
