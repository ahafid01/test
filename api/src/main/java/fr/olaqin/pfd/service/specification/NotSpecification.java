package fr.olaqin.pfd.service.specification;

public class NotSpecification<T> extends AbstractCompositeSpecification<T> {
    public NotSpecification(Specification<T> specification) {
        super(specification);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isSatisfiedBy(final T candidate) {
        return !specifications[0].isSatisfiedBy(candidate);
    }
}
