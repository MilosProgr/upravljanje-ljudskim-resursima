import { Link } from "./link";

export interface PagedHateoasResponse<T> {
    content: T[];             // or `value` or `data`, depending on your API
    pageNumber: number;       // how many per page
    pageSize: number;    // pageSize
    totalElements: number;     // total number of records across all pages
    totalPages: number;     // total number of pages
    links?: Link[];         // HATEOAS navigation links
}