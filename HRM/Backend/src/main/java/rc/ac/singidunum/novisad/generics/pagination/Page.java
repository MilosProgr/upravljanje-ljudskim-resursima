package rc.ac.singidunum.novisad.generics.pagination;

import java.util.List;

import rc.ac.singidunum.novisad.generics.Link.Link.LinkDto;

public class Page<T> {
	 private List<T> content;
	    private int pageNumber;
	    private int pageSize;
	    private long totalElements;
	    private int totalPages;
	    private List<LinkDto> links; 

	    public Page(List<T> content, int pageNumber, int pageSize, long totalElements) {
	        this.content = content;
	        this.pageNumber = pageNumber;
	        this.pageSize = pageSize;
	        this.totalElements = totalElements;
	        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
	    }

	    public List<T> getContent() {
	        return content;
	    }

	    public int getPageNumber() {
	        return pageNumber;
	    }

	    public int getPageSize() {
	        return pageSize;
	    }

	    public long getTotalElements() {
	        return totalElements;
	    }

	    public int getTotalPages() {
	        return totalPages;
	    }

	    public List<LinkDto> getLinks() { return links; }
	    public void setLinks(List<LinkDto> links) { this.links = links; }
}
