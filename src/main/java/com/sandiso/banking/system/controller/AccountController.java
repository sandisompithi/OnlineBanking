package com.sandiso.banking.system.controller;

import com.sandiso.banking.system.model.*;
import com.sandiso.banking.system.service.AccountService;
import com.sandiso.banking.system.service.TransactionService;
import com.sandiso.banking.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/fixedAccount")
    public String fixedAccount(Model model, Principal principal){
        List<FixedTransaction> fixedTransactionList = transactionService
                .findFixedTransactionList(principal.getName());

        User user = userService.findByEmail(principal.getName());
        FixedAccount fixedAccount = user.getFixedAccount();

        model.addAttribute("fixedAccount", fixedAccount);
        model.addAttribute("fixedTransactionList", fixedTransactionList);

        return "fixedAccount";
    }

    @RequestMapping("/savingsAccount")
    public String savingsAccount(Model model, Principal principal){
        List<SavingsTransaction> savingsTransactionList = transactionService
                .findSavingsTransactionList(principal.getName());

        User user = userService.findByEmail(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("savingsAccount", savingsAccount);
        model.addAttribute("savingsTransactionList", savingsTransactionList);

        return "savingsAccount";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model){
        model.addAttribute("accountType","");
        model.addAttribute("amount","");
        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String makeDeposit(@ModelAttribute("amount") String amount,
                              @ModelAttribute("accountType") String accountType, Principal principal){
        accountService.deposit(accountType, Double.parseDouble(amount), principal);
        return "redirect:/user";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "withdraw";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdraw(@ModelAttribute("amount") String amount,
                           @ModelAttribute("accountType") String accountType, Principal principal){
        accountService.withdraw(accountType, Double.parseDouble(amount), principal);
        return "redirect:/user";
    }
}
