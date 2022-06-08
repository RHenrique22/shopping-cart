package br.edu.ifpb.padroes.model;

import br.edu.ifpb.padroes.service.impl.visitor.Visitor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("electronic")
public class Electronic extends Product {

    @Override
    public BigDecimal accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public BigDecimal accept(Visitor visitor, BigDecimal discount) {
        return visitor.visit(this, discount);
    }
}
