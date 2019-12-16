/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 13, 2019
 */
package com.ishanitech.ipalika.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public abstract class Converter<T, C> {
	private final Function<T, C> fromDto;
	private final Function<C, T> fromEntity;
	
	public final C convertFromDto(final T fromDto) {
		return this.fromDto.apply(fromDto);
	}
	
	public final T convertFromEntity(final C fromEntity) {
		return this.fromEntity.apply(fromEntity);
	}
	
	public final List<C> createFromDtos(final List<T> fromDtos) {
		return fromDtos.stream().map(this::convertFromDto).collect(Collectors.toList());
	}
	
	public final List<T> createFromEntities(final List<C> fromEntities) {
		return fromEntities.stream().map(this::convertFromEntity).collect(Collectors.toList());
	}
}
