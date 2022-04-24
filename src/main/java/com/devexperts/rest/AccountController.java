package com.devexperts.rest;

import com.devexperts.exceptions.AccountNotFoundException;
import com.devexperts.exceptions.InsufficientBalanceException;
import com.devexperts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class AccountController extends AbstractAccountController {
    private final Logger logger = Logger.getLogger(AccountController.class.getName());

    @Autowired
    AccountService accountService;

    /**
     * Transfers given amount of money from source account to target account
     *
     * @param sourceId account id to transfer money from
     * @param targetId account id to transfer money to
     * @param amount   dollar amount to transfer
     * @return ResponseEntity with status code
     */
    @PostMapping("operations/transfer")
    public ResponseEntity<Void> transfer(@RequestParam("source_id") long sourceId,
                                         @RequestParam("target_id") long targetId,
                                         @RequestParam double amount) {
        logger.info("Trying to transfer " + amount + "$ from source acc " + sourceId + " to target acc " + targetId);
        try {
            accountService.transfer(accountService.getAccount(sourceId),
                    accountService.getAccount(targetId),
                    amount);
            return ResponseEntity.ok().body(null);
        } catch (IllegalArgumentException e) {
            logger.warning(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (AccountNotFoundException e) {
            logger.warning(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (InsufficientBalanceException e) {
            logger.warning(e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
