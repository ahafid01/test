package fr.olaqin.pfd.service.specification;

public class AndSpecification<T> extends AbstractCompositeSpecification<T> {


    @SafeVarargs
    public AndSpecification(Specification<T>... specifications) {
        super(specifications);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isSatisfiedBy(final T candidate) {
        boolean result = true;

        for (final Specification<T> specification : this.specifications) {
            result &= specification.isSatisfiedBy(candidate);
        }
        return result;
    }
}
