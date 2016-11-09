/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upem.users.service.bank.binding;

import com.upem.bank.service.Compte;
import java.util.Objects;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
public class Compte_ extends Compte {

    @Override
    public String toString() {
        return iban;
    }

    public static Compte_ fromCompte(Compte c) {
        Compte_ n = new Compte_();
        n.amount = c.getAmount();
        n.comptePassword = c.getComptePassword();
        n.currency = c.getCurrency();
        n.iban = c.getIban();
        n.ownerFirstName = c.getOwnerFirstName();
        n.ownerLastName = c.getOwnerLastName();

        return n;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.iban);
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compte_ other = (Compte_) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.iban, other.iban);
    }

}
