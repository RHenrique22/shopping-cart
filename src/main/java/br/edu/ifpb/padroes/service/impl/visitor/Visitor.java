package br.edu.ifpb.padroes.service.impl.visitor;

import java.math.BigDecimal;
import java.util.List;

import br.edu.ifpb.padroes.model.Book;
import br.edu.ifpb.padroes.model.Electronic;
import br.edu.ifpb.padroes.model.Product;

public interface Visitor {
    BigDecimal visit(Book book);
    BigDecimal visit(Electronic electronic);
    BigDecimal visit(Book book, BigDecimal discount);
    BigDecimal visit(Electronic electronic, BigDecimal discount);
//    BigDecimal visit(Product product, BigDecimal discount);
}
