package com.demo.springboot.dto;

public class Meta {
	
	private final Long offset;
	private final Integer pageSize;
	private final Integer pageNumber;	
	private final Long totalElements;
	private final Integer totalPages;
	private final Boolean last;
	
	private Meta(Builder builder) {		
		this.offset = builder.offset;
		this.pageSize = builder.pageSize;
		this.pageNumber = builder.pageNumber;
		this.totalElements = builder.totalElements;
		this.totalPages = builder.totalPages;
		this.last = builder.last;
	}
	
	
	public Long getOffset() {
		return offset;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public Boolean getLast() {
		return last;
	}

	public static class Builder {
		private final Long offset;
		private final Integer pageSize;
		private Integer pageNumber;	
		private Long totalElements;
		private Integer totalPages;
		private Boolean last;
		
		public Builder(long offset, Integer pageSize) {
			super();
			this.offset = offset;
			this.pageSize = pageSize;
		}

		public Builder pageNumber(Integer pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public Builder totalElements(Long totalElements) {
			this.totalElements = totalElements;
			return this;
		}

		public Builder totalPages(Integer totalPages) {
			this.totalPages = totalPages;
			return this;
		}

		public Builder last(Boolean last) {
			this.last = last;
			return this;
		}
		
		public Meta build(){
			return new Meta(this);
		}
		
	}

}
