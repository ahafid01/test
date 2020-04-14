package fr.olaqin.pfd.service.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T candidate);

    Specification<T> or(Specification<T> specification);

    Specification<T> and(Specification<T> specification);

    Specification<T> not();
}
