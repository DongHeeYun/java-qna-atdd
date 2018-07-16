package codesquad.web;

import codesquad.HtmlFormDataBuilder;
import codesquad.domain.QuestionRepository;
import codesquad.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionAcceptanceTest extends AcceptanceTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    HtmlFormDataBuilder htmlFormDataBuilder;

    @Before
    public void setUp() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm();
    }

    @Test
    public void create() {
        String title = "test title";

        htmlFormDataBuilder.addParameter("title", title);
        htmlFormDataBuilder.addParameter("contents", "test contents");

        ResponseEntity<String> response = basicAuthTemplate().postForEntity("/questions", htmlFormDataBuilder.build(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(questionRepository.findByTitle(title).isPresent()).isTrue();
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/");
    }
}
