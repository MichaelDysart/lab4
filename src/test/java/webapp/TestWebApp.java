package webapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestWebApp {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCheckSequence() throws Exception {
        this.mockMvc.perform(get("/create?bookName=ABC")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Create Success")));

        this.mockMvc.perform(post("/add?bookName=ABC&name=Joe&address=Tatooine&phoneNumber=555-5555")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Add Buddy Success")))
                .andExpect(content().string(containsString("Joe : Tatooine : 555-5555;")));

        this.mockMvc.perform(post("/add?bookName=ABC&name=Bob&address=Manaan&phoneNumber=555-5555")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Add Buddy Success")))
                .andExpect(content().string(containsString("Bob : Manaan : 555-5555;")));

        this.mockMvc.perform(post("/remove?bookName=ABC&name=Joe")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Remove Buddy Success")))
                .andExpect(content().string(containsString("Bob : Manaan : 555-5555;")))
                .andExpect(content().string(not(containsString("Joe : Tatooine : 555-5555;"))));

        this.mockMvc.perform(get("/view?bookName=ABC")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Bob : Manaan : 555-5555;")))
                .andExpect(content().string(not(containsString("Joe : Tatooine : 555-5555;"))));
    }
}
