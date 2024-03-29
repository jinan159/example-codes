package com.jjikmuk.sikdorak.common.controller.request;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CursorPageRequest {

	@NotNull
	@Min(0)
	@Max(Long.MAX_VALUE)
	private Long before;

	@NotNull
	@Min(0)
	@Max(Long.MAX_VALUE)
	private Long after;

	@NotNull
	@Min(1)
	private int size;

	private boolean isAfter;

	public CursorPageRequest(Long before, Long after, int size, boolean isAfter) {
		this.before = before;
		this.after = after;
		this.size = size;
		this.isAfter = isAfter;
	}

	public boolean isAfter() {
		return isAfter;
	}

	public boolean isFirstPage() {
		return isAfter && Objects.nonNull(after) && (after == 0L);
	}
}
