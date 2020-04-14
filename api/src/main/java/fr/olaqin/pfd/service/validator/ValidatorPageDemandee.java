package fr.olaqin.pfd.service.validator;

import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.common.NotFoundException;
import fr.olaqin.pfd.service.specification.AbstractLeafSpecification;
import org.springframework.data.domain.Page;

public class ValidatorPageDemandee extends AbstractLeafSpecification<Page> {

    private int currentPage;
    private int size;

    @Override
    public boolean isSatisfiedBy(Page candidate) {
        if (candidate.hasContent()) {
            return true;
        }
        throw new NotFoundException(
                ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_PAGE,
                ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_PAGE,
                new Object[]{size, currentPage}
        );
    }

    public ValidatorPageDemandee setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public ValidatorPageDemandee setSize(int size) {
        this.size = size;
        return this;
    }
}
