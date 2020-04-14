package fr.olaqin.pfd.service.specification;

public class OrSpecification<T> extends AbstractCompositeSpecification<T> {

    @SafeVarargs
    public OrSpecification(Specification<T>... specifications) {
        super(specifications);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        boolean result = false;

        for (final Specification<T> specification : this.specifications) {
            result |= specification.isSatisfiedBy(candidate);
        }
        return result;
    }
}
