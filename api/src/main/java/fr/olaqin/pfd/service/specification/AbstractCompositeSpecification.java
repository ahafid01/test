package fr.olaqin.pfd.service.specification;


abstract class AbstractCompositeSpecification<T> implements Specification<T> {

    protected final Specification[] specifications;

    @SafeVarargs
    protected AbstractCompositeSpecification(Specification<T>... specifications) {
        this.specifications = specifications;
    }

    @Override
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

    @Override
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }

    @Override
    public Specification<T> not() {
        return new NotSpecification<>(this);
    }
}
