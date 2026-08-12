package com.corpcazorla.epidemiological.infrastructure.adapter.in.dto;

public class Pagination {

    private int page;
    private int size;
    private int totalElements;
    private int totalPages;

    public Pagination(int page, int size, int totalElements, int totalPages) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
    // ========================================
    // Factory Method Enterprise
    // ========================================

    public static Pagination of(Integer totalElements) {

        return new Pagination(
                1,
                1,
                 totalElements,
                1
        );
    }
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
    

}
