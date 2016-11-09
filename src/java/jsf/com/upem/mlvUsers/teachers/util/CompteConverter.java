/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.com.upem.mlvUsers.teachers.util;

import com.upem.users.service.bank.binding.Compte_;
import com.upem.users.service.bank.client.BankServiceClient;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Named
@FacesConverter("compteConverter")
public class CompteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return (Compte_) BankServiceClient.getAccountByIBAN(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Compte_)) {
            return "";
        }
        return ((Compte_) value).getIban();
    }

}
