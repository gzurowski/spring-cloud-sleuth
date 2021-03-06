/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * Default implementation of SpanNamer that tries to get the Span name as follows:
 *
 *  <li>
 *     <ul>from the @SpanName annotation if one is present</ul>
 *     <ul>from the toString() of the delegate if it's not the
 *     default {@link Object#toString()}</ul>
 *     <ul>the default provided value</ul>
 * </li>
 *
 * @see org.springframework.cloud.sleuth.SpanName
 *
 * @author Marcin Grzejszczak
 */
public class DefaultSpanNamer implements SpanNamer {

	@Override
	public String name(Object object, String defaultValue) {
		SpanName annotation = AnnotationUtils
				.findAnnotation(object.getClass(), SpanName.class);
		String spanName = annotation != null ? annotation.value() : object.toString();
		// If there is no overridden toString method we'll put a constant value
		if (isDefaultToString(object, spanName)) {
			return defaultValue;
		}
		return spanName;
	}

	private static boolean isDefaultToString(Object delegate, String spanName) {
		return (delegate.getClass().getName() + "@" +
				Integer.toHexString(delegate.hashCode())).equals(spanName);
	}
}
