/*
 * Copyright 2013-2015 the original author or authors.
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

package org.springframework.cloud.sleuth.util;

import org.apache.commons.logging.Log;

/**
 * @author Spencer Gibb
 */
public abstract class ExceptionUtils {
	private static final Log log = org.apache.commons.logging.LogFactory
			.getLog(ExceptionUtils.class);
	private static boolean fail = false;

	public static void warn(String msg) {
		if (fail) {
			throw new IllegalStateException(msg);
		}
		log.warn(msg);
	}
	public static void setFail(boolean fail) {
		ExceptionUtils.fail = fail;
	}
}
