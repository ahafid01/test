package fr.olaqin.pfd.service.specification;

public abstract class AbstractLeafSpecification<T> extends AbstractCompositeSpecification<T> {

    protected AbstractLeafSpecification(Specification<T>... specifications) {
        super(specifications);
    }

}
