/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upem.users.service.bank.client;

import com.upem.bank.service.BankService;
import com.upem.bank.service.BankService_Service;
import com.upem.bank.service.Compte;
import com.upem.users.service.bank.binding.Compte_;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
public class BankServiceClient {

    public static Compte_ getAccountByIBAN(String IBAN) {
        BankService_Service sv = new BankService_Service();
        BankService service = sv.getBankServicePort();
        return Compte_.fromCompte(service.getCompteBy(IBAN));
    }

    public static List<Compte_> getAllBankAccount() {
        BankService_Service sv = new BankService_Service();
        BankService service = sv.getBankServicePort();

        List<Compte_> rl = new ArrayList<>();
        for (Compte c : service.getAllCompte()) {
            rl.add(Compte_.fromCompte(c));
        }

        return rl;
    }

}
