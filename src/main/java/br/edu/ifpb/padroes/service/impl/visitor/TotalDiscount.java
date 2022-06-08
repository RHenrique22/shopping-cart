package br.edu.ifpb.padroes.service.impl.visitor;

import br.edu.ifpb.padroes.model.Book;
import br.edu.ifpb.padroes.model.Electronic;
import br.edu.ifpb.padroes.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public class TotalDiscount implements Visitor {

    private static final BigDecimal BOOK_DISCOUNT       = BigDecimal.valueOf(0.3); // 30 %
    private static final BigDecimal ELECTRONIC_DISCOUNT = BigDecimal.valueOf(0.05); // 5 %

    public BigDecimal calcDiscount(Map<Product, Integer> products, BigDecimal discount) {
        return products.keySet().stream()
                .filter(product -> discount == null)
                .map(
                        product -> product.accept(this)
                                          .multiply(BigDecimal.valueOf(products.get(product)))
                )
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(products.keySet()
                .stream()
                .filter(product -> discount != null)
                .map(
                        product -> product.accept(this, discount)
                                          .multiply(BigDecimal.valueOf(products.get(product)))
                )
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO));
    }

    @Override
    public BigDecimal visit(Book book) {
        return book.getPrice().multiply(BOOK_DISCOUNT);
    }

    @Override
    public BigDecimal visit(Electronic electronic) {
        return electronic.getPrice().multiply(ELECTRONIC_DISCOUNT);
    }

    @Override
    public BigDecimal visit(Book book, BigDecimal discount) {
        return book.getPrice().multiply(discount);
    }

    @Override
    public BigDecimal visit(Electronic electronic, BigDecimal discount) {
        return electronic.getPrice().multiply(discount);
    }
}
