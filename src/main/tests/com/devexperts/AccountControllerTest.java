package com.devexperts;

import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import com.devexperts.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AccountControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AccountService accountService;

    @Before
    public void setup() {
        accountService.clear();
        accountService.createAccount(new Account(AccountKey.valueOf(1),
                "Ivan", "Petrov", 1100.0));
        accountService.createAccount(new Account(AccountKey.valueOf(2),
                "Elena", "Lantrapova", 300.0));
    }

    private void assertPost(int status, String args) {
        ResponseEntity<String> result = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/operations/transfer?" + args,
                null, String.class);
        assertEquals(status, result.getStatusCodeValue());
    }

    @Test
    public void test200StatusCode() {
        assertPost(200, "source_id=1&target_id=2&amount=400.0");
    }

    @Test
    public void test400StatusCode() {
        assertPost(400, "source_id=2&amount=400.0");
    }

    @Test
    public void test404StatusCode() {
        assertPost(404, "source_id=3&target_id=1&amount=400.0");
    }

    @Test
    public void test500StatusCode() {
        assertPost(500, "source_id=1&target_id=2&amount=2000.0");
    }
}
