package com.starter.core.domain.auth;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class AuthTokenAuthoritiesConverter implements AttributeConverter<Set<String>, String> {

	@Override
	public String convertToDatabaseColumn(Set<String> attribute) {
		return Optional.ofNullable(attribute)
			.map(attr -> String.join(",", attr))
			.orElse(null);
	}

	@Override
	public Set<String> convertToEntityAttribute(String column) {
		return Optional.ofNullable(column)
			.map(c -> Arrays.stream(c.split(",")).collect(Collectors.toSet()))
			.orElseGet(HashSet::new);
	}

}
