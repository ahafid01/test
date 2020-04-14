package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiPage;
import fr.olaqin.pfd.api.model.ApiPageable;
import fr.olaqin.pfd.api.model.ApiSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public abstract class ApiPageMapper<API, MODEL> {

    protected abstract API toApi(Page<MODEL> page);

    protected ApiPage toApiPage(Page page) {
        if (Objects.isNull(page)) {
            return null;
        }

        return new ApiPage()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .number(page.getNumber())
                .numberOfElements(page.getNumberOfElements())
                .pageable(toApiPageable(page.getPageable()))
                .size(page.getSize())
                .sort(toApiSort(page.getSort()))
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages());

    }

    private ApiPageable toApiPageable(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            return null;
        }

        return new ApiPageable()
                .offset(pageable.getOffset())
                .paged(pageable.isPaged())
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .sort(toApiSort(pageable.getSort()));
    }

    private ApiSort toApiSort(Sort sort) {
        if (Objects.isNull(sort)) {
            return null;
        }

        return new ApiSort()
                .empty(sort.isEmpty())
                .sorted(sort.isSorted())
                .unsorted(sort.isUnsorted());
    }
}
